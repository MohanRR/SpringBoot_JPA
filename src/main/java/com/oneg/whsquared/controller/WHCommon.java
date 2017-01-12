/**
 * 
 */
package com.oneg.whsquared.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oneg.whsquared.entity.CustomerFavoriteCategory;
import com.oneg.whsquared.entity.CustomerFavoriteVendor;
import com.oneg.whsquared.entity.CustomerNotification;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventCategory;
import com.oneg.whsquared.entity.VendorCategory;
import com.oneg.whsquared.repository.CustomerFavoriteCategoryRepository;
import com.oneg.whsquared.repository.CustomerFavoriteVendorRepository;
import com.oneg.whsquared.repository.CustomerNotificationRepository;
import com.oneg.whsquared.repository.EventCategoryRepository;
import com.oneg.whsquared.repository.VendorCategoryRepository;

/**
 * @author Anbukkani G
 * 
 */
@Component
public class WHCommon {
	@Autowired
	private EventCategoryRepository eventCategoryRepository;

	@Autowired
	private VendorCategoryRepository vendorCategoryRepository;

	@Autowired
	private CustomerNotificationRepository customerNotificationRepository;

	@Autowired
	private CustomerFavoriteCategoryRepository customerFavoriteCategoryRepository;

	@Autowired
	private CustomerFavoriteVendorRepository customerFavoriteVendorRepository;

	public void deleteEventCategories(List<EventCategory> eventCategories) {
		for (EventCategory eventCategory : eventCategories) {
			eventCategoryRepository.delete(eventCategory);
		}
	}

	public void deleteCustomerFavoriteCategories(List<CustomerFavoriteCategory> customerFavoriteCategories) {
		for (CustomerFavoriteCategory customerFavoriteCategory : customerFavoriteCategories) {
			customerFavoriteCategoryRepository.delete(customerFavoriteCategory);
		}
	}

	public void deleteCustomerFavoriteVendors(List<CustomerFavoriteVendor> customerFavoriteVendors) {
		for (CustomerFavoriteVendor customerFavoriteVendor : customerFavoriteVendors) {
			customerFavoriteVendorRepository.delete(customerFavoriteVendor);
		}
	}

	public void deleteVendorCategories(List<VendorCategory> vendorCategories) {
		for (VendorCategory vendorCategory : vendorCategories) {
			vendorCategoryRepository.delete(vendorCategory);
		}
	}

	/**
	 * @param event
	 */
	private void deleteEvent(Event event) {
		List<CustomerNotification> customerNotifications = event.getCustomerNotificationList();
		for (CustomerNotification customerNotification : customerNotifications) {

			customerNotificationRepository.delete(customerNotification);
		}
	}
}
