package com.oneg.whsquared.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.entity.CustomerFavoriteCategory;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventCategory;
import com.oneg.whsquared.entity.EventCategoryModal;
import com.oneg.whsquared.entity.Vendor;
import com.oneg.whsquared.repository.EventCategoryModalRepository;
import com.oneg.whsquared.repository.EventCategoryRepository;
import com.oneg.whsquared.repository.VendorCategoryRepository;
import com.oneg.whsquared.security.JwtUtil;
import com.oneg.whsquared.util.ResponseType;
import com.oneg.whsquared.util.Util;
import com.oneg.whsquared.util.WHStatus;

@RestController
@RequestMapping(value = "api/event/category")
public class EventCategoryController {

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	private Util util;

	@Autowired
	private WHCommon whCommon;

	@Autowired
	EventCategoryModalRepository evetCategoryModalRepository;

	@Autowired
	EventCategoryRepository eventCategoryRepository;

	@Autowired
	VendorCategoryRepository vendorCategoryRepository;

	@Transactional
	@RequestMapping(value = "/listall", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType getCategories() throws Exception {
		List<EventCategoryModal> categories = evetCategoryModalRepository.findAll();
		return util.response(WHStatus.SUCCESS.value(), "", categories);
	}

	@Transactional
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType getCategories(@RequestParam("page") int page, @RequestParam("size") int size) throws Exception {
		PageRequest request = new PageRequest(page, size);
		Page<EventCategoryModal> categories = null;
		categories = evetCategoryModalRepository.findAll(request);
		return util.response(WHStatus.SUCCESS.value(), "", categories);
	}

	@Transactional
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType search(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("searchText") String searchText) throws Exception {
		PageRequest request = new PageRequest(page, size);
		Page<EventCategoryModal> categories = null;
		if (searchText == null || searchText.equalsIgnoreCase("\"\"")) {
			categories = evetCategoryModalRepository.findAll(request);
		} else {
			categories = evetCategoryModalRepository.searchByName(searchText, request);
		}
		return util.response(WHStatus.SUCCESS.value(), "", categories);
	}

	@Transactional
	@RequestMapping(value = "/get-by-id", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType getById(@RequestParam("id") int id) throws Exception {
		EventCategoryModal category = evetCategoryModalRepository.findById(id);
		return util.response(WHStatus.SUCCESS.value(), "", category);
	}

	@Transactional
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseType save(@RequestBody EventCategoryModal category, HttpServletRequest request) throws FileNotFoundException, IOException {

		category.setBackgroundUrl(util.uploadFile(category.getBackgroundUrl()));
		category.setBannerUrl(util.uploadFile(category.getBannerUrl()));
		List<EventCategoryModal> dup = evetCategoryModalRepository.findByName(category.getName());
		if (dup != null && !dup.isEmpty()) {
			if (!dup.get(0).getId().equals(category.getId())) {
				return util.response(WHStatus.FAILURE.value(), "Duplicate Category", category);
			}
		}
		category = evetCategoryModalRepository.save(category);
		return util.response(WHStatus.SUCCESS.value(), "", category);
	}

	@Transactional
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseType delete(@RequestParam("id") int id) throws Exception {
		List<Event> eventByCategory = eventCategoryRepository.getEventByCategory(id);
		if (!eventByCategory.isEmpty()) {
			return util.response(WHStatus.FAILURE.value(), "You can't delete this category. It has mapped with some events", "");
		}
		EventCategoryModal category = evetCategoryModalRepository.findOne(id);
		List<EventCategory> eventCategories = eventCategoryRepository.getEventCategoryByCategory(id);
		whCommon.deleteEventCategories(eventCategories);
		List<CustomerFavoriteCategory> customerFavoriteCategories = category.getCustomerFavoriteCategoryList();
		if (!customerFavoriteCategories.isEmpty()) {
			whCommon.deleteCustomerFavoriteCategories(customerFavoriteCategories);
		}
		evetCategoryModalRepository.delete(id);
		return util.response(WHStatus.SUCCESS.value(), "", null);
	}

	@RequestMapping(value = "/vendor-category/{vendorId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType getByVendorId(@PathVariable("vendorId") int vendorId) {
		try {
			return util.response(WHStatus.SUCCESS.value(), "", vendorCategoryRepository.findByVendor(new Vendor(vendorId)));
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), "internal error", "");
		}
	}

	@RequestMapping(value = "/delete-image/{categoryId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseType deleteImage(@PathVariable("categoryId") int categoryId, @RequestParam("property") String property, @RequestParam("uri") String uri) {
		try {
			EventCategoryModal category = evetCategoryModalRepository.findById(categoryId);
			if (property.equalsIgnoreCase("bannerurl")) {
				if (!uri.equalsIgnoreCase(category.getBannerUrl())) {
					return util.response(WHStatus.FAILURE.value(), "invalid resource", "");
				}
				category.setBannerUrl(null);
			}
			if (property.equalsIgnoreCase("backgroundurl")) {
				if (!uri.equalsIgnoreCase(category.getBackgroundUrl())) {
					return util.response(WHStatus.FAILURE.value(), "invalid resource", "");
				}
				category.setBackgroundUrl(null);
			}
			boolean deleteFile = util.deleteFile(uri);
			evetCategoryModalRepository.save(category);
			return util.response(WHStatus.SUCCESS.value(), "", deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), "internal error", "");
		}
	}

}
