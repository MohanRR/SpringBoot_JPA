/**
 * 
 */
package com.oneg.whsquared.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import com.oneg.whsquared.controller.BaseDependecy;
import com.oneg.whsquared.entity.Category;
import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.CustomerFavoriteEvents;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventAddress;
import com.oneg.whsquared.entity.EventAgenda;
import com.oneg.whsquared.entity.EventCalender;
import com.oneg.whsquared.entity.EventCategoryModal;
import com.oneg.whsquared.entity.EventDetail;
import com.oneg.whsquared.entity.RateCard;
import com.oneg.whsquared.entity.Reminder;
import com.oneg.whsquared.entity.Vendor;
import com.oneg.whsquared.entity.VendorAddress;
import com.oneg.whsquared.entity.VendorDetail;
import com.oneg.whsquared.entity.WHAppVersion;
import com.oneg.whsquared.entity.WHVersionDetails;

/**
 * @author arivu
 * 
 */
@Component
public class Helper extends BaseDependecy {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final DecimalFormat phoneFormatD = new DecimalFormat("0000000000");
	public static final MessageFormat phoneFormatM = new MessageFormat("({0}) {1}-{2}");

	/**
	 * 
	 * @param customerFromDb
	 * @return
	 */
	public CustomerResponseData updateCustomerProfile(Customer customerFromDb, Device device) {
		CustomerResponseData responseData = new CustomerResponseData();
		UserDTO userDTO = new UserDTO();
		if (customerFromDb.getId() != null) {
			userDTO.setUser_id(customerFromDb.getId());
		} else {
			userDTO.setUser_id(0);
		}

		if (customerFromDb.getFirstName() != null && !customerFromDb.getFirstName().isEmpty() && customerFromDb.getLastName() != null && !customerFromDb.getLastName().isEmpty()) {
			userDTO.setName(customerFromDb.getFirstName() + " " + customerFromDb.getLastName());
		} else {
			userDTO.setName("");
		}

		if (customerFromDb.getFirstName() != null && !customerFromDb.getFirstName().isEmpty()) {
			userDTO.setFirst_name(customerFromDb.getFirstName());
		} else {
			userDTO.setFirst_name("");
		}

		if (customerFromDb.getLastName() != null && !customerFromDb.getLastName().isEmpty()) {
			userDTO.setLast_name(customerFromDb.getLastName());
		} else {
			userDTO.setLast_name("");
		}

		if (customerFromDb.getEmail() != null && !customerFromDb.getEmail().isEmpty()) {
			userDTO.setEmail_id(customerFromDb.getEmail());
		} else {
			userDTO.setEmail_id("");
		}

		userDTO.setImage("");
		if (customerFromDb.getProfileImageUrl() != null && !customerFromDb.getProfileImageUrl().isEmpty()) {
			userDTO.setProfile_image_url(customerFromDb.getProfileImageUrl());
		} else {
			userDTO.setProfile_image_url("");
		}

		if (customerFromDb.getDeviceId() != null && !customerFromDb.getDeviceId().isEmpty()) {
			userDTO.setDeviceId(customerFromDb.getDeviceId());
		} else {
			userDTO.setDeviceId("");
		}
		if (userDTO != null) {
			responseData.setResult(true);
			responseData.setMessage("Success");
			responseData.setData(userDTO);
			responseData.setToken(jwtUtil.generateToken(customerFromDb, device));
		}
		return responseData;
	}

	public ResponseData constructCustomerReminders(Customer customerFromDb) {
		List<ReminderDTO> reminderDTOs = new ArrayList<ReminderDTO>();
		List<Reminder> reminders = reminderRepository.findByCustomer(customerFromDb);
		if (reminders != null && !reminders.isEmpty()) {
			for (Reminder reminder : reminders) {
				reminderDTOs.add(new ReminderDTO(reminder.getCustomer().getId(), reminder.getEvent().getId(), DateUtils.convertDateToString(reminder.getReminderDateTime()),
						reminder.isSendMailReminder(), reminder.isSendPushReminder(), reminder.getEvent().getName()));
			}
			return RestMessage.onSuccessMessage(reminderDTOs);
		} else {
			return RestMessage.onValidationErrorMessage("No Record");
		}
	}

	public List<VendorDetailDTO> constructListOfVendors(List<VendorDetail> vendorDetails) {
		List<VendorDetailDTO> vendorDetailDTOs = new ArrayList<VendorDetailDTO>();
		for (VendorDetail vendorDetail : vendorDetails) {
			if (vendorDetail != null) {
				VendorDetailDTO vendorDetailDTO = new VendorDetailDTO();
				vendorDetailDTO.setVendorId(vendorDetail.getVendor().getId());
				vendorDetailDTO.setVendorName(vendorDetail.getVendor().getName());
				vendorDetailDTO.setVendorBackgroundUrl(vendorDetail.getBackgroundUrl());
				vendorDetailDTO.setVendorIconUrl(vendorDetail.getLogoUrl());
				vendorDetailDTO.setVendorPhoneNumber(removeSpecialChars(vendorDetail.getPhoneNumber()));
				vendorDetailDTO.setQuickLink1(vendorDetail.getQuicklink1());
				vendorDetailDTO.setQuickLink2(vendorDetail.getQuicklink2());
				vendorDetailDTO.setQuickLink3(vendorDetail.getQuicklink3());
				vendorDetailDTO.setBussinessHours(vendorDetail.getBusinessHours());
				vendorDetailDTOs.add(vendorDetailDTO);
			}
		}
		return vendorDetailDTOs;
	}

	public static String removeSpecialChars(String value) {
		return value.replaceAll("[^a-zA-Z0-9]", "");
	}

	public List<EventAddressDTO> constructEventAddressDTO(List<EventAddress> eventAddresses) {
		List<EventAddressDTO> eventAddressDTOs = new ArrayList<EventAddressDTO>();
		if (eventAddresses != null && eventAddresses.size() > 0) {
			for (EventAddress eventAddress : eventAddresses) {
				eventAddressDTOs.add(new EventAddressDTO(eventAddress.getAddress().getFullAddress(), eventAddress.getAddress().getLat(), eventAddress.getAddress().getLong1()));
			}
		}
		return eventAddressDTOs;
	}

	public ResponseData constructListOfEventDetails(List<EventDetail> eventDetails) {
		ResponseData responseData = new ResponseData();
		List<EventDetailDTO> eventDetailsDTOs = new ArrayList<EventDetailDTO>();
		for (EventDetail eventDetail : eventDetails) {
			EventDetailDTO eventDetailsDTO = new EventDetailDTO();
			eventDetailsDTO.setEventId(eventDetail.getEventId().getId());
			eventDetailsDTO.setEventName(eventDetail.getEventId().getName());
			eventDetailsDTO.setStartDate(DateUtils.formatDateToString(eventDetail.getEventId().getStartDate()));
			eventDetailsDTO.setEndDate(DateUtils.formatDateToString(eventDetail.getEventId().getEndDate()));
			eventDetailsDTO.setStartTime(DateUtils.formatTimeToString(eventDetail.getEventId().getStartTime()));
			eventDetailsDTO.setEndTime(DateUtils.formatTimeToString(eventDetail.getEventId().getEndTime()));
			// eventDetailsDTO.setEventLink(eventDetail.getUrlLink());
			eventDetailsDTO.setEventBackground("");
			eventDetailsDTO.setEventBackgroundUrl(eventDetail.getBackgroundUrl());
			// eventDetailsDTO.setPhoneNumber(eventDetail.getPhoneNumber());
			List<EventAddress> eventAddress = eventAddressRepository.findByEvent(eventDetail.getEventId());
			eventDetailsDTO.setAddressDTOs(constructEventAddressDTO(eventAddress));
			// eventDetailsDTO.setHappyHourMenu(eventDetail.getHappyHourMenu());
			// eventDetailsDTO.setDinnerMenu(eventDetail.getDinnerMenu());
			// eventDetailsDTO.setPhotoUrl(eventDetail.getPhotosUrl());
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
			eventDetailsDTOs.add(eventDetailsDTO);
		}
		responseData.setData(eventDetailsDTOs);
		responseData.setMessage("Success");
		responseData.setResult(true);
		return responseData;
	}

	public ResponseData constructEventListResponse(List<EventDetail> eventDetails) {
		List<EventListDTO> data = new ArrayList<EventListDTO>();
		for (EventDetail eventDetail : eventDetails) {
			EventListDTO eventListDTO = new EventListDTO();
			eventListDTO.setEventId(eventDetail.getEventId().getId());
			eventListDTO.setEventName(eventDetail.getEventId().getName());
			eventListDTO.setImageUrl(eventDetail.getBackgroundUrl());
			eventListDTO.setEventShortDescription(eventDetail.getEventId().getShortDescription());
			eventListDTO.setVendorName(eventDetail.getEventId().getVendorId().getName());
			eventListDTO.setEventDate(DateUtils.formatDateToString(eventDetail.getEventId().getStartDate()));
			eventListDTO.setEventTime(DateUtils.formatTimeToString(eventDetail.getEventId().getStartTime()));
			eventListDTO.setOfferTime(constructOfferTime(eventDetail.getEventId().getEventCalender().getStartTime(), eventDetail.getEventId().getEventCalender().getEndTime()));
			data.add(eventListDTO);
		}
		return RestMessage.onSuccessMessage(data);
	}

	public List<EventListDTO> constructListOfEvents(List<EventDetail> eventDetails) {
		List<EventListDTO> datas = new ArrayList<EventListDTO>();
		for (EventDetail eventDetail : eventDetails) {
			EventListDTO eventListDTO = new EventListDTO();
			eventListDTO.setEventId(eventDetail.getEventId().getId());
			eventListDTO.setEventName(eventDetail.getEventId().getName());
			eventListDTO.setImageUrl(eventDetail.getBackgroundUrl());
			eventListDTO.setVendorName(eventDetail.getEventId().getVendorId().getName());
			eventListDTO.setEventDate(DateUtils.formatDateToString(eventDetail.getEventId().getStartDate()));
			eventListDTO.setEventTime(DateUtils.formatTimeToString(eventDetail.getEventId().getStartTime()));
			eventListDTO.setEventShortDescription(eventDetail.getEventId().getShortDescription());
			eventListDTO.setOfferTime(constructOfferTime(eventDetail.getEventId().getEventCalender().getStartTime(), eventDetail.getEventId().getEventCalender().getEndTime()));
			datas.add(eventListDTO);
		}
		return datas;
	}

	public ResponseData constructReminders(List<Reminder> reminders) {
		List<ReminderDTO> reminderDTOs = new ArrayList<ReminderDTO>();
		if (reminders != null && !reminders.isEmpty()) {
			for (Reminder reminder : reminders) {
				EventDetail eventDetail = eventDetailRepository.findByEventId(reminder.getEvent());
				List<EventCategoryModal> eventCategories = eventCategoryRepository.getCategoryByEvent(reminder.getEvent());
				List<String> categoryNames = new ArrayList<String>();
				for (EventCategoryModal category : eventCategories) {
					if (category.getName() != null) {
						categoryNames.add(category.getName());
					}
				}
				reminderDTOs.add(new ReminderDTO(reminder.getCustomer().getId(), reminder.getEvent().getId(), DateUtils.convertDateToString(reminder.getReminderDateTime()),
						reminder.isSendMailReminder(), reminder.isSendPushReminder(), reminder.getEvent().getName(), "", DateUtils.formatDateToString(reminder.getEvent()
								.getStartDate()), StringUtils.join(categoryNames, ", "), eventDetail.getBackgroundUrl()));
			}
			return RestMessage.onSuccessMessage(reminderDTOs);
		} else {
			return RestMessage.onValidationErrorMessage("No Record Found");
		}
	}

	private static final long K = 1024;
	private static final long M = K * K;
	private static final long G = M * K;
	private static final long T = G * K;

	public static String convertToStringRepresentation(final long value) {
		final long[] dividers = new long[] { T, G, M, K, 1 };
		final String[] units = new String[] { "TB", "GB", "MB", "KB", "B" };
		if (value < 1)
			throw new IllegalArgumentException("Invalid file size: " + value);
		String result = null;
		for (int i = 0; i < dividers.length; i++) {
			final long divider = dividers[i];
			if (value >= divider) {
				result = format(value, divider, units[i]);
				break;
			}
		}
		return result;
	}

	private static String format(final long value, final long divider, final String unit) {
		final double result = divider > 1 ? (double) value / (double) divider : (double) value;
		return new DecimalFormat("#,##0.#").format(result) + " " + unit;
	}

	public List<EventDetail> checkAllConditionsToShowEvents(List<EventDetail> eventDetails, Date today) {
		List<EventDetail> eventDetailsToShow = new ArrayList<EventDetail>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		int monthOfYear = calendar.get(Calendar.MONTH) + 1;

		for (EventDetail eventDetail : eventDetails) {
			// Get single event
			boolean addFlag = false;
			// Get event calender by this event
			EventCalender eventCalender = eventCalenderRepository.findByEventId(eventDetail.getEventId());
			// Check if there is recurrence rules that should display till end
			// date of event
			if (eventCalender.getRecurrenceRule() != null && !eventCalender.getRecurrenceRule().isEmpty()) {
				// If recurrence rule is 1 then It needs to display daily till
				// the end date of the event
				if (eventCalender.getRecurrenceRule().equals("1")) {
					addFlag = true;
					// If recurrence rule is 2 then It needs to display weekly (
					// specify the in which day it has to display )till the end
					// date of the event
				} else if (eventCalender.getRecurrenceRule().equals("2")) {
					if (eventCalender.getWeeks() == 0 || eventCalender.getWeeks() == weekOfMonth) {
						if (dayOfWeek == 1 && eventCalender.getIsSunday()) {
							addFlag = true;
						} else if (dayOfWeek == 2 && eventCalender.getIsMonday()) {
							addFlag = true;
						} else if (dayOfWeek == 3 && eventCalender.getIsTuesday()) {
							addFlag = true;
						} else if (dayOfWeek == 4 && eventCalender.getIsWednesday()) {
							addFlag = true;
						} else if (dayOfWeek == 5 && eventCalender.getIsThursday()) {
							addFlag = true;
						} else if (dayOfWeek == 6 && eventCalender.getIsFriday()) {
							addFlag = true;
						} else if (dayOfWeek == 7 && eventCalender.getIsSaturday()) {
							addFlag = true;
						}
					}
					// If recurrence rule is 3 then It needs to display monthly
				} else if (eventCalender.getRecurrenceRule().equals("3")) {
					if (eventCalender.getMonths() == 0 || eventCalender.getMonths() == monthOfYear) {
						if (eventCalender.getWeeks() == 0 || eventCalender.getWeeks() == weekOfMonth) {
							if (dayOfWeek == 1 && eventCalender.getIsSunday()) {
								addFlag = true;
							} else if (dayOfWeek == 2 && eventCalender.getIsMonday()) {
								addFlag = true;
							} else if (dayOfWeek == 3 && eventCalender.getIsTuesday()) {
								addFlag = true;
							} else if (dayOfWeek == 4 && eventCalender.getIsWednesday()) {
								addFlag = true;
							} else if (dayOfWeek == 5 && eventCalender.getIsThursday()) {
								addFlag = true;
							} else if (dayOfWeek == 6 && eventCalender.getIsFriday()) {
								addFlag = true;
							} else if (dayOfWeek == 7 && eventCalender.getIsSaturday()) {
								addFlag = true;
							}
						}
					}
					// Nothing to be done. No recurrence
				} else if (eventCalender.getRecurrenceRule().equals("4")) {
					addFlag = true;
				}
			} else {
				addFlag = true;
			}

			// For Exclude recurrence
			if (eventCalender.getExclusion() != null && !eventCalender.getExclusion().isEmpty()) {
				if (eventCalender.getExclusion().equals("1")) {
					if (eventCalender.getExclusionDate().compareTo(today) == 0) {
						addFlag = false;
					}
				} else if (eventCalender.getExclusion().equals("2")) {
					if (eventCalender.getExWeeks() == 0 || eventCalender.getExWeeks() == weekOfMonth) {
						if (dayOfWeek == 1 && eventCalender.getIsExSunday()) {
							addFlag = false;
						} else if (dayOfWeek == 2 && eventCalender.getIsExMonday()) {
							addFlag = false;
						} else if (dayOfWeek == 3 && eventCalender.getIsExTuesday()) {
							addFlag = false;
						} else if (dayOfWeek == 4 && eventCalender.getIsExWednesday()) {
							addFlag = false;
						} else if (dayOfWeek == 5 && eventCalender.getIsExThursday()) {
							addFlag = false;
						} else if (dayOfWeek == 6 && eventCalender.getIsExFriday()) {
							addFlag = false;
						} else if (dayOfWeek == 7 && eventCalender.getIsExSaturday()) {
							addFlag = false;
						}
					}
				} else if (eventCalender.getExclusion().equals("3")) {
					if (eventCalender.getExMonths() == 0 || eventCalender.getExMonths() == monthOfYear) {
						if (eventCalender.getExWeeks() == 0 || eventCalender.getExWeeks() == weekOfMonth) {
							if (dayOfWeek == 1 && eventCalender.getIsExSunday()) {
								addFlag = false;
							} else if (dayOfWeek == 2 && eventCalender.getIsExMonday()) {
								addFlag = false;
							} else if (dayOfWeek == 3 && eventCalender.getIsExTuesday()) {
								addFlag = false;
							} else if (dayOfWeek == 4 && eventCalender.getIsExWednesday()) {
								addFlag = false;
							} else if (dayOfWeek == 5 && eventCalender.getIsExThursday()) {
								addFlag = false;
							} else if (dayOfWeek == 6 && eventCalender.getIsExFriday()) {
								addFlag = false;
							} else if (dayOfWeek == 7 && eventCalender.getIsExSaturday()) {
								addFlag = false;
							}
						}
					}
				} else if (eventCalender.getExclusion().equals("4")) {
					addFlag = false;
				}
			}

			if (addFlag) {
				eventDetailsToShow.add(eventDetail);
			}
		}
		return eventDetailsToShow;
	}

	/**
	 * 
	 * @param eventDetails
	 * @return
	 * 
	 *         Construct Event Details
	 * 
	 */

	public ResponseData constructEventDetails(Integer eventId, Integer customerId) throws Exception {
		VendorEventDetails vendorEventDetails = new VendorEventDetails();
		Event eventid = eventRepository.findOne(eventId);
		Customer customer = customerRepository.findOne(customerId);
		EventDetail eventDetail = eventDetailRepository.findByEventId(eventid);
		List<EventCategoryModal> eventCategory = eventCategoryRepository.getCategoryByEvent(eventid);
		if (eventDetail != null) {
			vendorEventDetails.setEventId(eventDetail.getEventId().getId());
			vendorEventDetails.setEventName(eventDetail.getEventId().getName());
			vendorEventDetails.setEventImageUrl(eventDetail.getBackgroundUrl());
			vendorEventDetails.setEventDescription(eventDetail.getEventId().getDescription().trim());
			if (eventDetail.getEventId().getEventCalender() != null) {
				vendorEventDetails.setEventDate(DateUtils.formatDateToString(eventDetail.getEventId().getEventCalender().getStartDate()));
				vendorEventDetails.setOfferTime(constructOfferTime(eventDetail.getEventId().getEventCalender().getStartTime(), eventDetail.getEventId().getEventCalender()
						.getEndTime()));
			}

			Vendor vendor = eventDetail.getEventId().getVendorId();
			CustomerFavoriteEvents customerFavoriteEvents = customerFavoriteRepository.findByEventAndCustomerId(eventDetail.getEventId(), customer);
			if (customerFavoriteEvents != null) {
				vendorEventDetails.setEventStatus(true);
			} else {
				vendorEventDetails.setEventStatus(false);
			}

			if (vendor != null) {
				vendorEventDetails.setVendorId(eventDetail.getEventId().getVendorId().getId());
				VendorDetail vendorDetail = vendor.getVendorDetail();
				List<VendorAddress> vendorAddress = vendorAddressRepository.findByVendorId(vendor.getId());
				if (vendorDetail != null) {
					vendorEventDetails.setVendorName(vendor.getName());
					vendorEventDetails.setVendorImageUrl(vendorDetail.getLogoUrl());
					vendorEventDetails.setAddressDTOs(constructVendorAddressDTO(vendorAddress));
					vendorEventDetails.setPhoneNumber(vendorDetail.getPhoneNumber());
					vendorEventDetails.setCategoryDTOs(constructVendorEventCategories(eventCategory));
				}
			}
		}
		return RestMessage.onSuccessMessage(vendorEventDetails);
	}

	public List<VendorEventCategoryDTO> constructVendorEventCategories(List<EventCategoryModal> eventCategory) {
		List<VendorEventCategoryDTO> categoryDTOs = new ArrayList<VendorEventCategoryDTO>();
		for (EventCategoryModal category : eventCategory) {
			categoryDTOs.add(new VendorEventCategoryDTO(category.getId(), category.getName()));
		}
		return categoryDTOs;
	}

	public ResponseData constructVendorEventDetails(Integer vendorId, Integer eventId) throws Exception {
		List<Event> events = eventRepository.findByVendorIdAndIdNotIn(vendorId, eventId);
		return RestMessage.onSuccessMessage(constructEvents(events, vendorId));
	}

	private List<VendorEventDetails> constructEvents(List<Event> events, Integer vendorId) {
		List<VendorEventDetails> eventDetails = new ArrayList<VendorEventDetails>();
		Vendor vendor = vendorRepository.findOne(vendorId);
		if (!events.isEmpty()) {
			for (Event event : events) {
				EventDetail eventDetail = event.getEventDetail();
				if (vendor != null) {
					VendorDetail vendorDetail = vendor.getVendorDetail();
					List<CustomerFavoriteEvents> customerFavoriteEvents = customerFavoriteRepository.findByEvent(eventDetail.getEventId());
					List<VendorAddress> vendorAddress = vendorAddressRepository.findByVendorId(vendor.getId());
					if (customerFavoriteEvents != null && !customerFavoriteEvents.isEmpty()) {
						eventDetails.add(new VendorEventDetails(eventDetail.getEventId().getId(), event.getName(), eventDetail.getBackgroundUrl(), event.getShortDescription(),
								vendor.getId(), vendor.getName(), vendorDetail.getLogoUrl(), vendorDetail.getPhoneNumber(), true, constructOfferTime(eventDetail.getEventId()
										.getEventCalender().getStartTime(), eventDetail.getEventId().getEventCalender().getEndTime()), constructVendorAddressDTO(vendorAddress),
								DateUtils.formatDateToString(event.getEventCalender().getStartDate())));
					} else {
						eventDetails.add(new VendorEventDetails(eventDetail.getEventId().getId(), event.getName(), eventDetail.getBackgroundUrl(), event.getShortDescription(),
								vendor.getId(), vendor.getName(), vendorDetail.getLogoUrl(), vendorDetail.getPhoneNumber(), false, constructOfferTime(eventDetail.getEventId()
										.getEventCalender().getStartTime(), eventDetail.getEventId().getEventCalender().getEndTime()), constructVendorAddressDTO(vendorAddress),
								DateUtils.formatDateToString(event.getEventCalender().getStartDate())));
					}

				}
			}
		}
		return eventDetails;
	}

	public ResponseData constructCategoryEventDetails(Integer vendorId, Integer categoryId, Integer eventId) throws Exception {
		List<Event> events = eventCategoryRepository.findByCategoryIdAndEventNotIn(categoryId, eventId);
		return RestMessage.onSuccessMessage(constructEvents(events, vendorId));
	}

	public List<VendorAddressDTO> constructVendorAddressDTO(List<VendorAddress> vendorAddress) {
		List<VendorAddressDTO> eventAddressDTOs = new ArrayList<VendorAddressDTO>();
		if (vendorAddress != null && vendorAddress.size() > 0) {
			for (VendorAddress address : vendorAddress) {
				eventAddressDTOs.add(new VendorAddressDTO(address.getFullAddress(), address.getAddress().getLat(), address.getAddress().getLong1()));
			}
		}
		return eventAddressDTOs;
	}

	/**
	 * 
	 * @param searchTerm
	 * @return
	 * 
	 *         This method is for searching with three categories which retrives
	 *         from event, by address and category
	 * 
	 */

	public List<EventListDTO> removeDublicates(List<EventListDTO> list1, List<EventListDTO> list2) {
		// Combine list1 and list 2 into one
		List<EventListDTO> union = new ArrayList<EventListDTO>(list1);
		union.addAll(list2);
		/**
		 * Prepare hashset and add the combined list to the declared hashset
		 * variable to remove the dublicates.
		 */
		HashSet<EventListDTO> uniqueElement = new HashSet<EventListDTO>();
		uniqueElement.addAll(union);
		union.clear();
		/**
		 * combine all the unique element and add it back to list.
		 */
		union.addAll(uniqueElement);
		return union;
	}

	public List<CategoryDTO> constructListOfCategory(List<Category> categories) {
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		for (Category category : categories) {
			categoryDTOs.add(new CategoryDTO(category.getId(), category.getName(), category.getBackgroundUrl()));
		}
		return categoryDTOs;
	}

	public static String formatPhoneNumber(Object phone) throws Exception {
		if (phone != null) {
			double p = 0;

			if (phone instanceof String)
				p = Double.valueOf((String) phone);

			if (phone instanceof Integer)
				p = (Integer) phone;

			if (phone instanceof Float)
				p = (Float) phone;

			if (phone instanceof Double)
				p = (Double) phone;

			if (p == 0 || String.valueOf(p) == "" || String.valueOf(p).length() < 7)
				throw new Exception("Paramenter is no valid");

			String fot = phoneFormatD.format(p);

			String extra = fot.length() > 10 ? fot.substring(0, fot.length() - 10) : "";
			fot = fot.length() > 10 ? fot.substring(fot.length() - 10, fot.length()) : fot;

			String[] arr = { (fot.charAt(0) != '0') ? fot.substring(0, 3) : (fot.charAt(1) != '0') ? fot.substring(1, 3) : fot.substring(2, 3), fot.substring(3, 6),
					fot.substring(6) };
			String r = phoneFormatM.format(arr);
			r = (r.contains("(0)")) ? r.replace("(0) ", "") : r;
			r = (extra != "") ? ("+" + extra + " " + r) : r;
			return (r);
		} else {
			return "";
		}

	}

	public ResponseData constructCategoryDTO() {
		List<PreferenceDTO> preferenceForCustomer = new ArrayList<PreferenceDTO>();
		List<EventCategoryModal> categoriesFromDb = eventCategoryModalRepository.findAll();
		for (EventCategoryModal category : categoriesFromDb) {
			PreferenceDTO preferenceDTO = new PreferenceDTO();
			preferenceDTO.setCategoryName(category.getName());
			preferenceDTO.setId(category.getId());
			preferenceDTO.setImageUrl(category.getBackgroundUrl());
			preferenceForCustomer.add(preferenceDTO);
		}
		return RestMessage.onSuccessMessage(preferenceForCustomer);
	}

	public WHAppVersion getAppVersions() {
		try {
			WHAppVersion appVersion = new WHAppVersion();
			List<WHVersionDetails> list = whVersionDetailsRepository.findAll();
			if (list != null && list.size() > 0) {
				for (WHVersionDetails property : list) {
					if (property.getVersionName().equals("Android Version")) {
						appVersion.setAndroidVersion(property.getVersionValue());
					} else if (property.getVersionName().equals("iOS Version")) {
						appVersion.setiOSVersion(property.getVersionValue());
					}
				}
			} else {
				logger.debug("No property values found..");
			}
			logger.debug("Fetched app version .. {}", appVersion);
			return appVersion;
		} catch (Exception ex) {
			logger.error("Failed to get app versions .. {}", ex.getMessage());
		}
		return null;
	}

	public static String[] getLatLongPositions(String address) throws Exception {
		int responseCode = 0;
		String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
		System.out.println("URL : " + api);
		URL url = new URL(api);
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.connect();
		responseCode = httpConnection.getResponseCode();
		if (responseCode == 200) {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(httpConnection.getInputStream());
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("/GeocodeResponse/status");
			String status = (String) expr.evaluate(document, XPathConstants.STRING);
			if (status.equals("OK")) {
				expr = xpath.compile("//geometry/location/lat");
				String latitude = (String) expr.evaluate(document, XPathConstants.STRING);
				expr = xpath.compile("//geometry/location/lng");
				String longitude = (String) expr.evaluate(document, XPathConstants.STRING);
				return new String[] { latitude, longitude };
			} else {
				throw new Exception("Error from the API - response status: " + status);
			}
		}
		return null;
	}

	public String uploadProfileImage(String customerProfileImage) {
		String profileImageUrl = "";
		if (customerProfileImage != null && !customerProfileImage.isEmpty()) {
			profileImageUrl = util.uploadFileWithBase64String(customerProfileImage);
		}
		return profileImageUrl;
	}

	String constructOfferTime(Date startTime, Date endTime) {
		String startTimeFormatted = DateUtils.formatTimeToString(startTime);
		String endTimeFormatted = DateUtils.formatTimeToString(endTime);
		String offerTime = startTimeFormatted + " - " + endTimeFormatted;
		return offerTime;

	}

	public List<EventListDateDTO> constructEventListOnDateOrderResponse(Page<EventDetail> eventDetails) {
		List<EventListDateDTO> result = new ArrayList<EventListDateDTO>();
		String headerDate = "";
		List<EventListDTO> datas = new ArrayList<EventListDTO>();
		for (EventDetail eventDetail : eventDetails) {
			if (headerDate.isEmpty()) {
				String date = DateUtils.formatDateToString(eventDetail.getEventId().getStartDate());
				headerDate = date;
			} else if (!headerDate.equals(eventDetail.getEventId().getStartDate())) {
				if (datas.size() > 0) {
					EventListDateDTO eventListDateDTO = new EventListDateDTO();
					eventListDateDTO.setDate(headerDate);
					eventListDateDTO.setEventListDTOs(datas);
					result.add(eventListDateDTO);
					String date = DateUtils.formatDateToString(eventDetail.getEventId().getStartDate());
					headerDate = date;
					datas = new ArrayList<EventListDTO>();
				}
			}
			EventListDTO eventListDTO = new EventListDTO();
			if (eventDetail != null && eventDetail.getEventId().getId() != null) {
				eventListDTO.setEventId(eventDetail.getEventId().getId());
				eventListDTO.setEventName(eventDetail.getEventId().getName());
				eventListDTO.setImageUrl(eventDetail.getBackgroundUrl());
				eventListDTO.setEventShortDescription(eventDetail.getEventId().getShortDescription());
				eventListDTO.setVendorName(eventDetail.getEventId().getVendorId().getName());
				if (eventDetail.getEventId().getEventCalender() != null) {
					eventListDTO.setEventDate(DateUtils.formatDateToString(eventDetail.getEventId().getEventCalender().getStartDate()));
					eventListDTO.setEventTime(DateUtils.formatTimeToString(eventDetail.getEventId().getEventCalender().getStartTime()));
					eventListDTO.setOfferTime(constructOfferTime(eventDetail.getEventId().getEventCalender().getStartTime(), eventDetail.getEventId().getEventCalender()
							.getEndTime()));
				} else {
					eventListDTO.setEventDate("");
					eventListDTO.setEventTime("");
					eventListDTO.setOfferTime("");
				}
				if (eventDetail.getBackgroundUrl() != null) {
					eventListDTO.setImageUrl(eventDetail.getBackgroundUrl());
				} else {
					eventListDTO.setImageUrl("");
				}
			}
			datas.add(eventListDTO);
		}

		if (datas.size() > 0) {
			EventListDateDTO eventListDateDTO = new EventListDateDTO();
			eventListDateDTO.setDate(headerDate);
			eventListDateDTO.setEventListDTOs(datas);
			result.add(eventListDateDTO);
		}
		return result;
	}

	public List<EventListDTO> constructEventListResponse(Page<EventDetail> eventDetails) {
		List<EventListDTO> datas = new ArrayList<EventListDTO>();
		for (EventDetail eventDetail : eventDetails) {
			EventListDTO eventListDTO = new EventListDTO();
			if (eventDetail != null && eventDetail.getEventId().getId() != null) {
				eventListDTO.setEventId(eventDetail.getEventId().getId());
				eventListDTO.setEventName(eventDetail.getEventId().getName());
				eventListDTO.setEventShortDescription(eventDetail.getEventId().getShortDescription());
				eventListDTO.setVendorName(eventDetail.getEventId().getVendorId().getName());
				if (eventDetail.getEventId().getEventCalender() != null) {
					eventListDTO.setEventDate(DateUtils.formatDateToString(eventDetail.getEventId().getEventCalender().getStartDate()));
					eventListDTO.setEventTime(DateUtils.formatTimeToString(eventDetail.getEventId().getEventCalender().getStartTime()));
					eventListDTO.setOfferTime(constructOfferTime(eventDetail.getEventId().getEventCalender().getStartTime(), eventDetail.getEventId().getEventCalender()
							.getEndTime()));
				} else {
					eventListDTO.setEventDate("");
					eventListDTO.setEventTime("");
					eventListDTO.setOfferTime("");
				}
				if (eventDetail.getBackgroundUrl() != null) {
					eventListDTO.setImageUrl(eventDetail.getBackgroundUrl());
				} else {
					eventListDTO.setImageUrl("");
				}
			}

			datas.add(eventListDTO);
		}
		return datas;
	}
}
