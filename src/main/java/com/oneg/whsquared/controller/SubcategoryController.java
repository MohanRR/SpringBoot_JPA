package com.oneg.whsquared.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.entity.Category;
import com.oneg.whsquared.entity.SubCategory;
import com.oneg.whsquared.repository.CategoryRepository;
import com.oneg.whsquared.repository.EventCategoryRepository;
import com.oneg.whsquared.repository.SubCategoryRepository;
import com.oneg.whsquared.security.JwtUtil;
import com.oneg.whsquared.util.ResponseType;
import com.oneg.whsquared.util.Util;
import com.oneg.whsquared.util.WHStatus;

@RestController
@RequestMapping(value = "api/subCategory")
public class SubcategoryController {
	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	private Util util;

	@Autowired
	private WHCommon whCommon;

	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	EventCategoryRepository eventCategoryRepository;

	@Transactional
	@RequestMapping(value = "/listall", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType getCategories() throws Exception {
		List<SubCategory> subCategories = subCategoryRepository.findAll();
		return util.response(WHStatus.SUCCESS.value(), "", subCategories);
	}

	@Transactional
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType getCategories(@RequestParam("page") int page, @RequestParam("size") int size) throws Exception {
		PageRequest request = new PageRequest(page, size);
		Page<SubCategory> subCategories = null;
		subCategories = subCategoryRepository.findAll(request);
		JSONObject jObj = generateSubcategoryListJSON(subCategories);

		return util.response(WHStatus.SUCCESS.value(), "", jObj);
	}

	@Transactional
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType search(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("searchText") String searchText) throws Exception {
		PageRequest request = new PageRequest(page, size);
		Page<SubCategory> categories = null;
		if (searchText == null || searchText.equalsIgnoreCase("\"\"")) {
			categories = subCategoryRepository.findAll(request);
		} else {
			categories = subCategoryRepository.searchByName(searchText, request);
		}
		JSONObject jObj = generateSubcategoryListJSON(categories);
		return util.response(WHStatus.SUCCESS.value(), "", jObj);
	}

	@Transactional
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType getById(@RequestParam("id") int id) throws Exception {
		SubCategory subCategory = subCategoryRepository.findById(id);
		JSONObject jObj = new JSONObject();
		JSONObject jCategoryObj = new JSONObject();
		jObj.put("id", subCategory.getId());
		jObj.put("name", subCategory.getName());
		jCategoryObj.put("id", subCategory.getCategory().getId());
		jObj.put("category", jCategoryObj);
		return util.response(WHStatus.SUCCESS.value(), "", jObj);
	}

	@Transactional
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	ResponseType save(@RequestBody SubCategory subCategory, HttpServletRequest request) throws FileNotFoundException, IOException {
		SubCategory data = null;
		if (subCategory != null && subCategory.getCategory().getId() != null && subCategory.getCategory().getId() > 0) {
			Category category = new Category();
			category.setId(subCategory.getCategory().getId());
			subCategory.setCategory(category);
			SubCategory isExistingSubcategory = subCategoryRepository.findByNameAndCategory(subCategory.getCategory().getId(), subCategory.getName());
			if (isExistingSubcategory != null && subCategory.getId() == null) {
				return util.response(WHStatus.FAILURE.value(), "Subcategory already exist in this category.", subCategory);
			}
			data = subCategoryRepository.save(subCategory);
			return util.response(WHStatus.SUCCESS.value(), "", data);
		}
		return util.response(WHStatus.FAILURE.value(), "Please select the Category from list", subCategory);
	}

	@Transactional
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseType delete(@RequestParam("id") int id) throws Exception {
		subCategoryRepository.delete(id);
		return util.response(WHStatus.SUCCESS.value(), "", null);
	}

	public JSONObject generateSubcategoryListJSON(Page<SubCategory> subCategories) {
		JSONObject jObj = new JSONObject();
		JSONArray jArray = new JSONArray();
                if (subCategories != null && subCategories.getSize() > 0) {
                        for (SubCategory subCategory : subCategories) {
                                JSONObject jSubObj = new JSONObject();
                                jSubObj.put("id", subCategory.getId());
                                jSubObj.put("name", subCategory.getName());
                                jSubObj.put("categoryName", subCategory.getCategory().getName());
                                jArray.add(jSubObj);
                        }
                        jObj.put("content", jArray);
                        jObj.put("totalElements", subCategories.getTotalElements());
                        jObj.put("last", subCategories.isLast());
                        jObj.put("totalPages", subCategories.getTotalPages());
                        jObj.put("size", subCategories.getSize());
                        jObj.put("number", subCategories.getNumber());
                        jObj.put("sort", subCategories.getSort());
                        jObj.put("numberOfElements", subCategories.getNumberOfElements());
                        jObj.put("first", subCategories.isFirst());
                        
                }
                 else {
                        jObj.put("users", jArray);
                        jObj.put("first", true);
                        jObj.put("last", true);
                        jObj.put("totalElements", 0);
                        jObj.put("totalPages", 1);
                        jObj.put("size", 0);
                        jObj.put("number", null);
                        jObj.put("sort", null);
                        jObj.put("numberOfElements", 0);
			}
                
                return jObj;
	}
}
