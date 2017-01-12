/**
 * 
 */
package com.oneg.whsquared.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.EventCategoryModal;
import com.oneg.whsquared.entity.Preference;
import com.oneg.whsquared.repository.CategoryRepository;
import com.oneg.whsquared.repository.CustomerRepository;
import com.oneg.whsquared.repository.EventCategoryModalRepository;
import com.oneg.whsquared.repository.PreferenceRepository;
import com.oneg.whsquared.util.ResponseType;
import com.oneg.whsquared.util.Util;

/**
 * @author Anbukkani G
 * 
 */
@RestController
@RequestMapping(value = "api/preference")
public class PreferenceController {

	Logger log = Logger.getLogger(PreferenceController.class);

	@Autowired
	private PreferenceRepository preferenceRepository;

	@Autowired
	private Util util;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EventCategoryModalRepository eventCategoryModalRepository;

	@Transactional
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseType savePreferences(@Param("customerId") int customerId, @RequestBody List<Integer> categoryIds) throws Exception {
		List<Preference> preferences = new ArrayList<Preference>();
		try {
			preferences = preferenceRepository.findByCustomerId(customerId);
			for (Preference preference : preferences) {
				preferenceRepository.delete(preference);
			}
			for (Integer categoryId : categoryIds) {
				if (categoryId != null) {
					Preference pref = new Preference();
					EventCategoryModal category = eventCategoryModalRepository.findOne(categoryId);
					Customer customer = customerRepository.findOne(customerId);
					if (customer != null && category != null) {
						pref.setCategory(category);
						pref.setCustomer(customer);
						preferenceRepository.save(pref);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
		}
		return util.response("success", "Preferences added successfully ", preferences);
	}
}
