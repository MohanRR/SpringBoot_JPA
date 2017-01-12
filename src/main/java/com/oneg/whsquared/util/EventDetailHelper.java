/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.oneg.whsquared.controller.BaseDependecy;
import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.CustomerFavoriteEvents;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventAddress;
import com.oneg.whsquared.entity.EventAgenda;
import com.oneg.whsquared.entity.EventCategoryModal;
import com.oneg.whsquared.entity.EventDetail;
import com.oneg.whsquared.entity.RateCard;
import com.oneg.whsquared.entity.Reminder;

/**
 * @author arivu
 * 
 */
@Component
public class EventDetailHelper extends BaseDependecy {

	public ResponseData onEventDetails(Integer eventId, Integer customerId) {
		Event event = eventRepository.findOne(eventId);
		Customer customer = customerRepository.findOne(customerId);
		if (customer != null) {
			if (event != null) {
				EventDetail eventDetail = eventDetailRepository.findByEventId(event);
				if (eventDetail != null && eventDetail.getId() > 0) {
					return RestMessage.onSuccessMessage(constructEventDetails(eventDetail, customer));
				} else {
					return RestMessage.onValidationErrorMessage("No event details found.");
				}
			} else {
				return RestMessage.onValidationErrorMessage("No event found.");
			}
		} else {
			return RestMessage.onValidationErrorMessage("Customer not found.");
		}

	}

	public EventDetailDTO constructEventDetails(EventDetail eventDetail, Customer customer) {
		EventDetailDTO eventDetailsDTO = new EventDetailDTO();
		eventDetailsDTO.setEventId(eventDetail.getEventId().getId());
		eventDetailsDTO.setEventName(eventDetail.getEventId().getName());
		eventDetailsDTO.setStartDate(DateUtils.formatDateToString(eventDetail.getEventId().getEventCalender().getStartDate()));
		eventDetailsDTO.setEndDate(DateUtils.formatDateToString(eventDetail.getEventId().getEventCalender().getEndDate()));
		eventDetailsDTO.setStartTime(DateUtils.formatTimeToString(eventDetail.getEventId().getEventCalender().getStartTime()));
		eventDetailsDTO.setEndTime(DateUtils.formatTimeToString(eventDetail.getEventId().getEventCalender().getEndTime()));
		eventDetailsDTO.setEventBackground("");
		eventDetailsDTO.setEventBackgroundUrl(eventDetail.getBackgroundUrl());
		List<EventAddress> eventAddress = eventAddressRepository.findByEvent(eventDetail.getEventId());
		eventDetailsDTO.setAddressDTOs(helper.constructEventAddressDTO(eventAddress));
		List<EventAgenda> eventAgendas = eventAgendaRepository.findByEventId(eventDetail.getEventId());
		if (eventAgendas != null && !eventAgendas.isEmpty()) {
			List<EventAgendaDTO> agendaDTOs = new ArrayList<EventAgendaDTO>();
			for (EventAgenda eventAgenda : eventAgendas) {
				agendaDTOs.add(new EventAgendaDTO(eventAgenda.getTitle(), eventAgenda.getDayAndTime(), eventAgenda.getOffers(), "", eventAgenda.getImageUrl()));
			}
			eventDetailsDTO.setAgendaDTOs(agendaDTOs);
		}
		List<RateCard> rateCards = rateCardRepository.findByEventId(eventDetail.getEventId());
		if (rateCards != null && !rateCards.isEmpty()) {
			List<EventRateCard> eventRateCards = new ArrayList<EventRateCard>();
			for (RateCard rateCard : rateCards) {
				eventRateCards.add(new EventRateCard(rateCard.getName(), rateCard.getAmount(), rateCard.getCurrency()));
			}
			eventDetailsDTO.setEventRateCards(eventRateCards);
		}
		List<CustomerFavoriteEvents> customerFavoriteEvents = customerFavoriteRepository.findByEvent(eventDetail.getEventId());
		if (customerFavoriteEvents != null && !customerFavoriteEvents.isEmpty()) {
			eventDetailsDTO.setEventStatus(true);
		} else {
			eventDetailsDTO.setEventStatus(false);
		}
		Reminder reminder = reminderRepository.findByCustomerAndEvent(customer, eventDetail.getEventId());
		if (reminder != null) {
			eventDetailsDTO.setReminderDTOs(new ReminderDTO(reminder.getCustomer().getId(), reminder.getEvent().getId(), DateUtils.convertDateToString(reminder
					.getReminderDateTime()), reminder.isSendMailReminder(), reminder.isSendPushReminder(), reminder.getEvent().getName()));
		}
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		List<EventCategoryModal> eventCategories = eventCategoryRepository.getCategoryByEvent(eventDetail.getEventId());
		for (EventCategoryModal category : eventCategories) {
			categoryDTOs.add(new CategoryDTO(category.getId(), category.getName(), "", "", category.getBackgroundUrl(), category.getBannerUrl()));
		}
		eventDetailsDTO.setCategoryDTOs(categoryDTOs);
		return eventDetailsDTO;
	}
}
