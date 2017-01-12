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
import com.oneg.whsquared.util.CustomerProfileHelper;
import com.oneg.whsquared.util.CustomerResponseData;
import com.oneg.whsquared.util.ResponseData;

/**
 * @author arivu
 * 
 *         This API gives information about the customer profile such as
 *         Customer's name etc.
 * 
 */
@RestController
@RequestMapping("/api/customerProfile")
public class RestCustomerProfileController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerProfileHelper customerProfileHelper;

	/**
	 * 
	 * Get customer information based on customerId and return 'Customer not
	 * found ' if record is not exist.
	 * 
	 */
	@RequestMapping(value = "getCustomerProfile", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onCustomerProfile(@RequestParam(value = "customerId") Integer customerId, Device device) {
		logger.debug("Inside onCustomerProfile method");
		return customerProfileHelper.updateCustomerProfileData(customerId, device);
	}

	/**
	 * 
	 * Update customer details such as profile image of customer etc.
	 * 
	 */
	@RequestMapping(value = "updateCustomerProfile", method = RequestMethod.POST, produces = "application/json")
	public CustomerResponseData onUpdateCustomer(@RequestBody Customer customer) {
		logger.debug("Inside onUpdateCustomer method");
		return customerProfileHelper.onCustomerUpdate(customer);
	}
}
