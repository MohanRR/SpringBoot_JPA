package com.oneg.whsquared.util;

/**
 * 
 * @author arivu
 * 
 */
public class UserDTO {

	private Integer user_id;

	private String first_name;

	private String last_name;

	private String name;

	private String email_id;

	private String image;

	private String profile_image_url;

	private String deviceId;

	public UserDTO() {

	}

	/**
	 * @param user_id
	 * @param first_name
	 * @param last_name
	 * @param name
	 * @param email_id
	 * @param image
	 * @param profile_image_url
	 */
	public UserDTO(Integer user_id, String first_name, String last_name, String name, String email_id, String image, String profile_image_url) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.name = name;
		this.email_id = email_id;
		this.image = image;
		this.profile_image_url = profile_image_url;
	}

	/**
	 * @param user_id
	 * @param first_name
	 * @param last_name
	 * @param name
	 * @param email_id
	 * @param image
	 * @param profile_image_url
	 * @param deviceId
	 */
	public UserDTO(Integer user_id, String first_name, String last_name, String name, String email_id, String image, String profile_image_url, String deviceId) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.name = name;
		this.email_id = email_id;
		this.image = image;
		this.profile_image_url = profile_image_url;
		this.deviceId = deviceId;
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
	 * @return the profile_image_url
	 */
	public String getProfile_image_url() {
		return profile_image_url;
	}

	/**
	 * @param profile_image_url
	 *            the profile_image_url to set
	 */
	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId
	 *            the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", name=" + name + ", email_id=" + email_id + ", image=" + image
				+ ", profile_image_url=" + profile_image_url + "]";
	}

}