/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.oneg.whsquared.controller.BaseDependecy;
import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.CustomerFavoriteEvents;
import com.oneg.whsquared.entity.CustomerFavoriteVendor;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventCategory;
import com.oneg.whsquared.entity.EventCategoryModal;
import com.oneg.whsquared.entity.EventDetail;
import com.oneg.whsquared.entity.Preference;

/**
 * @author arivu
 * 
 */
@Component
public class CustomerEventHelper extends BaseDependecy {

	public ResponseData onCustomerFavoriteEvent(FavouriteEvent favoriteEvent) {
		if (RestValidation.validateForInteger(favoriteEvent.getCustomerId()) != 0) {
			Customer customer = customerRepository.findOne(favoriteEvent.getCustomerId());
			if (customer != null) {
				Event event = eventRepository.findOne(favoriteEvent.getEventId());
				if (event != null) {
					CustomerFavoriteEvents favoriteEvents = customerFavoriteRepository.findByEventAndCustomerId(event, customer);
					List<EventCategory> eventCategories = eventCategoryRepository.findByEventId(event.getId());
					if (favoriteEvents == null) {
						customerFavoriteRepository.save(new CustomerFavoriteEvents(event, customer));
						if (!eventCategories.isEmpty()) {
							for (EventCategory eventCategory : eventCategories) {
								if (eventCategory != null) {
									preferenceRepository.save(new Preference(eventCategory.getEventCategory(), customer));
								}
							}
						}
						return RestMessage.onSuccess();
					} else {
						return RestMessage.onValidationErrorMessage("Customer prefered already");
					}
				} else {
					return RestMessage.onValidationErrorMessage("Event not found");
				}
			} else {
				return RestMessage.onValidationErrorMessage("Customer not found");
			}
		}
		return RestMessage.onValidationErrorMessage("Customer Id is empty");
	}

	public ResponseData onCustomerUnFavoriteEvent(FavouriteEvent favoriteEvent) {
		if (favoriteEvent.getCustomerId() != 0) {
			Customer customer = customerRepository.findOne(favoriteEvent.getCustomerId());
			if (customer != null) {
				Event event = eventRepository.findOne(favoriteEvent.getEventId());
				if (event != null) {
					CustomerFavoriteEvents favoriteEvents = customerFavoriteRepository.findByEventAndCustomerId(event, customer);
					if (favoriteEvents != null) {
						customerFavoriteRepository.deleteByEventAndCustomerId(event, customer);
						return RestMessage.onSuccess();
					} else {
						return RestMessage.onValidationErrorMessage("No event found for this customer");
					}
				} else {
					return RestMessage.onValidationErrorMessage("Event not found");
				}
			} else {
				return RestMessage.onValidationErrorMessage("Customer not found");
			}
		}
		return RestMessage.onValidationErrorMessage("Customer Id is empty");
	}

	public ResponseData constructCustomerFavoriteEvents(Integer customerId) {
		Customer customerFromDb = customerRepository.findOne(customerId);
		List<CustomerFavouriteDTO> customerFavouriteDTOs = new ArrayList<>();
		List<CustomerFavoriteEvents> customerFavoriteEvents = customerFavoriteRepository.findByCustomerId(customerFromDb);
		if (customerFavoriteEvents != null && !customerFavoriteEvents.isEmpty()) {
			for (CustomerFavoriteEvents favoriteEvents : customerFavoriteEvents) {
				List<EventCategoryModal> eventCategories = eventCategoryRepository.getCategoryByEvent(favoriteEvents.getEvent());
				EventDetail eventDetail = eventDetailRepository.findByEventId(favoriteEvents.getEvent());
				List<String> categoryNames = new ArrayList<String>();
				for (EventCategoryModal category : eventCategories) {
					if (category.getName() != null) {
						categoryNames.add(category.getName());
					}
				}
				customerFavouriteDTOs.add(new CustomerFavouriteDTO(eventDetail.getEventId().getId(), StringUtils.join(categoryNames, ", "), favoriteEvents.getEvent().getName(),
						"", eventDetail.getBackgroundUrl()));
			}
			return RestMessage.onSuccessMessage(customerFavouriteDTOs);
		} else {
			return RestMessage.onValidationErrorMessage("No Record Found");
		}
	}

	public ResponseData constructCustomerFavoriteVendors(Integer customerId) {
		Customer customerFromDb = customerRepository.findOne(customerId);
		List<FavoriteVendorDTO> customerFavouriteDTOs = new ArrayList<FavoriteVendorDTO>();
		List<CustomerFavoriteVendor> customerFavoriteVendors = customerFavoriteVendorRepository.findByCustomerId(customerFromDb);
		if (customerFavoriteVendors != null && !customerFavoriteVendors.isEmpty()) {
			for (CustomerFavoriteVendor customerFavoriteVendor : customerFavoriteVendors) {
				customerFavouriteDTOs.add(new FavoriteVendorDTO(customerFavoriteVendor.getVendorId().getId(), customerFavoriteVendor.getVendorId().getName(),
						customerFavoriteVendor.getVendorId().getVendorDetail().getLogoUrl(), customerFavoriteVendor.getVendorId().getVendorDetail().getBackgroundUrl()));
			}
			return RestMessage.onSuccessMessage(customerFavouriteDTOs);
		} else {
			return RestMessage.onValidationErrorMessage("No Record Found");
		}
	}
}
