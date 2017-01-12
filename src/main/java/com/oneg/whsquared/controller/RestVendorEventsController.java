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

import com.oneg.whsquared.util.Helper;
import com.oneg.whsquared.util.ResponseData;
import com.oneg.whsquared.util.RestMessage;

/**
 * @author arivu
 * 
 */
@RestController
@RequestMapping("/api/vendorEvents")
public class RestVendorEventsController {

	@Autowired
	private Helper helper;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "eventDetailsById", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onEventDetailsById(@RequestParam(value = "eventId") Integer eventId, @RequestParam(value = "customerId") Integer customerId) throws Exception {
		logger.debug("onEventDetailsById method");
		return helper.constructEventDetails(eventId, customerId);
	}

	/**
	 * List Event Details by vendor
	 */
	@RequestMapping(value = "eventDetailsByVendorId", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onEventDetailsByVendorId(@RequestParam(value = "vendorId") Integer vendorId, @RequestParam(value = "eventId") Integer eventId) throws Exception {
		logger.debug("onEventDetailsByVendorId");
		if (vendorId != null) {
			return helper.constructVendorEventDetails(vendorId, eventId);
		} else {
			return RestMessage.onValidationErrorMessage("Vendor ID is empty");
		}

	}

	/**
	 * List Event Details by category
	 */
	@RequestMapping(value = "eventDetailsByCategoryId", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onEventDetailsByCategoryId(@RequestParam(value = "vendorId") Integer vendorId, @RequestParam(value = "categoryId") Integer categoryId,
			@RequestParam(value = "eventId") Integer eventId) throws Exception {
		logger.debug("onEventDetailsByCategoryId");
		return helper.constructCategoryEventDetails(vendorId, categoryId, eventId);
	}
}
