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

import com.oneg.whsquared.util.EventCategoryHelper;
import com.oneg.whsquared.util.ResponseData;

/**
 * @author Leo
 * 
 *         This API is to display the events by its category
 * 
 */
@RestController
@RequestMapping("/api/eventCategory")
public class RestEventCategoryController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EventCategoryHelper eventCategoryHelper;

	/**
	 * 
	 * Events By Category
	 */
	@RequestMapping(value = "listAllCategoryEventsByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllCategoryEventsByCategory(@RequestParam(name = "categoryId") Integer categoryId,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside onListAllCategoryEventsByCategory method");
		return eventCategoryHelper.onAllEventsByCategory(categoryId, page, size);
	}

	/**
	 * 
	 * 
	 * List Event's By Category on Date
	 * 
	 */
	@RequestMapping(value = "listAllEventsDateByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsDateByCategory(@RequestParam(name = "categoryId") Integer categoryId, @RequestParam(name = "date") String date,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside onListAllCategoryEventsByCategory method");
		return eventCategoryHelper.onAllEventsDateByCategory(categoryId, date, page, size);
	}

	/**
	 * 
	 * 
	 * List Event's By Category on Location
	 */
	@RequestMapping(value = "listAllEventsLocationByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsLocationByCategory(@RequestParam(name = "categoryId") Integer categoryId, @RequestParam(name = "city") String city,
			@RequestParam(name = "state") Integer state, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside onListAllCategoryEventsByCategory method");
		return eventCategoryHelper.onAllEventsByLocation(categoryId, city, state, page, size);
	}

	/**
	 * 
	 * 
	 * List Event's By Category on Price
	 * 
	 */
	@RequestMapping(value = "listAllEventsPriceByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsPriceByCategory(@RequestParam(name = "categoryId") Integer categoryId, @RequestParam(name = "startRange") BigDecimal startRange,
			@RequestParam(name = "endRange") BigDecimal endRange, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside onListAllEventsPriceByCategory method");
		return eventCategoryHelper.onAllEventsByPrice(categoryId, startRange, endRange, page, size);
	}

	/**
	 * 
	 * List Today's Event's By Category
	 * 
	 */
	@RequestMapping(value = "listTodaysEventsByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListTodaysEventsByCategory(@RequestParam(name = "categoryId") Integer categoryId,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside onListTodaysEventsByCategory method");
		return eventCategoryHelper.onAllTodaysEvents(categoryId, page, size);
	}

	/**
	 * 
	 * Today's Event's By Category on Location
	 * 
	 */
	@RequestMapping(value = "listTodaysEventsLocationByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListTodaysEventsLocationByCategory(@RequestParam(name = "categoryId") Integer categoryId, @RequestParam(name = "city") String city,
			@RequestParam(name = "state") Integer state, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside onListTodaysEventsLocationByCategory method");
		return eventCategoryHelper.onAllTodaysEventsByLocation(categoryId, city, state, page, size);
	}

	/**
	 * 
	 * Today's Event's By Category on Price
	 */
	@RequestMapping(value = "listTodaysEventsPriceByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListTodaysEventsPriceByCategory(@RequestParam(name = "categoryId") Integer categoryId, @RequestParam(name = "startRange") BigDecimal startRange,
			@RequestParam(name = "endRange") BigDecimal endRange, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside onListTodaysEventsPriceByCategory method");
		return eventCategoryHelper.onAllTodaysEventsByPrice(categoryId, startRange, endRange, page, size);
	}

	/**
	 * 
	 * List All Event's By Category on DateOrder
	 * 
	 */
	@RequestMapping(value = "listAllEventsOnDateOrderByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsOnDateOrderByCategory(@RequestParam(name = "categoryId") Integer categoryId,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside onListAllEventsOnDateOrderByCategory method");
		return eventCategoryHelper.onAllEventsByDateOrder(categoryId, page, size);
	}

	/**
	 * 
	 * List All Event's By Category on Date
	 * 
	 */
	@RequestMapping(value = "listAllEventsDateOnDateOrderByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsDateOnDateOrderByCategory(@RequestParam(name = "categoryId") Integer categoryId, @RequestParam(name = "date") String date,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside onListAllEventsDateOnDateOrderByCategory");
		return eventCategoryHelper.onAllEventsDateOnDateOrderByCategory(categoryId, date, page, size);
	}

	/**
	 * 
	 * List All Event's By Category on DateOrder Location
	 */
	@RequestMapping(value = "listAllEventsLocationOnDateOrderByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsLocationOnDateOrderByCategory(@RequestParam(name = "categoryId") Integer categoryId, @RequestParam(name = "city") String city,
			@RequestParam(name = "state") Integer state, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		return eventCategoryHelper.onAllEventsLocationOnDateOrderByCategory(categoryId, city, state, page, size);
	}

	/**
	 * 
	 * List All Event's By Category on DateOrder Price
	 * 
	 */
	@RequestMapping(value = "listAllEventsPriceOnDateOrderByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllEventsPriceOnDateOrderByCategory(@RequestParam(name = "categoryId") Integer categoryId, @RequestParam(name = "startRange") BigDecimal startRange,
			@RequestParam(name = "endRange") BigDecimal endRange, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		return eventCategoryHelper.onAllEventsPriceOnDateOrderByCategory(categoryId, startRange, endRange, page, size);
	}

}
