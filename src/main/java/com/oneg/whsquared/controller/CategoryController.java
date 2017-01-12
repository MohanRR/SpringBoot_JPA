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

import com.oneg.whsquared.entity.Category;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.Vendor;
import com.oneg.whsquared.entity.VendorCategory;
import com.oneg.whsquared.repository.CategoryRepository;
import com.oneg.whsquared.repository.EventCategoryRepository;
import com.oneg.whsquared.repository.VendorCategoryRepository;
import com.oneg.whsquared.security.JwtUtil;
import com.oneg.whsquared.util.ResponseType;
import com.oneg.whsquared.util.Util;
import com.oneg.whsquared.util.WHStatus;

/**
 * @author Anbukkani G
 * 
 */
@RestController
@RequestMapping(value = "api/category")
public class CategoryController {

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	private Util util;

	@Autowired
	private WHCommon whCommon;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	EventCategoryRepository eventCategoryRepository;

	@Autowired
	VendorCategoryRepository vendorCategoryRepository;

	@Transactional
	@RequestMapping(value = "/listall", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType getCategories() throws Exception {

		// List<Category> listOfCategories = (List<Category>)
		// categoryRepository.findAll();
		// int totalSize = listOfCategories.size();
		List<Category> categories = categoryRepository.findAll();
		return util.response(WHStatus.SUCCESS.value(), "", categories);
	}

	@Transactional
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType getCategories(@RequestParam("page") int page, @RequestParam("size") int size) throws Exception {
		PageRequest request = new PageRequest(page, size);
		// List<Category> listOfCategories = (List<Category>)
		// categoryRepository.findAll();
		// int totalSize = listOfCategories.size();
		Page<Category> categories = null;
		categories = categoryRepository.findAll(request);
		return util.response(WHStatus.SUCCESS.value(), "", categories);
	}

	@Transactional
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType search(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("searchText") String searchText) throws Exception {
		PageRequest request = new PageRequest(page, size);
		// List<Category> listOfCategories = (List<Category>)
		// categoryRepository.findAll();
		// int totalSize = listOfCategories.size();
		Page<Category> categories = null;
		if (searchText == null || searchText.equalsIgnoreCase("\"\"")) {
			categories = categoryRepository.findAll(request);
		} else {
			categories = categoryRepository.searchByName(searchText, request);
		}
		return util.response(WHStatus.SUCCESS.value(), "", categories);
	}

	@Transactional
	@RequestMapping(value = "/get-by-id", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType getById(@RequestParam("id") int id) throws Exception {
		Category category = categoryRepository.findById(id);
		return util.response(WHStatus.SUCCESS.value(), "", category);
	}

	@Transactional
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseType save(@RequestBody Category category, HttpServletRequest request) throws FileNotFoundException, IOException {

		category.setBackgroundUrl(util.uploadFile(category.getBackgroundUrl()));
		category.setBannerUrl(util.uploadFile(category.getBannerUrl()));
		Category dup = categoryRepository.findByName(category.getName());
		if (dup != null && category.getId()==null) {
			return util.response(WHStatus.FAILURE.value(), "The category was already exist.", category);
		}
		category = categoryRepository.save(category);
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
		Category category = categoryRepository.findOne(id);
		List<VendorCategory> vendorCategories = vendorCategoryRepository.getVendorCategoryByCategory(id);
		whCommon.deleteVendorCategories(vendorCategories);
		/*
		 * List<CustomerFavoriteCategory> customerFavoriteCategories =
		 * category.getCustomerFavoriteCategoryList(); if
		 * (!customerFavoriteCategories.isEmpty()) {
		 * whCommon.deleteCustomerFavoriteCategories
		 * (customerFavoriteCategories); }
		 */
		categoryRepository.delete(id);
		return util.response(WHStatus.SUCCESS.value(), "", null);
	}

	// @Transactional
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// public @ResponseBody ResponseType update(@RequestBody Category category,
	// HttpRequest request) {
	// // category.setCreatedBy("");
	// // category.setModificationTime(new Date());
	// category = categoryRepository.save(category);
	// return util.response(WHStatus.SUCCESS.value(), "", category);
	// }
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
			Category category = categoryRepository.findById(categoryId);
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
			categoryRepository.save(category);
			return util.response(WHStatus.SUCCESS.value(), "", deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), "internal error", "");
		}
	}
}
