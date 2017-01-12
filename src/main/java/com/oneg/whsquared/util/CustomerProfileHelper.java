/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;

import com.oneg.whsquared.controller.BaseDependecy;
import com.oneg.whsquared.entity.Address;
import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.EventCategoryModal;

/**
 * @author arivu
 * 
 */
@Component
public class CustomerProfileHelper extends BaseDependecy {

	public ResponseData updateCustomerProfileData(Integer customerId, Device device) {
		Customer customerFromDb = customerRepository.findOne(customerId);
		CustomerProfileDTO userDTO = new CustomerProfileDTO();
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
		userDTO.setFirst_name(RestValidation.validate(customerFromDb.getFirstName()));
		userDTO.setLast_name(RestValidation.validate(customerFromDb.getLastName()));
		userDTO.setEmail_id(RestValidation.validate(customerFromDb.getEmail()));
		userDTO.setProfileImageUrl(customerFromDb.getProfileImageUrl());
		Address address = customerFromDb.getAddress();
		if (address != null && address.getFullAddress() != null && RestValidation.onEmptyvalidation(address.getFullAddress())) {
			userDTO.setAddress(RestValidation.validate(address.getFullAddress()));
		}
		if (customerFromDb.getSendMailNotification()) {
			userDTO.setSendMailNotification(customerFromDb.getSendMailNotification());
		} else {
			userDTO.setSendMailNotification(false);
		}
		if (customerFromDb.getSendPushNotification()) {
			userDTO.setSendPushNotification(customerFromDb.getSendPushNotification());
		} else {
			userDTO.setSendPushNotification(false);
		}
		try {
			if (!Helper.formatPhoneNumber(customerFromDb.getPhoneNumber()).isEmpty())
				userDTO.setPhoneNumber(Helper.formatPhoneNumber(customerFromDb.getPhoneNumber()));
			else
				userDTO.setPhoneNumber("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		userDTO.setRegisterationType(RestValidation.validate(customerFromDb.getRegisterationType()));
		List<EventCategoryModal> categories = eventCategoryModalRepository.findAll();
		List<EventCategoryModal> customerCategories = categoryRepository.findCategoriesByCustomer(customerFromDb);
		List<ProfileCategoryDTO> categoryDTOs = new ArrayList<ProfileCategoryDTO>();
		if (categories != null && !categories.isEmpty()) {
			for (EventCategoryModal categoryFromDb : categories) {
				categoryDTOs.add(new ProfileCategoryDTO(categoryFromDb.getId(), categoryFromDb.getName(), "", false));
			}
			if (customerCategories != null && !customerCategories.isEmpty()) {
				for (ProfileCategoryDTO profileCategoryDTO : categoryDTOs) {
					for (EventCategoryModal category : customerCategories) {
						if (profileCategoryDTO.getCategoryId().intValue() == category.getId().intValue()) {
							profileCategoryDTO.setStatus(true);
							break;
						}
					}
				}
			}
		}
		userDTO.setPreferenceDTOs(categoryDTOs);
		return RestMessage.onSuccessMessage(userDTO);
	}

	public CustomerResponseData onCustomerUpdate(Customer customer) {
		Customer customerFromDb = customerRepository.findOne(customer.getId());
		if (customerFromDb != null) {
			if (customer.getFirstName() != null) {
				customerFromDb.setFirstName(customer.getFirstName());
			}
			if (customer.getLastName() != null) {
				customerFromDb.setLastName(customer.getLastName());
			}
			if (customer.getPhoneNumber() != null) {
				customerFromDb.setPhoneNumber(Helper.removeSpecialChars(customer.getPhoneNumber()));
			}

			if (customer.getPassword() != null)
				customerFromDb.setPassword(DigestUtils.md5Hex(customer.getPassword()));

			customerFromDb.setSendMailNotification(customer.getSendMailNotification());
			customerFromDb.setSendPushNotification(customer.getSendPushNotification());
			customerFromDb.setProfileImageUrl(helper.uploadProfileImage(customer.getAvatar()));
			customerFromDb = customerRepository.save(customerFromDb);
			return updateCustomerProfile(customerFromDb);
		} else {
			return RestMessage.onErrorMessage("Customer not found");
		}

	}

	public CustomerResponseData updateCustomerProfile(Customer customerFromDb) {

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
			responseData.setToken("");
		}
		return responseData;

	}

}
