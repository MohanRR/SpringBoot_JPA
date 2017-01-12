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
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.util.FavouriteVendor;
import com.oneg.whsquared.util.ResponseData;
import com.oneg.whsquared.util.VendorHelper;

/**
 * @author arivu
 * 
 */
@RestController
@RequestMapping("/api/favouriteVendors/")
public class RestVendorFavoriteController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private VendorHelper vendorHelper;

	/**
	 * Favorite vendor by customer
	 */
	@RequestMapping(value = "/favouriteVendor", method = RequestMethod.POST, produces = "application/json")
	public ResponseData onFavouriteVendor(@RequestBody FavouriteVendor favoriteVendor) {
		logger.debug("Inside favouriteVendor method");
		return vendorHelper.onVendorFavorite(favoriteVendor);
	}

	/**
	 * Unfavorite vendor by customer
	 */
	@RequestMapping(value = "/unFavouriteVendor", method = RequestMethod.POST, produces = "application/json")
	public ResponseData unFavouriteVendor(@RequestBody FavouriteVendor favoriteVendor) {
		logger.debug("Inside unFavouriteVendor method");
		return vendorHelper.onVendorUnfavorite(favoriteVendor);
	}
}
