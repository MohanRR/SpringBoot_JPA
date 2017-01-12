/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.oneg.whsquared.controller.BaseDependecy;
import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.CustomerFavoriteCategory;
import com.oneg.whsquared.entity.EventCategoryModal;
import com.oneg.whsquared.entity.Preference;

/**
 * @author arivu
 * 
 */
@Component
public class CustomerPreferenceHelper extends BaseDependecy {

	public ResponseData onCustomerFavorite(FavoriteCategoryDTO favoriteCategoryDTO) {
		Customer customer = customerRepository.findOne(favoriteCategoryDTO.getCustomerId());
		EventCategoryModal category = eventCategoryModalRepository.findOne(favoriteCategoryDTO.getCategoryId());
		CustomerFavoriteCategory customerFavoriteCategory = customerFavoriteCategoryRepository.findByCategoryIdAndCustomerId(category, customer);
		if (customerFavoriteCategory == null) {
			customerFavoriteCategoryRepository.save(new CustomerFavoriteCategory(category, customer));
			preferenceRepository.save(new Preference(category, customer));
			return RestMessage.onSuccess("Added Successfully");
		} else {
			return RestMessage.onValidationErrorMessage("Category has been added already for this customer");
		}
	}

	public ResponseData onCustomerFavoriteCategories(Integer customerId) {
		return constructCategoryDTO(customerFavoriteCategoryRepository.findByCustomerId(customerRepository.findOne(customerId)));
	}

	public ResponseData onUnFavoriteCategory(FavoriteCategoryDTO favoriteCategoryDTO) {
		Customer customer = customerRepository.findOne(favoriteCategoryDTO.getCustomerId());
		EventCategoryModal category = eventCategoryModalRepository.findOne(favoriteCategoryDTO.getCategoryId());
		CustomerFavoriteCategory customerFavoriteCategory = customerFavoriteCategoryRepository.findByCategoryIdAndCustomerId(category, customer);
		if (customerFavoriteCategory != null) {
			customerFavoriteCategoryRepository.deleteByCategoryAndCustomerId(category, customer);
			Preference preference = preferenceRepository.findByCustomerAndCategory(customer, category);
			if (preference != null) {
				preferenceRepository.delete(preference);
			}
			return RestMessage.onSuccess("Deleted Successfully");
		} else {
			return RestMessage.onValidationErrorMessage("Category has been added already for this customer");
		}
	}

	public ResponseData constructCategoryDTO(List<CustomerFavoriteCategory> customerFavoriteCategories) {
		List<PreferenceDTO> preferenceForCustomer = new ArrayList<PreferenceDTO>();
		for (CustomerFavoriteCategory category : customerFavoriteCategories) {
			PreferenceDTO preferenceDTO = new PreferenceDTO();
			preferenceDTO.setCategoryName(category.getCategoryId().getName());
			preferenceDTO.setId(category.getCategoryId().getId());
			preferenceDTO.setImageUrl(category.getCategoryId().getBackgroundUrl());
			preferenceForCustomer.add(preferenceDTO);
		}
		return RestMessage.onSuccessMessage(preferenceForCustomer);
	}

	public ResponseData onCustomerPreferedCategories(Integer customerId) {
		List<PreferenceDTO> preferenceForCustomer = new ArrayList<PreferenceDTO>();
		if (customerId != null) {
			List<Preference> preferences = preferenceRepository.findByCustomerId(customerId);
			if (preferences != null && !preferences.isEmpty()) {
				preferences = preferenceRepository.findByCustomerId(customerId);
				if (preferences != null && !preferences.isEmpty()) {
					for (Preference preference : preferences) {
						preferenceForCustomer.add(new PreferenceDTO(preference.getId(), preference.getCategory().getName(), preference.getCategory().getBackgroundUrl()));
					}
					return RestMessage.onSuccessMessage(preferenceForCustomer);
				} else {
					return RestMessage.onValidationErrorMessage("No Preference found");
				}
			} else {
				return helper.constructCategoryDTO();
			}
		}
		return RestMessage.onSuccess("Customer not found");
	}

}
