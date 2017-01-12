/**
 * 
 */
package com.oneg.whsquared.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.util.CredetialHelper;
import com.oneg.whsquared.util.CustomerResponseData;
import com.oneg.whsquared.util.ResponseData;

/**
 * @author arivu
 * 
 *         This api contains details about the customer registration ,login and
 *         logout. This is mainly for the customer who has customer role in the
 *         wh product.
 * 
 */
@RestController
@RequestMapping("/security/customer")
public class RestCustomerController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CredetialHelper credentialHelper;

	/**
	 * 
	 * @param customer
	 * @return
	 * 
	 *         This login method is for the customer who has the customer role
	 *         to login to the application via web service. This method does the
	 *         following instructions.
	 * 
	 *         Verifying the registration type whether the login is from
	 *         manually registered or social media registration.
	 * 
	 *         If social then validate via register type and password
	 * 
	 *         else validate via register type and manual password.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public CustomerResponseData onLogin(@RequestBody Customer customer, Device device) {
		logger.debug("Login Using " + customer.getUserName());
		return credentialHelper.onLoginProcess(customer, device);
	}

	/**
	 * 
	 * @param customer
	 * @return
	 * 
	 *         Register new customer
	 * 
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	public CustomerResponseData onRegister(@RequestBody Customer customer, Device device) {
		return credentialHelper.onRegistration(customer, device);
	}

	/**
	 * Check app version for each and every new version updation.
	 */
	@RequestMapping(value = "/checkAppVersion", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onCheckAppVersion(@RequestParam(name = "appVersionFromClient") String appVersionFromClient) {
		return credentialHelper.onAppVersion(appVersionFromClient);
	}

	/**
	 * 
	 * @param customer
	 * @param device
	 * @return
	 * 
	 *         Check customer exists for sending notification
	 */
	@RequestMapping(value = "/checkCustomerExists", method = RequestMethod.POST, produces = "application/json")
	public CustomerResponseData onCheckCustomerExists(@RequestBody Customer customer, Device device) {
		return credentialHelper.onCustomerExistence(customer, device);
	}

}
