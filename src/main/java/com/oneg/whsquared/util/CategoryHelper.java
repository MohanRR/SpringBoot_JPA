/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.oneg.whsquared.controller.BaseDependecy;
import com.oneg.whsquared.entity.Category;
import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.CustomerFavoriteCategory;
import com.oneg.whsquared.entity.EventCategoryModal;
import com.oneg.whsquared.entity.Preference;
import com.oneg.whsquared.entity.Vendor;

/**
 * @author arivu
 * 
 */
@Component
public class CategoryHelper extends BaseDependecy {

	public ResponseData onCustomerPreferences(Integer customerId, Integer page, Integer size) {
		Customer customer = customerRepository.findOne(customerId);
		if (customer != null) {
			List<CategoryDTO> categoriesFromDb = new ArrayList<CategoryDTO>();
			Page<EventCategoryModal> categories = categoryRepository.findByCustomerPreference(customer, new PageRequest(page, size));
			if (categories != null && !categories.getContent().isEmpty()) {
				for (EventCategoryModal category : categories) {
					categoriesFromDb.add(onConstructCategoryModelDTO(category));
				}
				return RestMessage.onSuccessMessage(categoriesFromDb);
			} else {
				return helper.constructCategoryDTO();
			}
		} else {
			return RestMessage.onValidationErrorMessage("Customer not found");
		}
	}

	public ResponseData onListAllVendorCategories(Integer page, Integer size) {
		List<CategoryDTO> categoriesFromDb = new ArrayList<CategoryDTO>();
		Page<Category> categories = categoryRepository.findAll(new PageRequest(page, size));
		if (categories != null && !categories.getContent().isEmpty()) {
			for (Category category : categories) {
				categoriesFromDb.add(onConstructCategoryDTO(category));
			}
			if (categoriesFromDb != null && !categoriesFromDb.isEmpty()) {
				return RestMessage.onSuccessMessage(categoriesFromDb);
			} else {
				return RestMessage.onValidationErrorMessage("No Record Found");
			}
		} else {
			return RestMessage.onValidationErrorMessage("No Record Found");
		}
	}

	public ResponseData onDeleteCustomerPreferences(Integer customerId, Integer categoryId) {
		Customer customer = customerRepository.findOne(customerId);
		EventCategoryModal category = eventCategoryModalRepository.findOne(categoryId);
		Preference preference = preferenceRepository.findByCustomerAndCategory(customer, category);
		if (preference != null) {
			preferenceRepository.delete(preference);
			return RestMessage.onSuccess("Deleted successfully.");
		} else {
			return RestMessage.onValidationErrorMessage("Failed to delete.");
		}
	}

	public ResponseData onAddCustomerPreferences(PreferenceCategoryDTO preferenceCategoryDTO) {

		List<Preference> preferences = new ArrayList<Preference>();
		try {
			if (preferenceCategoryDTO.getCustomerId() != null) {
				Customer customerFromDb = customerRepository.findOne(preferenceCategoryDTO.getCustomerId());
				if (customerFromDb != null && customerFromDb.getId() > 0) {
					/**
					 * Delete all preferences of the customer
					 */
					preferences = preferenceRepository.findByCustomerId(preferenceCategoryDTO.getCustomerId());
					if (preferences != null && !preferences.isEmpty()) {
						for (Preference preference : preferences) {
							preferenceRepository.delete(preference);
						}
					}
					/**
					 * If customer's preferences are not found then list all the
					 * preferences
					 */
					if (preferenceCategoryDTO.getCategories() != null && preferenceCategoryDTO.getCategories().size() > 0) {
						for (Integer categoryId : preferenceCategoryDTO.getCategories()) {
							if (categoryId != null) {
								Preference preference = new Preference();
								EventCategoryModal category = eventCategoryModalRepository.findOne(categoryId);
								Customer customer = customerRepository.findOne(preferenceCategoryDTO.getCustomerId());
								if (category != null) {
									if (customer != null) {
										preference.setCategory(category);
										preference.setCustomer(customer);
										preferenceRepository.save(preference);
									} else {
										return new ResponseData("No customer found for this id", false, "");
									}
								} else {
									return new ResponseData("No category found for this id", false, "");
								}

							}
						}
					} else {
						List<EventCategoryModal> categories = (List<EventCategoryModal>) eventCategoryModalRepository.findAll();
						for (EventCategoryModal category : categories) {
							Preference preference = new Preference();
							Customer customer = customerRepository.findOne(preferenceCategoryDTO.getCustomerId());
							if (customer != null && category != null) {
								preference.setCategory(category);
								preference.setCustomer(customer);
								preferenceRepository.save(preference);
							}
						}
					}
				} else {
					return RestMessage.onValidationErrorMessage("Customer not found");
				}
			} else {
				return RestMessage.onValidationErrorMessage("CustomerId is empty");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return RestMessage.onSuccess();

	}

	public ResponseData onListAllCategories(Integer page, Integer size) {
		List<CategoryDTO> categoriesFromDb = new ArrayList<CategoryDTO>();
		Page<EventCategoryModal> categories = eventCategoryModalRepository.findAll(new PageRequest(page, size));
		// Page<Category> categories = categoryRepository.findAll(new
		// PageRequest(page, size));
		if (categories != null && !categories.getContent().isEmpty()) {
			for (EventCategoryModal category : categories) {
				categoriesFromDb.add(onConstructCategoryModelDTO(category));
			}
			if (categoriesFromDb != null && !categoriesFromDb.isEmpty()) {
				return RestMessage.onSuccessMessage(categoriesFromDb);
			} else {
				return RestMessage.onValidationErrorMessage("No Record Found");
			}
		} else {
			return RestMessage.onValidationErrorMessage("No Record Found");
		}
	}

	public ResponseData onVendorsByCategory(Integer categoryId, String name) {
		List<VendorDTO> vendorDTOs = new ArrayList<>();
		List<Vendor> vendors = new ArrayList<Vendor>();
		if (categoryId != null) {
			vendors = vendorCategoryRepository.getVendorsByCategory(categoryId);
		} else if (name != null && !name.isEmpty()) {
			vendors = vendorRepository.searchByName(name);
		}
		if (vendors != null && !vendors.isEmpty()) {
			for (Vendor vendor : vendors) {
				vendorDTOs.add(new VendorDTO(vendor.getId(), vendor.getName().trim(), vendor.getVendorDetail().getLogoUrl()));
			}
			return RestMessage.onSuccessMessage(vendorDTOs);
		} else {
			return RestMessage.onValidationErrorMessage("No Data");
		}
	}

	public ResponseData onCustomerCategory(Integer categoryId, Integer customerId) {
		EventCategoryModal category = eventCategoryModalRepository.findOne(categoryId);
		Customer customer = customerRepository.findOne(customerId);
		if (category != null) {
			CustomerFavoriteCategory categoryFromDb = customerFavoriteCategoryRepository.findByCategoryIdAndCustomerId(category, customer);
			boolean categoryStatus = false;
			if (categoryFromDb != null) {
				categoryStatus = true;
			}
			return RestMessage
					.onSuccessMessage(new CategoryDTO(category.getId(), category.getName(), "", "", category.getBannerUrl(), category.getBackgroundUrl(), categoryStatus));
		} else {
			return RestMessage.onValidationErrorMessage("No category Found");
		}
	}

	private CategoryDTO onConstructCategoryModelDTO(EventCategoryModal category) {
		return new CategoryDTO(category.getId(), category.getName(), "", "", category.getBannerUrl(), category.getBackgroundUrl());
	}

	private CategoryDTO onConstructCategoryDTO(Category category) {
		return new CategoryDTO(category.getId(), category.getName().trim(), "", "", category.getBannerUrl(), category.getBackgroundUrl());
	}
}
