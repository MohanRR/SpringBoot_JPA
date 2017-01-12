/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.oneg.whsquared.controller.BaseDependecy;
import com.oneg.whsquared.entity.Category;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventDetail;
import com.oneg.whsquared.entity.VendorDetail;

/**
 * @author arivu
 * 
 */
@Component
public class EventSearchHelper extends BaseDependecy {

	public List<SearchResultDTO> constructSearchResults(String searchTerm) {
		List<SearchResultDTO> searchResultDTOs = new ArrayList<SearchResultDTO>();
		List<EventDetail> eventDetailsForPlaces = new ArrayList<EventDetail>();
		List<EventListDTO> eventDetailDTOs = new ArrayList<>();
		List<EventListDTO> eventDetailDTOsForPlaces = new ArrayList<>();
		SearchResultDTO searchResultDTO = new SearchResultDTO();
		List<EventDetail> eventDetailsForEvents = eventDetailRepository.searchFromEventDetails(searchTerm);
		if (eventDetailsForEvents != null && !eventDetailsForEvents.isEmpty()) {
			eventDetailDTOs = helper.constructListOfEvents(eventDetailsForEvents);
		}

		List<Event> events = eventAddressRepository.getEventByAddress(searchTerm);
		if (events != null && !events.isEmpty()) {
			eventDetailsForPlaces = eventDetailRepository.findByEvents(events);
			eventDetailDTOsForPlaces = helper.constructListOfEvents(eventDetailsForPlaces);
		}
		List<EventListDTO> union = helper.removeDublicates(eventDetailDTOs, eventDetailDTOsForPlaces);

		searchResultDTO.setEventDetails(union);
		// Get Category
		List<Category> categories = categoryRepository.searchByNameAndDesc(searchTerm);
		if (categories != null) {
			searchResultDTO.setCategoryDetails(helper.constructListOfCategory(categories));
		}

		// Vendor
		List<VendorDetail> vendorDetails = vendorDetailRepository.searchFromVendorDetails(searchTerm);
		searchResultDTO.setVendorDetailDTOs(helper.constructListOfVendors(vendorDetails));
		searchResultDTOs.add(searchResultDTO);
		return searchResultDTOs;
	}
}
