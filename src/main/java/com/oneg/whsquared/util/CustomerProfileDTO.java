/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.List;

/**
 * @author arivu
 * 
 */
public class CustomerProfileDTO {

	private Integer user_id;

	private String first_name;

	private String last_name;

	private String name;

	private String email_id;

	private String image;

	private String address;

	private String phoneNumber;

	private String registerationType;

	private boolean sendPushNotification;

	private boolean sendMailNotification;;

	private List<ProfileCategoryDTO> preferenceDTOs;

	private String profileImageUrl;

	public CustomerProfileDTO() {

	}

	/**
	 * @param user_id
	 * @param first_name
	 * @param last_name
	 * @param name
	 * @param email_id
	 * @param image
	 * @param preferenceDTOs
	 */
	public CustomerProfileDTO(Integer user_id, String first_name, String last_name, String name, String email_id, String image, List<ProfileCategoryDTO> preferenceDTOs) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.name = name;
		this.email_id = email_id;
		this.image = image;
		this.preferenceDTOs = preferenceDTOs;
	}

	/**
	 * @param user_id
	 * @param first_name
	 * @param last_name
	 * @param name
	 * @param email_id
	 * @param image
	 * @param address
	 * @param preferenceDTOs
	 */
	public CustomerProfileDTO(Integer user_id, String first_name, String last_name, String name, String email_id, String image, String address,
			List<ProfileCategoryDTO> preferenceDTOs) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.name = name;
		this.email_id = email_id;
		this.image = image;
		this.address = address;
		this.preferenceDTOs = preferenceDTOs;
	}

	/**
	 * @param user_id
	 * @param first_name
	 * @param last_name
	 * @param name
	 * @param email_id
	 * @param image
	 * @param address
	 * @param phoneNumber
	 * @param preferenceDTOs
	 */
	public CustomerProfileDTO(Integer user_id, String first_name, String last_name, String name, String email_id, String image, String address, String phoneNumber,
			List<ProfileCategoryDTO> preferenceDTOs) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.name = name;
		this.email_id = email_id;
		this.image = image;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.preferenceDTOs = preferenceDTOs;
	}

	/**
	 * @param user_id
	 * @param first_name
	 * @param last_name
	 * @param name
	 * @param email_id
	 * @param image
	 * @param address
	 * @param phoneNumber
	 * @param registerationType
	 * @param sendPushNotification
	 * @param sendMailNotification
	 * @param preferenceDTOs
	 */
	public CustomerProfileDTO(Integer user_id, String first_name, String last_name, String name, String email_id, String image, String address, String phoneNumber,
			String registerationType, boolean sendPushNotification, boolean sendMailNotification, List<ProfileCategoryDTO> preferenceDTOs) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.name = name;
		this.email_id = email_id;
		this.image = image;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.registerationType = registerationType;
		this.sendPushNotification = sendPushNotification;
		this.sendMailNotification = sendMailNotification;
		this.preferenceDTOs = preferenceDTOs;
	}

	/**
	 * @param preferenceDTOs
	 */
	public CustomerProfileDTO(List<ProfileCategoryDTO> preferenceDTOs) {
		super();
		this.preferenceDTOs = preferenceDTOs;
	}

	/**
	 * @return the preferenceDTOs
	 */
	public List<ProfileCategoryDTO> getPreferenceDTOs() {
		return preferenceDTOs;
	}

	/**
	 * @param preferenceDTOs
	 *            the preferenceDTOs to set
	 */
	public void setPreferenceDTOs(List<ProfileCategoryDTO> preferenceDTOs) {
		this.preferenceDTOs = preferenceDTOs;
	}

	/**
	 * @return the user_id
	 */
	public Integer getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name
	 *            the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name
	 *            the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email_id
	 */
	public String getEmail_id() {
		return email_id;
	}

	/**
	 * @param email_id
	 *            the email_id to set
	 */
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the registerationType
	 */
	public String getRegisterationType() {
		return registerationType;
	}

	/**
	 * @param registerationType
	 *            the registerationType to set
	 */
	public void setRegisterationType(String registerationType) {
		this.registerationType = registerationType;
	}

	/**
	 * @return the sendPushNotification
	 */
	public boolean isSendPushNotification() {
		return sendPushNotification;
	}

	/**
	 * @param sendPushNotification
	 *            the sendPushNotification to set
	 */
	public void setSendPushNotification(boolean sendPushNotification) {
		this.sendPushNotification = sendPushNotification;
	}

	/**
	 * @return the sendMailNotification
	 */
	public boolean isSendMailNotification() {
		return sendMailNotification;
	}

	/**
	 * @param sendMailNotification
	 *            the sendMailNotification to set
	 */
	public void setSendMailNotification(boolean sendMailNotification) {
		this.sendMailNotification = sendMailNotification;
	}

	/**
	 * @return the profileImageUrl
	 */
	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	/**
	 * @param profileImageUrl
	 *            the profileImageUrl to set
	 */
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerProfileDTO [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", name=" + name + ", email_id=" + email_id + ", image="
				+ image + ", address=" + address + ", phoneNumber=" + phoneNumber + ", registerationType=" + registerationType + ", sendPushNotification=" + sendPushNotification
				+ ", sendMailNotification=" + sendMailNotification + ", preferenceDTOs=" + preferenceDTOs + ", profileImageUrl=" + profileImageUrl + "]";
	}

}
