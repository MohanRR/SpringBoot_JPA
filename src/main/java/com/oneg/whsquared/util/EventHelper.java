/**
 * 
 */
package com.oneg.whsquared.util;

import java.math.BigDecimal;
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
public class EventHelper extends BaseDependecy {

	public ResponseData onAllEvents(Integer page, Integer size) {
		Page<EventDetail> eventDetails = eventDetailRepository.getAllActiveEvents(new PageRequest(page, size));
		if (eventDetails != null && eventDetails.getContent().size() > 0) {
			return RestMessage.onSuccessMessage(helper.constructEventListResponse(eventDetails));
		} else {
			return RestMessage.onValidationErrorMessage("No event found");
		}
	}

	public ResponseData onTrendingEvents(Integer page, Integer size) {
		Page<EventDetail> eventDetails = eventDetailRepository.getAllActiveEvents(new PageRequest(page, size));
		if (eventDetails != null && eventDetails.getContent().size() > 0) {
			return RestMessage.onSuccessMessage(helper.constructEventListResponse(eventDetails));
		} else {
			return RestMessage.onValidationErrorMessage("No event found");
		}
	}

	public ResponseData onEventsByDate(String date, Integer page, Integer size) {
		Page<EventDetail> eventDetails = eventDetailRepository.getAllActiveEventsByDate(DateUtils.formatDate(date), new PageRequest(page, size));
		if (eventDetails != null && eventDetails.getContent().size() > 0) {
			return RestMessage.onSuccessMessage(helper.constructEventListResponse(eventDetails));
		} else {
			return RestMessage.onValidationErrorMessage("No event found for date");
		}
	}

	public ResponseData onEventsByLocation(String city, Integer state, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		List<Event> events = eventAddressRepository.getEventDataByAddress(state, city);
		if (events != null && events.size() > 0) {
			eventDetails = eventDetailRepository.findByEvents(events, new PageRequest(page, size));
		}
		if (eventDetails != null && eventDetails.getContent().size() > 0) {
			return RestMessage.onSuccessMessage(helper.constructEventListResponse(eventDetails));
		} else {
			return RestMessage.onValidationErrorMessage("No event found for location");
		}
	}

	public ResponseData onEventsByPrice(BigDecimal startRange, BigDecimal endRange, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		List<Event> events = rateCardRepository.getEventDataByAmountRange(startRange, endRange);
		if (events != null && events.size() > 0) {
			eventDetails = eventDetailRepository.findByEvents(events, new PageRequest(page, size));
		}
		if (eventDetails != null && eventDetails.getContent().size() > 0) {
			return RestMessage.onSuccessMessage(helper.constructEventListResponse(eventDetails));
		} else {
			return RestMessage.onValidationErrorMessage("No event found for price range");
		}
	}

	public ResponseData onTodaysEvents(Integer page, Integer size) {
		Date today = new Date();
		Page<EventDetail> eventDetails = eventDetailRepository.getAllActiveTodaysEvents(new PageRequest(page, size));
		if (eventDetails != null && eventDetails.getContent().size() > 0) {
			List<EventDetail> eventDetailsToShow = helper.checkAllConditionsToShowEvents(eventDetails.getContent(), today);
			return helper.constructEventListResponse(eventDetailsToShow);
		} else {
			return RestMessage.onValidationErrorMessage("No Event Found");
		}
	}

	public ResponseData onTodaysEventsByLocation(String city, Integer state, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		Date today = new Date();
		List<Event> events = eventAddressRepository.getEventDataByAddressAndTodayDate(state, city);
		if (events != null && events.size() > 0) {
			eventDetails = eventDetailRepository.findByEvents(events, new PageRequest(page, size));
		}
		if (eventDetails != null && eventDetails.getContent().size() > 0) {
			List<EventDetail> eventDetailsToShow = helper.checkAllConditionsToShowEvents(eventDetails.getContent(), today);
			return helper.constructEventListResponse(eventDetailsToShow);
		} else {
			return RestMessage.onValidationErrorMessage("No Event Found");
		}
	}

	public ResponseData onTodaysEventsByPrice(BigDecimal startRange, BigDecimal endRange, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		Date today = new Date();
		List<Event> events = rateCardRepository.getEventDataByAmountRangeAndToday(startRange, endRange);
		if (events != null && events.size() > 0) {
			eventDetails = eventDetailRepository.findByEvents(events, new PageRequest(page, size));
		}
		if (eventDetails != null && eventDetails.getContent().size() > 0) {
			List<EventDetail> eventDetailsToShow = helper.checkAllConditionsToShowEvents(eventDetails.getContent(), today);
			return helper.constructEventListResponse(eventDetailsToShow);
		} else {
			return RestMessage.onValidationErrorMessage("No Event Found");
		}
	}

	public ResponseData onEventsOrderByDate(Integer page, Integer size) {
		Page<EventDetail> eventDetails = eventDetailRepository.getAllActiveEventsAndDateOrder(new PageRequest(page, size));
		if (eventDetails != null && eventDetails.getContent().size() > 0) {
			return RestMessage.onSuccessMessage(helper.constructEventListOnDateOrderResponse(eventDetails));
		} else {
			return RestMessage.onValidationErrorMessage("No Event found");
		}
	}

	public ResponseData onEventsDateOrderByDate(String date, Integer page, Integer size) {
		Page<EventDetail> eventDetails = eventDetailRepository.getAllActiveEventsByDateAndDateOrder(DateUtils.formatDate(date), new PageRequest(page, size));
		if (eventDetails != null && eventDetails.getContent().size() > 0) {
			return RestMessage.onSuccessMessage(helper.constructEventListOnDateOrderResponse(eventDetails));
		} else {
			return RestMessage.onValidationErrorMessage("No event found for date");
		}

	}

	public ResponseData onEventsLocationByDateOrder(String city, Integer state, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		List<Event> events = eventAddressRepository.getEventDataByAddress(state, city);
		if (events != null && events.size() > 0) {
			eventDetails = eventDetailRepository.findByEventsAndDateOrder(events, new PageRequest(page, size));
		}
		if (eventDetails != null && eventDetails.getContent().size() > 0) {
			return RestMessage.onSuccessMessage(helper.constructEventListOnDateOrderResponse(eventDetails));
		} else {
			return RestMessage.onValidationErrorMessage("No event found for location");
		}
	}
	
	public ResponseData onEventsByPriceDateOrder(BigDecimal startRange, BigDecimal endRange, Integer page, Integer size) {
		Page<EventDetail> eventDetails = null;
		List<Event> events = rateCardRepository.getEventDataByAmountRange(startRange, endRange);
		if (events != null && events.size() > 0) {
			eventDetails = eventDetailRepository.findByEventsAndDateOrder(events, new PageRequest(page, size));
		}
		if (eventDetails != null && eventDetails.getContent().size() > 0) {
			return RestMessage.onSuccessMessage(helper.constructEventListOnDateOrderResponse(eventDetails));
		} else {
			return RestMessage.onValidationErrorMessage("No event found for price range");
		}
	}
	
}
