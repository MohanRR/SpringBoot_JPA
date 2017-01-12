/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.oneg.whsquared.util.ImageUtil;

/**
 * 
 * @author Anbukkani G
 */
@Entity
@Table(name = "customer")
@XmlRootElement
public class Customer extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Basic(optional = false)
	@Column(name = "email")
	private String email;

	@Basic(optional = true)
	@Column(name = "phone_number")
	private String phoneNumber;

	@Lob
	@Column(name = "avatar")
	private byte[] avatar;

	private transient String profileImage;

	@Basic(optional = true)
	@Column(name = "profile_image_url", columnDefinition = "text")
	private String profileImageUrl;

	@Basic(optional = false)
	@Column(name = "send_mail_notification")
	private boolean sendMailNotification;

	@Basic(optional = false)
	@Column(name = "send_push_notification")
	private boolean sendPushNotification;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
	private List<CalendarEvent> calendarEventList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
	private List<CustomerFavoriteVendor> customerFavoriteVendorList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
	private List<CustomerFavoriteCategory> customerFavoriteCategoryList;
	@OneToMany(mappedBy = "customerId")
	private List<PasswordVerification> passwordVerificationList;
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	@ManyToOne
	// @JsonManagedReference(value="address-customerList")
	private Address address;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<CustomerNotification> customerNotificationList;

	@Basic(optional = false)
	@Column(name = "register_type")
	private String registerationType;

	@Column(name = "social_media_link")
	private String socialMediaLink;

	@Column(name = "device_id")
	private String deviceId;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Set<Role> roles = new HashSet<>();

	public Customer() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		String imageString = ImageUtil.bytesToBase64(avatar);
		return imageString;
	}

	public void setAvatar(String imageString) {
		byte[] avatar = ImageUtil.Base64ToBytes(imageString);
		this.avatar = avatar;
	}

	public boolean getSendMailNotification() {
		return sendMailNotification;
	}

	public void setSendMailNotification(boolean sendMailNotification) {
		this.sendMailNotification = sendMailNotification;
	}

	public boolean getSendPushNotification() {
		return sendPushNotification;
	}

	public void setSendPushNotification(boolean sendPushNotification) {
		this.sendPushNotification = sendPushNotification;
	}

	@XmlTransient
	public List<CalendarEvent> getCalendarEventList() {
		return calendarEventList;
	}

	public void setCalendarEventList(List<CalendarEvent> calendarEventList) {
		this.calendarEventList = calendarEventList;
	}

	@XmlTransient
	public List<CustomerFavoriteVendor> getCustomerFavoriteVendorList() {
		return customerFavoriteVendorList;
	}

	public void setCustomerFavoriteVendorList(List<CustomerFavoriteVendor> customerFavoriteVendorList) {
		this.customerFavoriteVendorList = customerFavoriteVendorList;
	}

	@XmlTransient
	public List<CustomerFavoriteCategory> getCustomerFavoriteCategoryList() {
		return customerFavoriteCategoryList;
	}

	public void setCustomerFavoriteCategoryList(List<CustomerFavoriteCategory> customerFavoriteCategoryList) {
		this.customerFavoriteCategoryList = customerFavoriteCategoryList;
	}

	@XmlTransient
	public List<PasswordVerification> getPasswordVerificationList() {
		return passwordVerificationList;
	}

	public void setPasswordVerificationList(List<PasswordVerification> passwordVerificationList) {
		this.passwordVerificationList = passwordVerificationList;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@XmlTransient
	public List<CustomerNotification> getCustomerNotificationList() {
		return customerNotificationList;
	}

	public void setCustomerNotificationList(List<CustomerNotification> customerNotificationList) {
		this.customerNotificationList = customerNotificationList;
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
	 * @return the socialMediaLink
	 */
	public String getSocialMediaLink() {
		return socialMediaLink;
	}

	/**
	 * @param socialMediaLink
	 *            the socialMediaLink to set
	 */
	public void setSocialMediaLink(String socialMediaLink) {
		this.socialMediaLink = socialMediaLink;
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
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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

	/**
	 * @return the profileImage
	 */
	public String getProfileImage() {
		return profileImage;
	}

	/**
	 * @param profileImage
	 *            the profileImage to set
	 */
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
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

}
