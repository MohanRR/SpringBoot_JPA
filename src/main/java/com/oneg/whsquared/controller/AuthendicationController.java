package com.oneg.whsquared.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import net.minidev.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.entity.Address;
import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.CustomerDevice;
import com.oneg.whsquared.entity.Role;
import com.oneg.whsquared.entity.State;
import com.oneg.whsquared.entity.User;
import com.oneg.whsquared.repository.AddressRepository;
import com.oneg.whsquared.repository.CustomerDeviceRepository;
import com.oneg.whsquared.repository.CustomerRepository;
import com.oneg.whsquared.repository.StateRepository;
import com.oneg.whsquared.repository.UserRepository;
import com.oneg.whsquared.security.CustomerAuthenticationProvider;
import com.oneg.whsquared.security.JwtUtil;
import com.oneg.whsquared.security.ProdutAuthenticationProvider;
import com.oneg.whsquared.util.ResponseType;
import com.oneg.whsquared.util.Util;
import com.oneg.whsquared.util.WHStatus;

/* 
 *This Class has entry point for the service to validate user name and password. 
 */
@RestController
@RequestMapping("/security")
public class AuthendicationController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Util util;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private ProdutAuthenticationProvider authenticationManager;

	@Autowired
	private CustomerAuthenticationProvider customerAuthenticationManager;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CustomerDeviceRepository customerDeviceRepository;

	/*
	 * This method will validate username and password. On successful login, it
	 * will return status, message and token to user else it will return error
	 * information.
	 */
	@RequestMapping(value = "/user-login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseType loginPage(@RequestBody Map<String, String> credentials, Device device) throws Exception {

		UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(credentials.get("userName"), credentials.get("password"));
		try {
			Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);

			if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
				throw new Exception("Unable to authenticate Domain User for provided credentials");
			}
			SecurityContextHolder.getContext().setAuthentication(responseAuthentication);

			// Reload password post-security so we can generate token
			List<User> users = userRepository.findByUserName(credentials.get("userName"));
			User user = null;
			if (users.isEmpty()) {
				return util.response("Error", "invalid username ", "");
			} else {
				user = users.get(0);
			}
			final String token = jwtUtil.generateToken(user, device);
			JSONObject respobse = new JSONObject();
			JSONObject userObj = new JSONObject();
			userObj.put("id", user.getId());
			userObj.put("name", user.getFirstName() + " " + user.getLastName());
			userObj.put("email", user.getEmail());
			userObj.put("user", user.getUserName());
			Role role = user.getRole();
			userObj.put("role", role.getName());
			respobse.put("token", token);
			respobse.put("user", userObj);
			return util.response("success", "security-token generated", respobse);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response("Error", "invalid username or password", "");
		}

	}

	@Transactional
	@RequestMapping(value = "/customer-login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseType customerLogin(@RequestBody Map<String, String> credentials, Device device) throws Exception {

		List<Customer> customers = customerRepository.findByUserName(credentials.get("userName"));
		Customer customer = null;
		if (!customers.isEmpty()) {
			customer = customers.get(0);
		}
		String loginFrom = credentials.get("loginFrom");
		if (loginFrom != null && !loginFrom.equalsIgnoreCase("\"\"") && customer == null) {
			customer = new Customer();
			customer.setUserName(credentials.get("userName"));
			// customer.setPassword("");
			customer.setEmail(credentials.get("email"));
			customer.setFirstName(credentials.get("firstName"));
			customer.setLastName(credentials.get("lastName"));
			customer.setSendMailNotification(credentials.get("sendMailNotification").equals("true") ? true : false);
			customer.setSendPushNotification(credentials.get("sendPushNotification").equals("true") ? true : false);
			customer.setModificationTime(new Date());
			customer.setCreationTime(new Date());
			customer.setRegisterationType(loginFrom);

			String address1 = credentials.get("address1");
			String address2 = credentials.get("address2");
			String city = credentials.get("city");
			String strState = credentials.get("state");
			String zip = credentials.get("zip");
			String lat = credentials.get("lat");
			String longi = credentials.get("long");
			String name = credentials.get("name");
			String phone = credentials.get("phone");
			State state = null;
			if (strState != null && !strState.equalsIgnoreCase("\"\"")) {
				List<State> states = stateRepository.findByName(strState);
				if (!states.isEmpty()) {
					state = states.get(0);
				}
			}
			if (address1 != null && city != null && zip != null && state != null) {
				Address address = new Address();
				address.setAddress1(address1);
				address.setAddress2(address2);
				address.setCity(city);
				address.setZip(zip);
				address.setLat(lat);
				address.setLong1(longi);
				address.setName(name);
				address.setCreationTime(new Date());
				address = addressRepository.save(address);
				customer.setAddress(address);
			}

			customerRepository.save(customer);
		} else if (customers.isEmpty()) {
			return util.response("Error", "invalid username ", "");
		}
		String deviceId = credentials.get("deviceId");
		if (deviceId != null && deviceId.equalsIgnoreCase("\"\"")) {
			// List<CustomerDevice> deviceList =
			// customerDeviceRepository.findByDeviceIdAndCustomerId(deviceId,
			// customer.getId());
			List<CustomerDevice> deviceList = customerDeviceRepository.findByDeviceIdAndCustomer(deviceId, customer.getId());
			if (deviceList.isEmpty()) {
				CustomerDevice customerDevice = new CustomerDevice();
				customerDevice.setCreatedBy(customer.getUserName());
				customerDevice.setCustomer(customer);
				customerDevice.setDeviceId(deviceId);
				customerDeviceRepository.save(customerDevice);
			}
		}
		UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(credentials.get("userName"), credentials.get("password"));
		try {
			Authentication responseAuthentication = customerAuthenticationManager.authenticate(requestAuthentication);

			if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
				throw new Exception("Unable to authenticate Domain User for provided credentials");
			}
			SecurityContextHolder.getContext().setAuthentication(responseAuthentication);

			// Reload password post-security so we can generate token

			final String token = jwtUtil.generateToken(customer, device);
			JSONObject respobse = new JSONObject();
			respobse.put("token", token);
			respobse.put("customer", customer);
			return util.response("success", "security-token generated", respobse);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response("Error", "invalid username or password", "");
		}

	}

	@Transactional
	@RequestMapping(value = "/customer-registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseType customerRegistration(@RequestBody Customer customer, Device device) throws Exception {
		try {
			if (customer != null) {
				if (customer.getAddress() == null) {
					return util.response(WHStatus.FAILURE.value(), "Address should not be null", "");
				}
				Address address = customer.getAddress();
				State state = address.getState();
				Address add = new Address();
				if (state != null) {
					// List<State> states =
					// stateRepository.findByName(state.getName());
					// if(states.isEmpty()){
					// return util.response(WHStatus.FAILURE.value(),
					// "Invalide state", "");
					// }else{
					add.setState(state);
					// }

				} else {
					return util.response(WHStatus.FAILURE.value(), "State should not be null", "");
				}

				add.setAddress1(address.getAddress1());
				add.setAddress2(address.getAddress2());
				add.setCity(address.getCity());
				add.setCreationTime(new Date());
				add.setName(address.getName());
				add.setModificationTime(new Date());
				add.setZip(address.getZip());
				String lat = null;
				add.setLat(lat);
				String long1 = null;
				add.setLong1(long1);
				addressRepository.save(add);
				Customer newCustomer = new Customer();
				newCustomer.setAvatar(customer.getAvatar());
				newCustomer.setCreationTime(new Date());
				newCustomer.setEmail(customer.getEmail());
				newCustomer.setFirstName(customer.getFirstName());
				newCustomer.setLastName(customer.getLastName());
				newCustomer.setRegisterationType("manual");
				newCustomer.setAddress(add);
				String password = DigestUtils.md5Hex(customer.getPassword());
				newCustomer.setPassword(password);
				newCustomer.setSendMailNotification(customer.getSendMailNotification());
				newCustomer.setSendPushNotification(customer.getSendPushNotification());
				newCustomer.setModificationTime(new Date());
				newCustomer.setUserName(customer.getUserName());
				newCustomer = customerRepository.save(newCustomer);
				final String token = jwtUtil.generateToken(newCustomer, device);
				JSONObject respobse = new JSONObject();
				respobse.put("token", token);
				respobse.put("customer", newCustomer);
				return util.response(WHStatus.SUCCESS.value(), "Customer Registration completed successfully", respobse);
			} else {
				return util.response(WHStatus.FAILURE.value(), "Customer should not be null", "");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), ex.getMessage(), "");
		}
	}

	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public @ResponseBody
	void file(@RequestParam("fileName") String fileName, HttpServletResponse response) {
		util.writeFileInResponse(response, fileName);
	}
}
