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

import com.oneg.whsquared.util.EventDetailHelper;
import com.oneg.whsquared.util.ResponseData;

/**
 * @author arivu
 * 
 *         This API gives full information about particular event.
 * 
 */
@RestController
@RequestMapping("/api/eventdetail")
public class RestEventDetailController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EventDetailHelper eventDetailHelper;

	/**
	 * 
	 * Display Event Details
	 * 
	 */
	@RequestMapping(value = "getEventDetailById", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onGetEventDetail(@RequestParam(value = "eventId") Integer eventId, @RequestParam(value = "customerId") Integer customerId) {
		logger.debug("Inside onGetEventDetail method");
		return eventDetailHelper.onEventDetails(eventId, customerId);
	}
}
