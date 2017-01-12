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

import com.oneg.whsquared.util.CustomerPreferenceHelper;
import com.oneg.whsquared.util.FavoriteCategoryDTO;
import com.oneg.whsquared.util.ResponseData;

/**
 * @author arivu
 * 
 *         This API gives the information about the customer preference
 *         categories
 * 
 */
@RestController
@RequestMapping("/api/preference")
public class RestPreferenceController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerPreferenceHelper customerPreferenceHelper;

	/**
	 * 
	 * This method provides the category details which are all liked by customer
	 * 
	 */
	@RequestMapping(value = "/favoriteCategory", method = RequestMethod.POST, produces = "application/json")
	public ResponseData onFavoriteCategory(@RequestBody FavoriteCategoryDTO favoriteCategoryDTO) {
		logger.debug("Inside onPreferCategory method");
		return customerPreferenceHelper.onCustomerFavorite(favoriteCategoryDTO);
	}

	/**
	 * List customer favorite categories
	 */
	@RequestMapping(value = "/customerFavoriteCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onCustomerFavoriteCategories(@RequestParam(value = "customerId") Integer customerId) {
		logger.debug("Inside onCustomerFavoriteCategories method");
		return customerPreferenceHelper.onCustomerFavoriteCategories(customerId);
	}

	/**
	 * 
	 * This method delete the category details which are all unliked by customer
	 */
	@RequestMapping(value = "/unFavoriteCategory", method = RequestMethod.POST, produces = "application/json")
	public ResponseData onUnFavoriteCategory(@RequestBody FavoriteCategoryDTO favoriteCategoryDTO) {
		logger.debug("Inside unFavoriteCategory method");
		return customerPreferenceHelper.onUnFavoriteCategory(favoriteCategoryDTO);
	}

	/**
	 * 
	 * Display customer prefered categories
	 * 
	 */
	@RequestMapping(value = "/findByCustomer", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onFindPreferenceByCustomer(@RequestParam(value = "customerId") Integer customerId) {
		logger.debug("Inside findPreferenceByCustomer method");
		return customerPreferenceHelper.onCustomerPreferedCategories(customerId);
	}
}
