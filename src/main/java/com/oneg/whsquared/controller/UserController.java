/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.Role;
import com.oneg.whsquared.entity.User;
import com.oneg.whsquared.entity.UserVendors;
import com.oneg.whsquared.entity.Vendor;
import com.oneg.whsquared.repository.CustomerRepository;
import com.oneg.whsquared.repository.EventRepository;
import com.oneg.whsquared.repository.RoleRepository;
import com.oneg.whsquared.repository.UserRepository;
import com.oneg.whsquared.repository.UserVendorRepository;
import com.oneg.whsquared.repository.VendorViewRepository;
import com.oneg.whsquared.repository.vendor.VendorAddressRepository;
import com.oneg.whsquared.repository.vendor.VendorRepository;
import com.oneg.whsquared.request.ResetPassRequest;
import com.oneg.whsquared.security.JwtUtil;
import com.oneg.whsquared.util.EmailFromLocal;
import com.oneg.whsquared.util.ResponseType;
import com.oneg.whsquared.util.Util;
import com.oneg.whsquared.util.WHStatus;

/**
 * 
 * @author Anbukkani G
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private Util util;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	VendorRepository vendorRepo;

	@Autowired
	private VendorAddressRepository vendorAddressRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private RoleRepository roleRpository;

	@Autowired
	private EventController eventController;

	@Autowired
	private WHCommon whCommon;

	@Autowired
	private VendorViewRepository vendorViewRepository;

	@Autowired
	private EmailFromLocal emailFromLocal;

	@Autowired
	private UserVendorRepository userVendorRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Transactional
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ResponseType findAll(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size, @RequestParam(name = "searchText", defaultValue = "") String searchText,
			HttpServletRequest httpServletRequest) throws Exception {
		Page<User> users = null;
		JSONObject jObject = new JSONObject();
		JSONArray jUserArray = new JSONArray();
		try {
			LinkedHashMap linkedHashMap = jwtUtil.getUser(httpServletRequest);
			Integer userId = (Integer) linkedHashMap.get("id");
			User user = userRepository.findOne(userId);
			Role role = user.getRole();
			if (role == null) {
				return util.response(WHStatus.FAILURE.value(), "Invalid user role.", null);
			}
			String roleName = role.getName();
			PageRequest request = new PageRequest(page, size);
			// users = userRepository.findAll(request);
			if (roleName != null && roleName.equalsIgnoreCase("ROLE_ADMIN")) {
				if (searchText.isEmpty()) {
					users = userRepository.findAll(request);
				} else {
					users = userRepository.search(searchText, request);
				}
			} else {
				return util.response(WHStatus.FAILURE.value(), "Access denied", null);
			}

			if (users != null && users.getSize() > 0) {
				for (User userVal : users) {
					JSONObject jUserObj = generateUserListJSON(userVal);
					jUserArray.add(jUserObj);
				}
				jObject.put("users", jUserArray);
				jObject.put("first", users.isFirst());
				jObject.put("last", users.isLast());
				jObject.put("totalElements", users.getTotalElements());
				jObject.put("totalPages", users.getTotalPages());
				jObject.put("size", users.getSize());
				jObject.put("number", users.getNumber());
				jObject.put("sort", users.getSort());
				jObject.put("numberOfElements", users.getNumberOfElements());
			} else {
				jObject.put("users", jUserArray);
				jObject.put("first", true);
				jObject.put("last", true);
				jObject.put("totalElements", 0);
				jObject.put("totalPages", 1);
				jObject.put("size", size);
				jObject.put("number", null);
				jObject.put("sort", null);
				jObject.put("numberOfElements", 0);
			}
			/*
			 * Page<Customer> customers = customerRepository.findAll(request);
			 * userSearchResponse.setUsers(users);
			 * userSearchResponse.setCustomers(customers);
			 */
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), "Server error", "");
		}
		return util.response(WHStatus.SUCCESS.value(), "", jObject);
	}

	@Transactional
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType findById(@PathVariable(value = "userId") int userId) {
		User user = null;
		JSONObject jObj = new JSONObject();
		try {
			user = userRepository.findOne(userId);
			jObj = generateUserJSON(user);

		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), "Server error", "");
		}
		return util.response(WHStatus.SUCCESS.value(), "", jObj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ResponseType<Object> saveUser(@RequestBody User user) {
		try {
			User newUser = new User();
			String password = user.getPasswordTemp();
			if (password == null || password.isEmpty()) {
				return util.response(WHStatus.FAILURE.value(), "Password Missing", "");
			}
			newUser.setPassword(DigestUtils.md5Hex(password));
			String email = user.getEmail();
			if (email == null || email.isEmpty()) {
				return util.response(WHStatus.FAILURE.value(), "Email id Missing", "");
			}
			User duplicate = userRepository.findByEmail(email);
			if (duplicate != null) {
				return util.response(WHStatus.FAILURE.value(), "Email id already exist", "");
			}
			String userName = user.getUserName();
			if (userName == null || userName.isEmpty()) {
				return util.response(WHStatus.FAILURE.value(), "username id Missing", "");
			}
			duplicate = userRepository.findUser(userName);
			if (duplicate != null) {
				return util.response(WHStatus.FAILURE.value(), "Username  already exist", "");
			}
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setEmail(user.getEmail());
			newUser.setAvatarUrl(util.uploadFile(user.getAvatarUrl()));
			newUser.setUserName(user.getUserName());
			if (user.getRole() == null) {
				return util.response(WHStatus.FAILURE.value(), "Role Missing", "");
			}
			Role role = new Role();
			role.setId(user.getRole().getId());
			newUser.setRole(role);
			User savedUser = userRepository.save(newUser);
			savedUser.setId(newUser.getId());
			if (user.getUserVendorList() != null && user.getUserVendorList().size() > 0) {
				List<UserVendors> userVendors = new ArrayList<UserVendors>();
				for (UserVendors userVendor : user.getUserVendorList()) {
					userVendor.setUser(savedUser);
					userVendor.setVendor(userVendor.getVendor());
					userVendors.add(userVendor);
					userVendorRepository.save(userVendor);
				}
				newUser.setUserVendorList(userVendors);
			}
			user = newUser;
		} catch (Exception e) {
			return util.response(WHStatus.SUCCESS.value(), "Server error", "");
		}
		return util.response(WHStatus.SUCCESS.value(), "", user);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody
	ResponseType<Object> updateUser(@RequestBody User user) {
		try {
			User updateUser = userRepository.findOne(user.getId());
			String email = user.getEmail();
			if (email == null || email.isEmpty()) {
				return util.response(WHStatus.FAILURE.value(), "Email id Missing", "");
			}
			User duplicate = userRepository.findByEmail(email);
			if (duplicate != null && !(duplicate.getId().equals(user.getId()))) {
				return util.response(WHStatus.FAILURE.value(), "Email id already exist", "");
			}
			String userName = user.getUserName();
			if (userName == null || userName.isEmpty()) {
				return util.response(WHStatus.FAILURE.value(), "username id Missing", "");
			}
			duplicate = userRepository.findUser(userName);
			if (duplicate != null && !(duplicate.getId().equals(user.getId()))) {
				return util.response(WHStatus.FAILURE.value(), "Username  already exist", "");
			}

			updateUser.setFirstName(user.getFirstName());
			updateUser.setLastName(user.getLastName());
			updateUser.setEmail(user.getEmail());
			updateUser.setAvatarUrl(util.uploadFile(user.getAvatarUrl()));
			updateUser.setUserName(user.getUserName());
			if (user.getRole() == null) {
				return util.response(WHStatus.FAILURE.value(), "Role Missing", "");
			}
			Role role = new Role();
			role.setId(user.getRole().getId());
			updateUser.setRole(role);
			User savedUser = userRepository.save(updateUser);
			savedUser.setId(updateUser.getId());
			if (user.getUserVendorList() != null && user.getUserVendorList().size() > 0) {
				List<UserVendors> userVendors = new ArrayList<UserVendors>();
				List<UserVendors> existUserVendors = userVendorRepository.findByUserId(savedUser.getId());
				if (existUserVendors != null && existUserVendors.size() > 0) {
					for (UserVendors existUserVendor : existUserVendors) {
						userVendorRepository.delete(existUserVendor);
					}
				}
				for (UserVendors userVendor : user.getUserVendorList()) {
					userVendor.setUser(savedUser);
					userVendor.setVendor(userVendor.getVendor());
					userVendors.add(userVendor);
					userVendorRepository.save(userVendor);
				}
				updateUser.setUserVendorList(userVendors);
			}
			user = updateUser;
		} catch (Exception e) {
			return util.response(WHStatus.SUCCESS.value(), "Server error", "");
		}
		return util.response(WHStatus.SUCCESS.value(), "", user);
	}

	@Transactional
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType<Object> getUser(HttpServletRequest httpServletRequest) {
		User user = null;
		try {
			LinkedHashMap linkedHashMap = jwtUtil.getUser(httpServletRequest);
			Integer userId = (Integer) linkedHashMap.get("id");
			user = userRepository.findOne(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", user);
	}

	@Transactional
	@RequestMapping(value = "/reset-pass", method = RequestMethod.POST)
	public @ResponseBody
	ResponseType<Object> resetPassword(@RequestBody ResetPassRequest resetPassRequest, HttpServletRequest httpServletRequest) {
		User user = null;
		try {
			if (resetPassRequest == null || resetPassRequest.getOldPassword() == null) {
				return util.response(WHStatus.FAILURE.value(), "Password should not empty", null);
			} else if (resetPassRequest.getNewPassword() == null) {
				return util.response(WHStatus.FAILURE.value(), "New Password should not empty", null);
			}

			Integer userId = resetPassRequest.getUserId();
			user = userRepository.findOne(userId);
			String password = user.getPassword();
			String oldPass = DigestUtils.md5Hex(resetPassRequest.getOldPassword());
			if (password != null && !password.equalsIgnoreCase(oldPass)) {
				return util.response(WHStatus.FAILURE.value(), "Invalid Password", null);
			} else {
				user.setPassword(DigestUtils.md5Hex(resetPassRequest.getNewPassword().toString()));
				user = userRepository.save(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "Password updated successfully", user);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public @ResponseBody
	ResponseType<Object> delete(@PathVariable("userId") String userId) {
		try {

			User user = userRepository.findOne(Integer.parseInt(userId));

			// List<Vendor> vendors =
			// vendorRepo.findByUserId(Integer.parseInt(userId));

			Role userRole = user.getRole();// roleRpository.findByName("ROLE_ADMIN");
			if (userRole != null) {
				if (userRole.getName().equalsIgnoreCase("ROLE_ADMIN")) {
					throw new Exception("You cannot delete admin record. Please contact administrator.");
				}
			}

			for (UserVendors userVendors : user.getUserVendorList()) {
				userVendorRepository.delete(userVendors);

			}
			/*
			 * for (Vendor vendor : vendors) { List<CustomerFavoriteVendor>
			 * customerFavoriteVendors = vendor.getCustomerFavoriteVendorList();
			 * 
			 * List<VendorAddress> vendorAddressList =
			 * vendorAddressRepository.findByVendorId(vendor.getId()); for
			 * (VendorAddress vendorAddress : vendorAddressList) {
			 * vendorAddressRepository.delete(vendorAddress); } List<Event>
			 * events = eventRepository.findByVendorId(vendor.getId()); for
			 * (Event event : events) { eventController.delete(event.getId()); }
			 * if (!customerFavoriteVendors.isEmpty()) {
			 * whCommon.deleteCustomerFavoriteVendors(customerFavoriteVendors);
			 * } // VendorDetail vendorDetail=vendor.getVendorDetail();
			 * List<VendorView> vendorViews = vendor.getVendorViewList(); for
			 * (VendorView vendorView : vendorViews) {
			 * vendorViewRepository.delete(vendorView); }
			 * 
			 * for (VendorView vendorView : vendorViews) {
			 * vendorViewRepository.delete(vendorView); }
			 * vendorRepo.delete(vendor.getId()); }
			 */
			userRepository.delete(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", null);
	}

	@Transactional
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public @ResponseBody
	ResponseType<Object> forgotPassword(@RequestBody ForgotPassword forgotPassword, HttpServletRequest httpServletRequest) {
		User user = null;
		try {
			if (forgotPassword.getType() == null || forgotPassword.getValue() == null) {
				return util.response(WHStatus.FAILURE.value(), "Required values are missing", null);
			}
			if (forgotPassword.getType().equalsIgnoreCase("email")) {
				user = userRepository.findByEmail(forgotPassword.getValue());
			}
			if (forgotPassword.getType().equalsIgnoreCase("username")) {
				user = userRepository.findOneByUserName(forgotPassword.getValue());
			}
			if (user == null) {
				return util.response(WHStatus.FAILURE.value(), "Invalid " + forgotPassword.getType() + " try again.", null);
			}
			String password = UUID.randomUUID().toString().substring(0, 5);
			user.setPassword(DigestUtils.md5Hex(password));
			userRepository.save(user);

			Map<String, String> replaceMap = new HashMap<String, String>();
			replaceMap.put("name", user.getFirstName() + " " + user.getLastName());
			replaceMap.put("password", password);

			emailFromLocal.sendEmail(user.getEmail(), "WhatsHappenins!-Reset Pasword ", replaceMap, "com/oneg/whsquared/emailtemplate/forgotpassword.html");

		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "Yournew password has been send to your registered email", null);
	}

	@Transactional
	@RequestMapping(value = "vendors/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType findUserVendors(@PathVariable(value = "userId") int userId) {
		List<UserVendors> userVendors = null;
		JSONObject jObj = new JSONObject();
		try {
			userVendors = userVendorRepository.findByUserId(userId);
			jObj = generateUserVendorJSON(userVendors);
			jObj.put("id", userId);

		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), "Server error", "");
		}
		return util.response(WHStatus.SUCCESS.value(), "", jObj);
	}

	public JSONObject generateUserJSON(User user) {
		List<Customer> customers = (List<Customer>) customerRepository.findAll();

		JSONObject jObj = new JSONObject();
		jObj.put("id", user.getId());
		jObj.put("firstName", user.getFirstName());
		jObj.put("lastName", user.getLastName());
		jObj.put("userName", user.getUserName());
		jObj.put("email", user.getEmail());
		jObj.put("avatar", user.getAvatar());
		jObj.put("avatarUrl", user.getAvatarUrl());
		if (user.getRole() != null) {
			JSONObject jRoleObj = new JSONObject();
			jRoleObj.put("id", user.getRole().getId());
			jObj.put("role", jRoleObj);
		}
		List<UserVendors> userVendorList = user.getUserVendorList();
		JSONArray jArray = new JSONArray();
		if (userVendorList != null && userVendorList.size() > 0) {
			for (UserVendors userVendor : userVendorList) {
				JSONObject userVendorObj = new JSONObject();
				userVendorObj.put("id", userVendor.getId());
				JSONObject vendorObj = new JSONObject();
				Vendor vendor = userVendor.getVendor();
				vendorObj.put("id", vendor.getId());
				vendorObj.put("name", vendor.getName());
				userVendorObj.put("vendor", vendorObj);
				jArray.add(userVendorObj);
			}
			jObj.put("userVendorList", jArray);
		} else {
			jObj.put("userVendorList", jArray);
		}
		JSONArray jCustArray = new JSONArray();
		if (customers != null && customers.size() > 0) {
			for (Customer customer : customers) {
				JSONObject customerObj = new JSONObject();
				customerObj.put("id", customer.getId());
				customerObj.put("firstName", customer.getFirstName());
				customerObj.put("lastName", customer.getLastName());
				customerObj.put("userName", customer.getUserName());
				customerObj.put("email", customer.getEmail());
				customerObj.put("phoneNumber", customer.getPhoneNumber());
				customerObj.put("registerationType", customer.getRegisterationType());
				customerObj.put("socialMediaLink", customer.getSocialMediaLink());
				customerObj.put("deviceId", customer.getDeviceId());
				jCustArray.add(customerObj);
			}
		}
		jObj.put("customers", jCustArray);
		return jObj;
	}

	public JSONObject generateUserListJSON(User user) {
		JSONObject jObj = new JSONObject();
		jObj.put("id", user.getId());
		jObj.put("firstName", user.getFirstName());
		jObj.put("lastName", user.getLastName());
		jObj.put("userName", user.getUserName());
		jObj.put("email", user.getEmail());
		if (user.getRole() != null) {
			JSONObject jRoleObj = new JSONObject();
			jRoleObj.put("id", user.getRole().getId());
			jRoleObj.put("name", user.getRole().getName());
			jObj.put("role", jRoleObj);
		}
		return jObj;
	}

	public JSONObject generateUserVendorJSON(List<UserVendors> userVendors) {
		JSONObject jObj = new JSONObject();
		JSONArray jArray = new JSONArray();
		if (userVendors != null && userVendors.size() > 0) {
			for (UserVendors userVendor : userVendors) {
				JSONObject userVendorObj = new JSONObject();
				userVendorObj.put("id", userVendor.getId());
				JSONObject vendorObj = new JSONObject();
				Vendor vendor = userVendor.getVendor();
				vendorObj.put("id", vendor.getId());
				vendorObj.put("name", vendor.getName());
				userVendorObj.put("vendor", vendorObj);
				jArray.add(userVendorObj);
			}
			jObj.put("userVendorList", jArray);
		} else {
			jObj.put("userVendorList", jArray);
		}
		return jObj;
	}

}

class ForgotPassword {

	String type;
	String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
