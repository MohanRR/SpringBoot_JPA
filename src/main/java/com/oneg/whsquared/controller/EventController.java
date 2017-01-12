package com.oneg.whsquared.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.entity.Address;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventAddress;
import com.oneg.whsquared.entity.EventAgenda;
import com.oneg.whsquared.entity.EventCalender;
import com.oneg.whsquared.entity.EventCategory;
import com.oneg.whsquared.entity.EventDetail;
import com.oneg.whsquared.entity.EventView;
import com.oneg.whsquared.entity.RateCard;
import com.oneg.whsquared.entity.Role;
import com.oneg.whsquared.entity.State;
import com.oneg.whsquared.entity.User;
import com.oneg.whsquared.entity.UserVendors;
import com.oneg.whsquared.repository.AddressRepository;
import com.oneg.whsquared.repository.EventAddressRepository;
import com.oneg.whsquared.repository.EventAgendaRepository;
import com.oneg.whsquared.repository.EventCalenderRepository;
import com.oneg.whsquared.repository.EventCategoryRepository;
import com.oneg.whsquared.repository.EventDetailRepository;
import com.oneg.whsquared.repository.EventRepository;
import com.oneg.whsquared.repository.RateCardRepository;
import com.oneg.whsquared.repository.StateRepository;
import com.oneg.whsquared.repository.UserRepository;
import com.oneg.whsquared.repository.UserVendorRepository;
import com.oneg.whsquared.repository.vendor.VendorRepository;
import com.oneg.whsquared.security.JwtUtil;
import com.oneg.whsquared.util.Helper;
import com.oneg.whsquared.util.ResponseData;
import com.oneg.whsquared.util.ResponseType;
import com.oneg.whsquared.util.Util;
import com.oneg.whsquared.util.WHStatus;

/**
 * @author Anbukkani G
 * 
 */
@RestController
@RequestMapping(value = "api/event")
public class EventController {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	StateRepository stateRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	RateCardRepository rateCardRepository;
	@Autowired
	EventDetailRepository eventDetailRepository;
	@Autowired
	EventCategoryRepository eventCategoryRepository;
	@Autowired
	RateCardRepository eventRateCardRepository;
	@Autowired
	EventAgendaRepository eventAgendaRepository;

	@Autowired
	EventCalenderRepository eventCalenderRepository;

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private Util util;

	@Autowired
	private EventAddressRepository eventAddressRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private UserVendorRepository userVendorRepository;

	@Transactional
	@RequestMapping(value = "/bydate", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType getCategories(@Param("date") String date) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = format.parse(date);
		List<Event> events = eventRepository.findAll();
		return util.response(WHStatus.SUCCESS.value(), "", events);
	}

	@Transactional
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType getCategories(@Param("page") int page, @Param("size") int size, HttpServletRequest httpServletRequest) throws Exception {

		Page<Event> events = null;
		try {
			LinkedHashMap linkedHashMap = jwtUtil.getUser(httpServletRequest);
			Integer userId = (Integer) linkedHashMap.get("id");
			User user = userRepository.findOne(userId);
			Role role = user.getRole();
			if (role == null) {
				return util.response(WHStatus.FAILURE.value(), "Invalid user role.", null);
			}
			String roleName = role.getName();
			PageRequest request = new PageRequest(page, size);
			if (roleName != null && roleName.equalsIgnoreCase("ROLE_VENDOR")) {
				List<UserVendors> userVendorList = userVendorRepository.findByUserId(user.getId());
				if (userVendorList != null && userVendorList.size() > 0) {
					List<Integer> vendorIds = new ArrayList<Integer>();
					for (UserVendors userVendors : userVendorList) {
						if (userVendors.getVendor() != null && userVendors.getVendor().getId() != null && userVendors.getVendor().getId() > 0) {
							vendorIds.add(userVendors.getVendor().getId());
						}
					}
					if (vendorIds != null && vendorIds.size() > 0) {
						events = eventRepository.findAllVendorId(vendorIds, request);
					}
				}
			} else {
				events = eventRepository.findAll(request);
			}
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", events);
	}

	@Transactional
	@RequestMapping(value = "/list/{vendorId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType getCategories(@PathVariable("vendorId") int vendorId, @Param("page") int page, @Param("size") int size, HttpServletRequest httpServletRequest) throws Exception {

		Page<Event> events = null;
		try {
			PageRequest request = new PageRequest(page, size);
			events = eventRepository.findByVendorId(vendorId, request);
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", events);
	}

	@Transactional
	@RequestMapping(value = "/search/{vendorId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType search(@PathVariable("vendorId") int vendorId, @Param("page") int page, @Param("size") int size, @Param("searchText") String searchText,
			HttpServletRequest httpServletRequest) throws Exception {
		Page<Event> events = null;
		try {
			PageRequest request = new PageRequest(page, size);
			if (searchText == null && searchText.equalsIgnoreCase("\"\"")) {
				events = eventRepository.findByVendorId(vendorId, request);
			} else {
				events = eventRepository.searchByNameDescVendor(request, searchText, vendorId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", events);
	}

	@Transactional
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType search(@Param("page") int page, @Param("size") int size, @Param("searchText") String searchText, HttpServletRequest httpServletRequest) throws Exception {
		Page<Event> events = null;
		try {
			LinkedHashMap linkedHashMap = jwtUtil.getUser(httpServletRequest);
			Integer userId = (Integer) linkedHashMap.get("id");
			User user = userRepository.findOne(userId);
			Role role = user.getRole();
			if (role == null) {
				return util.response(WHStatus.FAILURE.value(), "Invalid user role.", null);
			}
			String roleName = role.getName();
			PageRequest request = new PageRequest(page, size);
			if (roleName != null && roleName.equalsIgnoreCase("ROLE_VENDOR")) {
				List<UserVendors> userVendorList = userVendorRepository.findByUserId(user.getId());
				if (userVendorList != null && userVendorList.size() > 0) {
					for (UserVendors userVendors : userVendorList) {
						if (userVendors.getVendor() != null && userVendors.getVendor().getId() > 0) {
							if (searchText == null && searchText.equalsIgnoreCase("\"\"")) {
								events = eventRepository.findByVendorId(userVendors.getVendor().getId(), request);
							} else {
								events = eventRepository.searchByNameDescVendor(request, searchText, userVendors.getVendor().getId());
							}
						}
					}
				}
			} else if (searchText == null && searchText.equalsIgnoreCase("\"\"")) {
				events = eventRepository.findAll(request);
			} else {
				events = eventRepository.searchByNameDesc(request, searchText);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", events);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseType create(@RequestBody Event event, HttpServletRequest httpServletRequest) {
		Event data = null;
		List<UserVendors> userVendors = null;
		try {
			LinkedHashMap linkedHashMap = jwtUtil.getUser(httpServletRequest);
			Integer userId = (Integer) linkedHashMap.get("id");
			User user = userRepository.findOne(userId);
			Role role = user.getRole();
			if (role == null) {
				return util.response(WHStatus.FAILURE.value(), "Invalid user role.", null);
			}
			String roleName = role.getName();

			/*
			 * if (roleName != null && roleName.equalsIgnoreCase("ROLE_VENDOR"))
			 * {
			 * 
			 * 
			 * userVendorRepository.findByUserId(user.getId()); if
			 * (event.getUserVendorList() != null &&
			 * event.getUserVendorList().size() > 0) { for (UserVendors
			 * userVendors : event.getUserVendorList()) {
			 * userVendors.setEvent(event); } }
			 * 
			 * }
			 */

			// userVendors = event.getUserVendorList();
			EventDetail eventDetail = event.getEventDetail();
			List<EventCategory> eventCategoryList = event.getEventCategoryList();
			EventCalender eventCalender = event.getEventCalender();

			event.setEventDetail(null);
			event.setEventCategoryList(null);
			event.setEventCalender(null);
			// event.setUserVendorList(null);

			data = eventRepository.save(event);
			event.setEventCalender(null);

			if (eventCategoryList != null && !eventCategoryList.isEmpty()) {
				List<EventCategory> eventCategories = new ArrayList<EventCategory>();
				for (EventCategory eventCategory : eventCategoryList) {
					EventCategory eventCategoryNew = new EventCategory();
					eventCategoryNew.setEvent(data);
					eventCategoryNew.setEventCategory(eventCategory.getEventCategory());
					eventCategoryRepository.save(eventCategoryNew);
					eventCategories.add(eventCategoryNew);
				}
				data.setEventCategoryList(eventCategories);
			}
			if (eventDetail != null) {
				Event event2 = new Event();
				event2.setId(data.getId());
				eventDetail.setEventId(event2);
				eventDetail.setName(event.getName());
				String url = util.uploadFile(eventDetail.getBackgroundUrl());
				eventDetail.setBackgroundUrl(url);

				eventDetailRepository.save(eventDetail);
			}

			if (eventCalender != null) {
				Event event2 = new Event();
				event2.setId(data.getId());
				eventCalender.setEventId(event2);
				eventCalender = eventCalenderRepository.save(eventCalender);
				data.setEventCalender(eventCalender);
			}

			if (userVendors != null && userVendors.size() > 0 && user != null && user.getId() > 0) {
				List<UserVendors> userVendorList = new ArrayList<UserVendors>();
				for (UserVendors userVendor : userVendors) {
					UserVendors findUserVendors = userVendorRepository.findByUserIdAndVendorId(user.getId(), userVendor.getVendor().getId());
					// findUserVendors.setEvent(data);
					UserVendors savedUserVendors = userVendorRepository.save(findUserVendors);
					userVendorList.add(savedUserVendors);
				}
				// data.setUserVendorList(userVendorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), data);
		}
		return util.response(WHStatus.SUCCESS.value(), "", data);
	}

	@RequestMapping(value = "/{eventId}")
	public ResponseType getById(@PathVariable("eventId") String eventId) {
		Event event = null;
		try {
			event = eventRepository.findById(Integer.parseInt(eventId));
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", event);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseType update(@RequestBody Event event) {
		try {
			if (event.getEventDetail() != null) {
				event.getEventDetail().setEventId(event);
				String url = util.uploadFile(event.getEventDetail().getBackgroundUrl());
				event.getEventDetail().setBackgroundUrl(url);

			}
			if (event.getEventCalender() != null) {
				event.getEventCalender().setEventId(event);
			}
			if (event.getEventViewList() != null && event.getEventViewList().size() > 0) {
				for (EventView eventView : event.getEventViewList()) {
					eventView.setEventId(event);
				}
			}
			List<EventCategory> eventCatList = eventCategoryRepository.findByEventId(event.getId());
			if (event.getEventCategoryList() != null && event.getEventCategoryList().size() > 0) {

				int eventCatListLength = eventCatList.size();
				int selEventCatLength = event.getEventCategoryList().size();
				int highestLength = eventCatListLength;
				boolean isRemoved = true;
				if (eventCatListLength < selEventCatLength) {
					highestLength = selEventCatLength;
					isRemoved = false;
				}
				if (isRemoved) {
					for (EventCategory eventCat : eventCatList) {
						int count = 0;
						for (EventCategory eventCategory : event.getEventCategoryList()) {
							if (eventCat.getId().equals(eventCategory.getId())) {
								eventCategory.setEvent(event);
								break;
							} else {
								count++;
							}
						}
						if (count >= selEventCatLength) {
							eventCategoryRepository.deleteEventCategoryById(eventCat.getId());
						}
					}
				} else {
					for (EventCategory eventCategory : event.getEventCategoryList()) {
						eventCategory.setEvent(event);
					}
				}
			} else {
				for (EventCategory eventCat : eventCatList) {
					eventCategoryRepository.deleteEventCategory(eventCat.getId());
				}
			}
			event = eventRepository.save(event);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", event);
	}

	@Modifying
	@Transactional
	@RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
	public ResponseType delete(@PathVariable("eventId") int eventId) {
		try {
			Event event = eventRepository.findOne(eventId);
			List<EventAddress> eventAddresslist = event.getEventAddressList();
			if (eventAddresslist != null && !eventAddresslist.isEmpty()) {
				List<Address> addressList = new ArrayList<Address>();
				for (EventAddress ea : eventAddresslist) {
					Address address = ea.getAddress();
					addressList.add(address);
				}
				eventAddressRepository.deleteEventAddress(eventId);
				for (Address add : addressList) {
					addressRepository.deleteAddress(add.getId());
				}
			}
			EventCalender eventCalender = event.getEventCalender();
			if (eventCalender != null) {
				eventCalenderRepository.deleteCalenderDetail(eventId);
			}
			EventDetail eventDetail = event.getEventDetail();
			if (eventDetail != null) {
				eventDetailRepository.deleteEventDetail(eventId);
			}

			List<RateCard> rateCardList = event.getRateCardList();
			if (rateCardList != null && !rateCardList.isEmpty()) {
				eventRateCardRepository.deleteEventRateCard(eventId);
			}
			List<EventCategory> eventCategoryList = event.getEventCategoryList();
			if (eventCategoryList != null && !eventCategoryList.isEmpty()) {
				eventCategoryRepository.deleteEventCategory(eventId);
			}
			List<EventAgenda> eventAgendaList = eventAgendaRepository.findByEventId(eventId);
			if (eventAgendaList != null && !eventAgendaList.isEmpty()) {
				eventAgendaRepository.deleteEventAgenda(eventId);
			}
			/*
			 * List<UserVendors> userVendors = event.getUserVendorList(); if
			 * (userVendors != null && userVendors.size() > 0) { for
			 * (UserVendors userVendor : userVendors) {
			 * userVendor.setEvent(null); userVendorRepository.save(userVendor);
			 * } }
			 */
			event.setVendorId(null);
			eventRepository.deleteEvent(eventId);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", null);
	}

	@RequestMapping
	public ResponseEntity<ResponseData> list() {
		ResponseData response = new ResponseData();
		try {
			List<Event> eventList = eventRepository.findAll();
			response.setResult(true);
			response.setMessage("SUCCESS");
			response.setData(eventList);
		} catch (Exception e) {
			// response.setStatus(AprilFourMessage.FAILURE);
			response.setMessage("ERROR");
		}
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/address/list", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType getEventAddressList(@RequestParam("eventId") int eventId) throws Exception {
		List<EventAddress> events = null;
		try {
			// PageRequest request = new PageRequest(page, size);
			events = eventAddressRepository.findByEventId(eventId);
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", events);
	}

	@Transactional
	@RequestMapping(value = "/address/save", method = RequestMethod.POST)
	public @ResponseBody
	ResponseType saveEventAddress(@RequestParam("eventId") int eventId, @RequestBody EventAddress eventAddress) throws Exception {
		try {
			Event event = new Event();
			event.setId(eventId);
			eventAddress.setEvent(event);
			State state = stateRepository.findOne(eventAddress.getAddress().getState().getId());
			eventAddress.getAddress().getState().setName(state.getName());

			// Added by Aariv
			String latLongs[] = Helper.getLatLongPositions(eventAddress.getAddress().getFullAddress());
			eventAddress.getAddress().setLat(latLongs[0]);
			eventAddress.getAddress().setLong1(latLongs[1]);

			addressRepository.save(eventAddress.getAddress());

			eventAddress = eventAddressRepository.save(eventAddress);

		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", eventAddress);
	}

	@Transactional
	@RequestMapping(value = "/address/update", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseType updateEventAddress(@RequestParam("eventId") int eventId, @RequestBody EventAddress eventAddress) throws Exception {
		try {
			Event event = new Event();
			event.setId(eventId);
			eventAddress.setEvent(event);
			eventAddress = eventAddressRepository.save(eventAddress);
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", eventAddress);
	}

	@Transactional
	@RequestMapping(value = "/address/get/{id}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType getEventAddress(@PathVariable("id") int id) throws Exception {
		EventAddress eventAddress = null;
		try {
			eventAddress = eventAddressRepository.findOne(id);
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", eventAddress);
	}

	@Transactional
	@RequestMapping(value = "/address/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ResponseType deleteEventAddress(@PathVariable("id") int id) throws Exception {
		EventAddress eventAddress = null;
		try {
			eventAddressRepository.delete(id);
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", eventAddress);
	}

	@Transactional
	@RequestMapping(value = "/agenda/list", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType getEventAgendas(@RequestParam("eventId") int eventId) throws Exception {
		List<EventAgenda> events = null;
		try {
			// PageRequest request = new PageRequest(page, size);
			events = eventAgendaRepository.findByEventId(eventId);
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", events);
	}

	@Transactional
	@RequestMapping(value = "/agenda/save", method = RequestMethod.POST)
	public @ResponseBody
	ResponseType saveEventAgenda(@RequestBody EventAgenda eventAgenda) throws Exception {
		try {
			eventAgenda.setImageUrl(util.uploadFile(eventAgenda.getImageUrl()));
			eventAgenda = eventAgendaRepository.save(eventAgenda);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", eventAgenda);
	}

	@Transactional
	@RequestMapping(value = "/agenda/update", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseType updateEventAgenda(@RequestParam("eventId") int eventId, @RequestBody EventAgenda eventAgenda) throws Exception {
		try {
			Event event = new Event();
			event.setId(eventId);
			eventAgenda.setEventId(event);
			eventAgenda = eventAgendaRepository.save(eventAgenda);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", eventAgenda);
	}

	@Transactional
	@RequestMapping(value = "/agenda/get/{id}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType getEventAgenda(@PathVariable("id") int id) throws Exception {
		EventAgenda eventAgenda = null;
		try {
			eventAgenda = eventAgendaRepository.findOne(id);
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", eventAgenda);
	}

	@Transactional
	@RequestMapping(value = "/agenda/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ResponseType deleteEventAgenda(@PathVariable("id") int id) throws Exception {
		try {
			eventAgendaRepository.delete(id);
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", null);
	}

	@RequestMapping(value = "/ratecard/list", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType getRateCards(@RequestParam("eventId") int eventId) {
		List<RateCard> rateCards = null;
		try {
			Event event = new Event();
			event.setId(eventId);
			rateCards = rateCardRepository.findByEventId(event);
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", rateCards);
	}

	@RequestMapping(value = "/ratecard/get/{rateCardId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType getRateCard(@PathVariable("rateCardId") int rateCardId) {
		RateCard rateCard = null;
		try {
			rateCard = rateCardRepository.findOne(rateCardId);
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", rateCard);
	}

	@RequestMapping(value = "/ratecard/delete/{rateCardId}", method = RequestMethod.DELETE)
	public @ResponseBody
	ResponseType deleteRateCard(@PathVariable("rateCardId") int rateCardId) {

		try {
			rateCardRepository.delete(rateCardId);
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", "Removed Successfully");
	}

	@RequestMapping(value = "/ratecard/save", method = RequestMethod.POST)
	public @ResponseBody
	ResponseType saveRateCard(@RequestBody RateCard rateCard) {
		try {
			rateCardRepository.save(rateCard);
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", rateCard);
	}

	@RequestMapping(value = "/ratecard/update", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseType updateRateCard(@RequestBody RateCard rateCard) {
		try {
			rateCardRepository.save(rateCard);
		} catch (Exception e) {
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", rateCard);
	}

	@RequestMapping(value = "/delete-image/{eventId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseType deleteImage(@PathVariable("eventId") int eventId, @RequestParam("property") String property, @RequestParam("uri") String uri) {
		try {
			Event event = eventRepository.findById(eventId);
			EventDetail detail = event.getEventDetail();
			if (property.equalsIgnoreCase("backgroundurl")) {
				if (!uri.equalsIgnoreCase(detail.getBackgroundUrl())) {
					return util.response(WHStatus.FAILURE.value(), "invalid resource", "");
				}
				detail.setBackgroundUrl(null);
			}
			event.setEventDetail(detail);
			boolean deleteFile = util.deleteFile(uri);
			eventRepository.save(event);
			return util.response(WHStatus.SUCCESS.value(), "", deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), "internal error", "");
		}
	}

}
