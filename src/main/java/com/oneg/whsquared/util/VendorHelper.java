/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.oneg.whsquared.controller.BaseDependecy;
import com.oneg.whsquared.entity.Category;
import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.CustomerFavoriteVendor;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventAddress;
import com.oneg.whsquared.entity.SubCategory;
import com.oneg.whsquared.entity.Vendor;
import com.oneg.whsquared.entity.VendorAddress;
import com.oneg.whsquared.entity.VendorCategory;
import com.oneg.whsquared.entity.VendorDetail;

/**
 * @author arivu
 * 
 */
@Component
public class VendorHelper extends BaseDependecy {

	public ResponseData constructVendorDetails(Integer vendorId, Integer customerId) {
		Vendor vendor = vendorRepository.findOne(vendorId);
		VendorDetail vendorDetail = vendor.getVendorDetail();
		VendorDetailDTO vendorDetailDTO = new VendorDetailDTO();
		Customer customer = customerRepository.findOne(customerId);
		if (vendorDetail != null) {
			vendorDetailDTO.setVendorId(vendorDetail.getVendor().getId());
			vendorDetailDTO.setVendorName(vendorDetail.getVendor().getName());
			vendorDetailDTO.setVendorBackgroundUrl(vendorDetail.getBackgroundUrl());
			vendorDetailDTO.setVendorIconUrl(vendorDetail.getLogoUrl());
			vendorDetailDTO.setVendorPhoneNumber(Helper.removeSpecialChars(vendorDetail.getPhoneNumber()));
			vendorDetailDTO.setQuickLink1(vendorDetail.getQuicklink1());
			vendorDetailDTO.setQuickLink2(vendorDetail.getQuicklink2());
			vendorDetailDTO.setQuickLink3(vendorDetail.getQuicklink3());
			vendorDetailDTO.setFaceBookUrl(vendorDetail.getFacebookUrl());
			vendorDetailDTO.setInstagramUrl(vendorDetail.getInstagramUrl());
			vendorDetailDTO.setTwitterUrl(vendorDetail.getTwitterUrl());
			if (vendorDetail.getQuickLinkText1() != null && !vendorDetail.getQuickLinkText1().isEmpty()) {
				vendorDetailDTO.setQuickLinkText1(vendorDetail.getQuickLinkText1());
			} else {
				vendorDetailDTO.setQuickLinkText1("Kids Menu");
			}
			if (vendorDetail.getQuickLinkText2() != null && !vendorDetail.getQuickLinkText2().isEmpty()) {
				vendorDetailDTO.setQuickLinkText2(vendorDetail.getQuickLinkText2());
			} else {
				vendorDetailDTO.setQuickLinkText2("Brunch Menu");
			}
			if (vendorDetail.getQuickLinkText3() != null && !vendorDetail.getQuickLinkText3().isEmpty()) {
				vendorDetailDTO.setQuickLinkText3(vendorDetail.getQuickLinkText3());
			} else {
				vendorDetailDTO.setQuickLinkText3("Photos");
			}
			List<VendorAddress> vendorAddress = vendorAddressRepository.findByVendorId(vendorDetail.getVendor().getId());
			List<CustomerFavoriteVendor> customerFavoriteVendors = customerFavoriteVendorRepository.findByCustomerId(customer);
			if (customerFavoriteVendors != null && !customerFavoriteVendors.isEmpty()) {
				vendorDetailDTO.setVendorStatus(true);
			} else {
				vendorDetailDTO.setVendorStatus(false);
			}
			vendorDetailDTO.setBussinessHours(vendorDetail.getBusinessHours());
			// Get Vendor Categories
			List<VendorEventCategoryDTO> vendorcategoryDTOs = new ArrayList<VendorEventCategoryDTO>();
			List<Category> vendorCategoriesFromDb = new ArrayList<Category>();
			List<VendorCategory> vendorCategories = vendorCategoryRepository.getVendorCategoryByVendor(vendorDetail.getVendor().getId());
			List<SubCategory> subCategories = new ArrayList<SubCategory>();
			for (VendorCategory vendorCategory : vendorCategories) {
				vendorCategoriesFromDb.add(vendorCategory.getCategory());
				subCategories.add(vendorCategory.getSubcategory());
			}

			HashSet<Category> categories = new HashSet<Category>(vendorCategoriesFromDb);
			for (Category category : categories) {
				vendorcategoryDTOs.add(new VendorEventCategoryDTO(category.getId(), category.getName()));
			}

			HashSet<SubCategory> subcategories = new HashSet<SubCategory>(subCategories);
			if (subcategories != null && !subcategories.isEmpty()) {
				for (SubCategory subCategory : subcategories) {
					if (subCategory != null) {
						vendorcategoryDTOs.add(new VendorEventCategoryDTO(subCategory.getId(), subCategory.getName()));
					}
				}
			}
			vendorDetailDTO.setVendorcategoryDTOs(vendorcategoryDTOs);

			// Vendor Events
			List<Event> vendorEvents = vendorDetail.getVendor().getEventList();
			if (vendorEvents != null && !vendorEvents.isEmpty()) {
				vendorDetailDTO.setVendorEventDTOs(constructVendorEventDTOs(vendorEvents));
			}

			if (vendorAddress != null && !vendorAddress.isEmpty()) {
				vendorDetailDTO.setAddressDTOs(helper.constructVendorAddressDTO(vendorAddress));
			}

			return RestMessage.onSuccessMessage(vendorDetailDTO);
		} else {
			return RestMessage.onValidationErrorMessage("No Vendor Found");
		}

	}

	private List<VendorEventDTO> constructVendorEventDTOs(List<Event> vendorEvents) {
		List<VendorEventDTO> vendorEventDTOs = new ArrayList<>();
		for (Event event : vendorEvents) {
			List<EventAddress> addresses = event.getEventAddressList();
			if (event.getEventCalender() != null) {
				vendorEventDTOs.add(new VendorEventDTO(event.getId(), event.getName(), event.getEventDetail().getBackgroundUrl(), event.getShortDescription(), helper
						.constructOfferTime(event.getEventCalender().getStartTime(), event.getEventCalender().getEndTime()), helper.constructEventAddressDTO(addresses), DateUtils
						.formatDateToString(event.getEventCalender().getStartDate())));
			} else {
				vendorEventDTOs.add(new VendorEventDTO(event.getId(), event.getName(), event.getEventDetail().getBackgroundUrl(), event.getShortDescription(), helper
						.constructOfferTime(event.getEventCalender().getStartTime(), event.getEventCalender().getEndTime()), helper.constructEventAddressDTO(addresses), DateUtils
						.formatDateToString(event.getEventCalender().getStartDate())));
			}
		}
		return vendorEventDTOs;
	}

	public ResponseData onVendorFavorite(FavouriteVendor favoriteVendor) {

		if (RestValidation.validateForInteger(favoriteVendor.getCustomerId()) != 0) {
			Customer customer = customerRepository.findOne(favoriteVendor.getCustomerId());
			if (customer != null) {
				Vendor vendor = vendorRepository.findOne(favoriteVendor.getVendorId());
				if (vendor != null) {
					CustomerFavoriteVendor customerFavoriteVendor = customerFavoriteVendorRepository.findByVendorIdAndCustomerId(vendor, customer);
					if (customerFavoriteVendor == null) {
						customerFavoriteVendorRepository.save(new CustomerFavoriteVendor(customer, vendor));
						return RestMessage.onSuccess();
					} else {
						return RestMessage.onValidationErrorMessage("Customer prefered already");
					}
				} else {
					return RestMessage.onValidationErrorMessage("Vendor not found");
				}
			} else {
				return RestMessage.onValidationErrorMessage("Customer not found");
			}
		}
		return RestMessage.onValidationErrorMessage("Customer Id is empty");
	}

	public ResponseData onVendorUnfavorite(FavouriteVendor favoriteVendor) {
		if (RestValidation.validateForInteger(favoriteVendor.getCustomerId()) != 0) {
			Customer customer = customerRepository.findOne(favoriteVendor.getCustomerId());
			if (customer != null) {
				Vendor vendor = vendorRepository.findOne(favoriteVendor.getVendorId());
				if (vendor != null) {
					CustomerFavoriteVendor customerFavoriteVendor = customerFavoriteVendorRepository.findByVendorIdAndCustomerId(vendor, customer);
					if (customerFavoriteVendor != null) {
						customerFavoriteVendorRepository.deleteByEventAndCustomerId(vendor, customer);
						return RestMessage.onSuccess();
					} else {
						return RestMessage.onValidationErrorMessage("No Vendor found for this customer");
					}
				} else {
					return RestMessage.onValidationErrorMessage("Vendor not found");
				}
			} else {
				return RestMessage.onValidationErrorMessage("Customer not found");
			}
		}
		return RestMessage.onValidationErrorMessage("Customer Id is empty");
	}
}
