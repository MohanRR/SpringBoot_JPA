package com.oneg.whsquared.controller;

import javax.servlet.http.HttpServletRequest;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

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

import com.oneg.whsquared.entity.Address;
import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.repository.AddressRepository;
import com.oneg.whsquared.repository.CustomerRepository;
import com.oneg.whsquared.repository.PreferenceRepository;
import com.oneg.whsquared.security.JwtUtil;
import com.oneg.whsquared.util.ResponseType;
import com.oneg.whsquared.util.Util;
import com.oneg.whsquared.util.WHStatus;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private Util util;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PreferenceRepository preferenceRepository;

	@Transactional
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ResponseType findAll(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size, @RequestParam(name = "searchText", defaultValue = "") String searchText,
			HttpServletRequest httpServletRequest) throws Exception {
		Page<Customer> customers = null;
		JSONObject jObject = new JSONObject();
		JSONArray jCustArray = new JSONArray();
		try {
			PageRequest request = new PageRequest(page, size);
			if (searchText.isEmpty())
				customers = customerRepository.findAll(request);
			else
				customers = customerRepository.search(searchText, request);
			if (customers != null && customers.getSize() > 0) {
				for (Customer customerVal : customers) {
					JSONObject jCustomerObj = generateCustomerJSON(customerVal);
					jCustArray.add(jCustomerObj);
				}
				jObject.put("customers", jCustArray);
				jObject.put("first", customers.isFirst());
				jObject.put("last", customers.isLast());
				jObject.put("totalElements", customers.getTotalElements());
				jObject.put("totalPages", customers.getTotalPages());
				jObject.put("size", customers.getSize());
				jObject.put("number", customers.getNumber());
				jObject.put("sort", customers.getSort());
				jObject.put("numberOfElements", customers.getNumberOfElements());
			} else {
				jObject.put("users", jCustArray);
				jObject.put("first", true);
				jObject.put("last", true);
				jObject.put("totalElements", 0);
				jObject.put("totalPages", 1);
				jObject.put("size", size);
				jObject.put("number", null);
				jObject.put("sort", null);
				jObject.put("numberOfElements", 0);
			}
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), "Server error", "");
		}
		return util.response(WHStatus.SUCCESS.value(), "", jObject);
	}

	@Transactional
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType findById(@PathVariable(value = "customerId") int customerId) {
		Customer customer = null;
                JSONObject jCustomerObj = new JSONObject();
		try {
			customer = customerRepository.findOne(customerId);
                        if (customer != null) {
                            jCustomerObj = generateCustomerJSON(customer);
                        }
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), "Server error", "");
		}
		return util.response(WHStatus.SUCCESS.value(), "", jCustomerObj);
	}

	@Transactional
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody
	ResponseType updateCustomer(@RequestBody Customer customer) {
		try {
			if (customer.getAddress() != null) {
				Address savedAddress = addressRepository.save(customer.getAddress());
				customer.setAddress(savedAddress);
			}
			customer = customerRepository.save(customer);

		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), "Server error", "");
		}
		return util.response(WHStatus.SUCCESS.value(), "", customer);
	}

	@Transactional
	@RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
	public @ResponseBody
	ResponseType deleteCustomer(@PathVariable(value = "customerId") int customerId) {
		try {
			Customer customer = customerRepository.findOne(customerId);
			if (customer != null) {
				if (customer.getCalendarEventList() != null && customer.getCalendarEventList().size() > 0) {
					customerRepository.deleteCalendarEventByCustomerId(customerId);
				}
				if (customer.getCustomerFavoriteVendorList() != null && customer.getCustomerFavoriteVendorList().size() > 0) {
					customerRepository.deleteCustomerFavoriteByCustomerId(customerId);
				}
				if (customer.getCustomerFavoriteCategoryList() != null && customer.getCustomerFavoriteCategoryList().size() > 0) {
					customerRepository.deleteCustomerFavoriteCategoryByCustomerId(customerId);
				}
				if (customer.getPasswordVerificationList() != null && customer.getPasswordVerificationList().size() > 0) {
					customerRepository.deletePasswordVerificationByCustomerId(customerId);
				}
				if (customer.getCustomerNotificationList() != null && customer.getCustomerNotificationList().size() > 0) {
					customerRepository.deleteCustomerNotificationByCustomerId(customerId);
				}
				if (customer.getAddress() != null) {
					addressRepository.delete(customer.getAddress());
				}
				preferenceRepository.deletePreferenceByCustomerId(customerId);
				customerRepository.delete(customerId);

			} else {
				return util.response(WHStatus.FAILURE.value(), "Customer was not found", null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", null);
	}

	public JSONObject generateCustomerJSON(Customer customer) {
		JSONObject jObj = new JSONObject();
		jObj.put("id", customer.getId());
		jObj.put("firstName", customer.getFirstName());
		jObj.put("lastName", customer.getLastName());
		jObj.put("userName", customer.getUserName());
		jObj.put("email", customer.getEmail());
		jObj.put("phoneNumber", customer.getPhoneNumber());
		jObj.put("registerationType", customer.getRegisterationType());
		jObj.put("socialMediaLink", customer.getSocialMediaLink());
		jObj.put("deviceId", customer.getDeviceId());
                jObj.put(("address"), customer.getAddress());
                jObj.put(("sendMailNotification"), customer.getSendMailNotification());
                jObj.put(("sendPushNotification"), customer.getSendPushNotification());
                jObj.put(("profileImageUrl"), customer.getProfileImageUrl());
		return jObj;
	}
}
