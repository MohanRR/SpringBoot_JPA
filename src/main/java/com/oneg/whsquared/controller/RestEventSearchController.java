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

import com.oneg.whsquared.util.EventSearchHelper;
import com.oneg.whsquared.util.ResponseData;
import com.oneg.whsquared.util.RestMessage;

/**
 * @author arivu
 * 
 *         This API is for searching of any event details based on given text
 * 
 */
@RestController
@RequestMapping("/api/eventSearch/")
public class RestEventSearchController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EventSearchHelper eventSearchHelper;

	/**
	 * 
	 * @param searchTerm
	 * @return
	 * 
	 *         Search for particular event details by given text
	 * 
	 */
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public ResponseData onSearch(@RequestParam(value = "search") String searchTerm) {
		logger.debug("Inside onSearch method");
		return RestMessage.onSuccessMessage(eventSearchHelper.constructSearchResults(searchTerm));
	}
}
