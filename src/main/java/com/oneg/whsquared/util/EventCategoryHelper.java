/**
 * 
 */
package com.oneg.whsquared.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.oneg.whsquared.controller.BaseDependecy;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventDetail;

/**
 * @author arivu
 * 
 */
@Component
public class EventCategoryHelper extends BaseDependecy {

	public ResponseData onAllEventsByCategory(Integer categoryId, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		List<Event> events = eventCategoryRepository.getEventByCategory(categoryId);
		if (events != null && events.size() > 0) {
			eventDetails = eventDetailRepository.findByEvents(events, new PageRequest(page, size));
			if (eventDetails != null && eventDetails.getContent().size() > 0) {
				return RestMessage.onSuccessMessage(helper.constructEventListResponse(eventDetails));
			} else {
				return RestMessage.onValidationErrorMessage("No event found");
			}
		}
		return RestMessage.onValidationErrorMessage("No event found");
	}

	public ResponseData onAllEventsDateByCategory(Integer categoryId, String date, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		List<Event> events = eventCategoryRepository.getEventByCategoryAndDate(categoryId, DateUtils.formatDate(date));
		if (events != null && events.size() > 0) {
			eventDetails = eventDetailRepository.findByEvents(events, new PageRequest(page, size));
			if (eventDetails != null && eventDetails.getContent().size() > 0) {
				return RestMessage.onSuccessMessage(helper.constructEventListResponse(eventDetails));
			} else {
				return RestMessage.onValidationErrorMessage("No event found for date");
			}
		} else {
			return RestMessage.onValidationErrorMessage("No event found for this category");
		}
	}

	public ResponseData onAllEventsByLocation(Integer categoryId, String city, Integer state, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		List<Event> categoryEvents = eventCategoryRepository.getEventByCategory(categoryId);
		if (categoryEvents != null) {
			List<Event> events = new ArrayList<Event>();
			events = eventAddressRepository.getEventDataByAddressAndEvents(state, city, categoryEvents);
			if (events != null && events.size() > 0) {
				eventDetails = eventDetailRepository.findByEvents(events, new PageRequest(page, size));
				if (eventDetails != null && eventDetails.getContent().size() > 0) {
					return RestMessage.onSuccessMessage(helper.constructEventListResponse(eventDetails));
				} else {
					return RestMessage.onValidationErrorMessage("No event found for location");
				}
			} else {
				return RestMessage.onValidationErrorMessage("No event found for this category");
			}
		} else {
			return RestMessage.onValidationErrorMessage("No event found for this category");
		}
	}

	public ResponseData onAllEventsByPrice(Integer categoryId, BigDecimal startRange, BigDecimal endRange, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		List<Event> categoryEvents = eventCategoryRepository.getEventByCategory(categoryId);
		if (categoryEvents != null && !categoryEvents.isEmpty()) {
			List<Event> events = rateCardRepository.getEventDataByAmountRangeAndEvents(startRange, endRange, categoryEvents);
			if (events != null && events.size() > 0) {
				eventDetails = eventDetailRepository.findByEvents(events, new PageRequest(page, size));
				if (eventDetails != null && eventDetails.getContent().size() > 0) {
					return RestMessage.onSuccessMessage(helper.constructEventListResponse(eventDetails));
				} else {
					return RestMessage.onValidationErrorMessage("No event found for price range");
				}
			} else {
				return RestMessage.onValidationErrorMessage("No Event Found");
			}
		} else {
			return RestMessage.onValidationErrorMessage("No event found for this category");
		}

	}

	public ResponseData onAllTodaysEvents(Integer categoryId, Integer page, Integer size) {
		Date today = new Date();
		List<Event> categoryEvents = eventCategoryRepository.getEventByCategory(categoryId);
		if (categoryEvents != null && !categoryEvents.isEmpty()) {
			Page<EventDetail> eventDetails = eventDetailRepository.getAllActiveEventsByEventsToday(categoryEvents, new PageRequest(page, size));
			if (eventDetails != null && eventDetails.getContent().size() > 0) {
				List<EventDetail> eventDetailsToShow = helper.checkAllConditionsToShowEvents(eventDetails.getContent(), today);
				return helper.constructEventListResponse(eventDetailsToShow);
			} else {
				return RestMessage.onValidationErrorMessage("No Event Found");
			}
		} else {
			return RestMessage.onValidationErrorMessage("No event found for this category");
		}
	}

	public ResponseData onAllTodaysEventsByLocation(Integer categoryId, String city, Integer state, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		Date today = new Date();
		List<Event> categoryEvents = eventCategoryRepository.getEventByCategory(categoryId);
		if (categoryEvents != null && !categoryEvents.isEmpty()) {
			List<Event> events = eventAddressRepository.getEventDataByAddressAndDateAndEventsToday(state, city, categoryEvents);
			if (events != null && events.size() > 0) {
				eventDetails = eventDetailRepository.findByEvents(events, new PageRequest(page, size));
				if (eventDetails != null && eventDetails.getContent().size() > 0) {
					List<EventDetail> eventDetailsToShow = helper.checkAllConditionsToShowEvents(eventDetails.getContent(), today);
					return helper.constructEventListResponse(eventDetailsToShow);
				} else {
					return RestMessage.onValidationErrorMessage("No Event Found");
				}
			} else {
				return RestMessage.onValidationErrorMessage("No event found for this category");
			}
		} else {
			return RestMessage.onValidationErrorMessage("No event found for this category");
		}
	}

	public ResponseData onAllTodaysEventsByPrice(Integer categoryId, BigDecimal startRange, BigDecimal endRange, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		Date today = new Date();
		List<Event> categoryEvents = eventCategoryRepository.getEventByCategory(categoryId);
		if (categoryEvents != null && !categoryEvents.isEmpty()) {
			List<Event> events = rateCardRepository.getEventDataByAmountRangeAndDateAndEventsToday(startRange, endRange, categoryEvents);
			if (events != null && events.size() > 0) {
				eventDetails = eventDetailRepository.findByEvents(events, new PageRequest(page, size));
				if (eventDetails != null && eventDetails.getContent().size() > 0) {
					List<EventDetail> eventDetailsToShow = helper.checkAllConditionsToShowEvents(eventDetails.getContent(), today);
					return helper.constructEventListResponse(eventDetailsToShow);
				} else {
					return RestMessage.onValidationErrorMessage("No event found for price range");
				}
			} else {
				return RestMessage.onValidationErrorMessage("No Event Found");
			}
		} else {
			return RestMessage.onValidationErrorMessage("No event found for this category");
		}
	}

	public ResponseData onAllEventsByDateOrder(Integer categoryId, Integer page, Integer size) {
		List<Event> categoryEvents = eventCategoryRepository.getEventByCategory(categoryId);
		if (categoryEvents != null && !categoryEvents.isEmpty()) {
			Page<EventDetail> eventDetails = eventDetailRepository.getAllActiveEventsAndDateOrderAndEvents(categoryEvents, new PageRequest(page, size));
			if (eventDetails != null && eventDetails.getContent().size() > 0) {
				return RestMessage.onSuccessMessage(helper.constructEventListOnDateOrderResponse(eventDetails));
			} else {
				return RestMessage.onValidationErrorMessage("No event found");
			}
		} else {
			return RestMessage.onValidationErrorMessage("No event found for this category");
		}
	}

	public ResponseData onAllEventsDateOnDateOrderByCategory(Integer categoryId, String date, Integer page, Integer size) {
		List<Event> categoryEvents = eventCategoryRepository.getEventByCategory(categoryId);
		if (categoryEvents != null && !categoryEvents.isEmpty()) {
			Page<EventDetail> eventDetails = eventDetailRepository.getAllActiveEventsByDateAndDateOrderAndEvents(categoryEvents, DateUtils.formatDate(date), new PageRequest(page,
					size));
			if (eventDetails != null && eventDetails.hasContent() && eventDetails.getContent().size() > 0) {
				return RestMessage.onSuccessMessage(helper.constructEventListOnDateOrderResponse(eventDetails));
			} else {
				return RestMessage.onValidationErrorMessage("No event found for date");
			}
		} else {
			return RestMessage.onValidationErrorMessage("No event found for this category");
		}
	}

	public ResponseData onAllEventsLocationOnDateOrderByCategory(Integer categoryId, String city, Integer state, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		List<Event> categoryEvents = eventCategoryRepository.getEventByCategory(categoryId);
		if (categoryEvents != null && !categoryEvents.isEmpty()) {
			List<Event> events = eventAddressRepository.getEventDataByAddressAndEvents(state, city, categoryEvents);
			if (events != null && events.size() > 0) {
				eventDetails = eventDetailRepository.findByEventsAndDateOrder(events, new PageRequest(page, size));
				if (eventDetails != null && eventDetails.getContent().size() > 0) {
					return RestMessage.onSuccessMessage(helper.constructEventListOnDateOrderResponse(eventDetails));
				} else {
					return RestMessage.onValidationErrorMessage("No event found for location");
				}
			} else {
				return RestMessage.onValidationErrorMessage("No event found for this category");
			}
		} else {
			return RestMessage.onValidationErrorMessage("No event found for this category");
		}
	}

	public ResponseData onAllEventsPriceOnDateOrderByCategory(Integer categoryId, BigDecimal startRange, BigDecimal endRange, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		List<Event> categoryEvents = eventCategoryRepository.getEventByCategory(categoryId);
		if (categoryEvents != null && !categoryEvents.isEmpty()) {
			List<Event> events = rateCardRepository.getEventDataByAmountRangeAndEvents(startRange, endRange, categoryEvents);
			if (events != null && events.size() > 0) {
				eventDetails = eventDetailRepository.findByEventsAndDateOrder(events, new PageRequest(page, size));
				if (eventDetails != null && eventDetails.getContent().size() > 0) {
					return RestMessage.onSuccessMessage(helper.constructEventListOnDateOrderResponse(eventDetails));
				} else {
					return RestMessage.onValidationErrorMessage("No event found for price range");
				}
			} else {
				return RestMessage.onValidationErrorMessage("No event found for this category");
			}
		} else {
			return RestMessage.onValidationErrorMessage("No event found for this category");
		}
	}

}
