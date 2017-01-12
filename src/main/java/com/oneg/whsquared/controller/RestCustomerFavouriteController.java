/**
 * 
 */
package com.oneg.whsquared.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.util.CustomerEventHelper;
import com.oneg.whsquared.util.FavouriteEvent;
import com.oneg.whsquared.util.ResponseData;

/**
 * @author arivu
 * 
 *         This API is to capture customer prefered events and customer's
 *         favourite events
 * 
 */
@RestController
@RequestMapping("/api/customerFavourite/")
public class RestCustomerFavouriteController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerEventHelper customerEventHelper;

	/**
	 * 
	 * This method is to favorite the event. And it checks wheather the event
	 * for the customer is available in the customer favorite table.
	 * 
	 */
	@RequestMapping(value = "/favourite", method = RequestMethod.POST, produces = "application/json")
	public ResponseData onFavourite(@RequestBody FavouriteEvent favoriteEvent) {
		logger.debug("Inside favourite method");
		return customerEventHelper.onCustomerFavoriteEvent(favoriteEvent);
	}

	/**
	 * 
	 * This method is delete the event from favorite table which are most
	 * disliked by the customer
	 * 
	 */
	@RequestMapping(value = "/unfavourite", method = RequestMethod.POST, produces = "application/json")
	public ResponseData onUnfavourite(@RequestBody FavouriteEvent favoriteEvent) {
		logger.debug("Inside unfavourite method");
		return customerEventHelper.onCustomerUnFavoriteEvent(favoriteEvent);
	}

	/**
	 * 
	 * This method gives the customer favorite events based on customer id.
	 * 
	 */
	@RequestMapping(value = "/customerFavoriteEvents", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onCustomerFavoriteEvents(@RequestParam(name = "customerId") Integer customerId,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside onCustomerFavoriteEvents method");
		return customerEventHelper.constructCustomerFavoriteEvents(customerId);
	}

	/**
	 * Get Customer favorite vendor places
	 */
	@RequestMapping(value = "/customerFavoriteVendorPlaces", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onusers_list(@RequestParam(name = "customerId") Integer customerId, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside customerFavoriteVendorPlaces method");
		return customerEventHelper.constructCustomerFavoriteVendors(customerId);
	}
}
