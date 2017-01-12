/**
 * 
 */
package com.oneg.whsquared.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.util.EventHelper;
import com.oneg.whsquared.util.ResponseData;

/**
 * 
 * This API gives detailed information of events by trending, by today's date,
 * by city state, and by price
 * 
 */
@RestController
@RequestMapping("/api/event")
public class RestEventController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EventHelper eventHelper;

	/**
	 * 
	 * Gets all events from database.
	 * 
	 */
	@RequestMapping(value = "listAllEvents", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEvents(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside listAllEvents method");
		return eventHelper.onAllEvents(page, size);
	}

	/**
	 * 
	 * Note : Need to update the service with trending algorithm.
	 * 
	 */
	@RequestMapping(value = "listAllEventsByTrending", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsByTrending(@RequestParam(name = "customerId") Integer customerId, @RequestParam(name = "latitude") Integer latitude,
			@RequestParam(name = "longitude") Integer longitude, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside listAllEventsByTrending method");
		return eventHelper.onTrendingEvents(page, size);
	}

	/**
	 * 
	 * List Event's By Date
	 * 
	 */
	@RequestMapping(value = "listAllEventsByDate", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsByDate(@RequestParam(name = "date") String date, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside listAllEventsByDate method");
		return eventHelper.onEventsByDate(date, page, size);
	}

	/**
	 * 
	 * List Event's By Location
	 */
	@RequestMapping(value = "listAllEventsByLocation", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsByLocation(@RequestParam(name = "city") String city, @RequestParam(name = "state") Integer state,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside listAllEventsByLocation method");
		return eventHelper.onEventsByLocation(city, state, page, size);
	}

	/**
	 * 
	 * 
	 * All Event's By Price
	 * 
	 */
	@RequestMapping(value = "listAllEventsByPrice", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsByPrice(@RequestParam(name = "startRange") BigDecimal startRange, @RequestParam(name = "endRange") BigDecimal endRange,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside listAllEventsByPrice method");
		return eventHelper.onEventsByPrice(startRange, endRange, page, size);
	}

	/**
	 * 
	 * Today's Events
	 * 
	 */
	@RequestMapping(value = "listTodaysEvents", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListTodaysEvents(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside listTodaysEvents method");
		return eventHelper.onTodaysEvents(page, size);
	}

	/**
	 * 
	 * 
	 * Today's Event's By Location
	 * 
	 */
	@RequestMapping(value = "listTodaysEventsByLocation", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListTodaysEventsByLocation(@RequestParam(name = "city") String city, @RequestParam(name = "state") Integer state,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside listTodaysEventsByLocation method");
		return eventHelper.onTodaysEventsByLocation(city, state, page, size);
	}

	/**
	 * 
	 * Today's Events By Price
	 */
	@RequestMapping(value = "listTodaysEventsByPrice", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListTodaysEventsByPrice(@RequestParam(name = "startRange") BigDecimal startRange, @RequestParam(name = "endRange") BigDecimal endRange,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside listTodaysEventsByPrice method");
		return eventHelper.onTodaysEventsByPrice(startRange, endRange, page, size);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "listAllEventsOnDateOrder", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsOnDateOrder(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside listAllEventsOnDateOrder method");
		return eventHelper.onEventsOrderByDate(page, size);
	}

	/**
	 * 
	 * List Event's By Date
	 * 
	 */
	@RequestMapping(value = "listAllEventsByDateOnDateOrder", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsByDateOnDateOrder(@RequestParam(name = "date") String date, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside listAllEventsByDateOnDateOrder method");
		return eventHelper.onEventsDateOrderByDate(date, page, size);
	}

	/**
	 * 
	 * List Event's By Location
	 */
	@RequestMapping(value = "listAllEventsByLocationOnDateOrder", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsByLocationOnDateOrder(@RequestParam(name = "city") String city, @RequestParam(name = "state") Integer state,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside listAllEventsByLocationOnDateOrder method");
		return eventHelper.onEventsLocationByDateOrder(city, state, page, size);
	}

	/**
	 * 
	 * All Event's By Price
	 * 
	 */
	@RequestMapping(value = "listAllEventsByPriceOnDateOrder", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsByPriceOnDateOrder(@RequestParam(name = "startRange") BigDecimal startRange, @RequestParam(name = "endRange") BigDecimal endRange,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside listAllEventsByPriceOnDateOrder method");
		return eventHelper.onEventsByPriceDateOrder(startRange, endRange, page, size);
	}

}
