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

import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.Reminder;
import com.oneg.whsquared.repository.CustomerRepository;
import com.oneg.whsquared.repository.EventRepository;
import com.oneg.whsquared.repository.ReminderRepository;
import com.oneg.whsquared.util.DateUtils;
import com.oneg.whsquared.util.Helper;
import com.oneg.whsquared.util.ReminderDTO;
import com.oneg.whsquared.util.ResponseData;
import com.oneg.whsquared.util.RestMessage;

/**
 * @author arivu
 * 
 *         Reminder settings
 * 
 *         This API is to add reminder, update reminder , delete and view
 *         reminder by customer
 * 
 */
@RestController
@RequestMapping("/api/reminder/")
public class RestReminderController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Helper helper;

	@Autowired
	private ReminderRepository reminderRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * 
	 * @param customerId
	 * @param page
	 * @param size
	 * @return
	 * 
	 *         This method constructs the reminder that are added by the
	 *         customer
	 * 
	 */
	@RequestMapping(value = "/findRemindersByCustomer", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onFindRemindersByCustomer(@RequestParam(name = "customerId") Integer customerId,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
		logger.debug("Inside onFindRemindersByCustomer method");
		if (customerId != null) {
			Customer customer = customerRepository.findOne(customerId);
			if (customer != null) {
				return helper.constructReminders(reminderRepository.findByCustomer(customer));
			} else {
				return RestMessage.onValidationErrorMessage("Cutomer not found");
			}
		} else {
			return RestMessage.onValidationErrorMessage("CutomerId is empty");
		}
	}

	/**
	 * 
	 * @param reminderDTO
	 * @return
	 * 
	 *         Add Reminder for particular event and date
	 */
	@RequestMapping(value = "/addReminder", method = RequestMethod.POST, produces = "application/json")
	public ResponseData onAddReminder(@RequestBody ReminderDTO reminderDTO) {
		logger.debug("Inside onAddReminder method");
		Customer customer = customerRepository.findOne(reminderDTO.getCustomerId());
		Event event = eventRepository.findOne(reminderDTO.getEventId());
		if (customer != null) {
			if (event != null) {
				Reminder reminder = reminderRepository.findByCustomerAndEvent(customer, event);
				if (reminder == null) {
					reminderRepository.save(new Reminder(customer, event, DateUtils.convertStringToDate(reminderDTO.getDateTime()), reminderDTO.isSendMailReminder(), reminderDTO
							.isSendPushReminder()));
					return RestMessage.onSuccess("Reminder added successfully");
				} else {
					reminder.setEvent(event);
					reminder.setCustomer(customer);
					reminder.setReminderDateTime(DateUtils.convertStringToDate(reminderDTO.getDateTime()));
					reminder.setSendMailReminder(reminderDTO.isSendMailReminder());
					reminder.setSendPushReminder(reminderDTO.isSendPushReminder());
					reminderRepository.save(reminder);
					return RestMessage.onSuccess("Reminder updated successfully");
				}
			} else {
				return RestMessage.onValidationErrorMessage("Event not found");
			}
		} else {
			return RestMessage.onValidationErrorMessage("Customer not found");
		}
	}

	/**
	 * 
	 * @param customerId
	 * @param eventId
	 * @return
	 * 
	 *         Delete Reminder for customer and event
	 * 
	 */
	@RequestMapping(value = "/deleteReminder", method = RequestMethod.GET, produces = "application/json")
	public ResponseData onDeleteReminder(@RequestParam(name = "customerId") Integer customerId, @RequestParam(name = "eventId") Integer eventId) {
		logger.debug("Inside onDeleteReminder method");
		Customer customer = customerRepository.findOne(customerId);
		Event event = eventRepository.findOne(eventId);
		if (customer != null) {
			if (event != null) {
				Reminder reminder = reminderRepository.findByCustomerAndEvent(customer, event);
				if (reminder != null) {
					reminderRepository.delete(reminder);
					return RestMessage.onSuccess("Reminder deleted successfully");
				} else {
					return RestMessage.onValidationErrorMessage("No reminder found for this event");
				}
			} else {
				return RestMessage.onValidationErrorMessage("Event not found");
			}
		} else {
			return RestMessage.onValidationErrorMessage("Customer not found");
		}
	}
}
