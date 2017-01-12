/**
 * 
 */
package com.oneg.whsquared.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.util.CategoryHelper;
import com.oneg.whsquared.util.PreferenceCategoryDTO;
import com.oneg.whsquared.util.ResponseData;

/**
 * @author arivu
 * 
 */
@RestController
@RequestMapping(value = "/api/category")
public class RestCategoryControlller {

	@Autowired
	private CategoryHelper categoryHelper;

	/**
	 * 
	 * 
	 * List all customer preferences
	 * 
	 */
	@RequestMapping(value = "listPreferenceCategoriesByCustomer", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onCustomerPreferences(@RequestParam(name = "customerId") Integer customerId,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		return categoryHelper.onCustomerPreferences(customerId, page, size);
	}

	/**
	 * 
	 * 
	 * Delete customer preferences based on customer and category id
	 * 
	 */
	@RequestMapping(value = "deleteCustomerPreferences", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onDeleteCustomerPreferences(@RequestParam(name = "customerId") Integer customerId, @RequestParam(name = "categoryId") Integer categoryId) {
		return categoryHelper.onDeleteCustomerPreferences(customerId, categoryId);
	}

	/**
	 * 
	 * 
	 * Add List of customer Preferences
	 */
	@RequestMapping(value = "savePreferenceCategories", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseData onSavePreferenceCategories(@RequestBody PreferenceCategoryDTO preferenceCategoryDTO) throws Exception {
		return categoryHelper.onAddCustomerPreferences(preferenceCategoryDTO);
	}

	/**
	 * 
	 * List all categories
	 */
	@RequestMapping(value = "listAllCategories", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllCategories(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		return categoryHelper.onListAllCategories(page, size);
	}

	/**
	 * List all vendor categories
	 */
	@RequestMapping(value = "listAllVendorCategories", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListAllVendorCategories(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		return categoryHelper.onListAllVendorCategories(page, size);
	}

	/**
	 * List vendors by category
	 */
	@RequestMapping(value = "listVendorsByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onListVendorsByCategory(@RequestParam(name = "categoryId", required = false) Integer categoryId,
			@RequestParam(name = "searchName", required = false) String searchName) {
		return categoryHelper.onVendorsByCategory(categoryId, searchName);
	}

	/**
	 * get customer category
	 */
	@RequestMapping(value = "findByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onFindByCategory(@RequestParam(name = "categoryId") Integer categoryId, @RequestParam(name = "customerId") Integer customerId) {
		return categoryHelper.onCustomerCategory(categoryId, customerId);
	}

}
