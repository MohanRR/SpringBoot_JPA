/**
 * 
 */
package com.oneg.whsquared.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.util.ResponseData;
import com.oneg.whsquared.util.VendorHelper;

/**
 * @author arivu
 * 
 */
@RestController
@RequestMapping("/api/vendorDetails/")
public class RestVendorDetailsController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	VendorHelper vendorHelper;

	@RequestMapping(value = "getVendorDetailsById", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onGetVendorDetails(@RequestParam(value = "vendorId") Integer vendorId, @RequestParam(value = "customerId") Integer customerId) {
		logger.debug("Inside onGetEventDetail method");
		return vendorHelper.constructVendorDetails(vendorId, customerId);
	}

}
