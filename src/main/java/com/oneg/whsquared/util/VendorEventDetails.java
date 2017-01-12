/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.List;

/**
 * @author arivu
 * 
 */
public class VendorEventDetails {

	private Integer eventId;

	private String eventName;

	private String eventImageUrl;

	private String eventDescription;

	private Integer vendorId;

	private String vendorName;

	private String vendorImageUrl;

	private String phoneNumber;

	private boolean eventStatus;

	private String offerTime;

	private List<VendorEventCategoryDTO> categoryDTOs;

	private List<VendorAddressDTO> addressDTOs;

	private String eventDate;

	public VendorEventDetails() {

	}

	/**
	 * @param eventImageUrl
	 * @param eventDescription
	 * @param vendorName
	 * @param vendorImageUrl
	 * @param phoneNumber
	 */
	public VendorEventDetails(String eventImageUrl, String eventDescription, String vendorName, String vendorImageUrl, String phoneNumber) {
		super();
		this.eventImageUrl = eventImageUrl;
		this.eventDescription = eventDescription;
		this.vendorName = vendorName;
		this.vendorImageUrl = vendorImageUrl;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @param eventImageUrl
	 * @param eventDescription
	 * @param vendorId
	 * @param vendorName
	 * @param vendorImageUrl
	 * @param phoneNumber
	 * @param addressDTOs
	 */
	public VendorEventDetails(String eventImageUrl, String eventDescription, Integer vendorId, String vendorName, String vendorImageUrl, String phoneNumber,
			List<VendorAddressDTO> addressDTOs) {
		super();
		this.eventImageUrl = eventImageUrl;
		this.eventDescription = eventDescription;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorImageUrl = vendorImageUrl;
		this.phoneNumber = phoneNumber;
		this.addressDTOs = addressDTOs;
	}

	/**
	 * @param eventImageUrl
	 * @param eventDescription
	 * @param vendorId
	 * @param vendorName
	 * @param vendorImageUrl
	 * @param phoneNumber
	 * @param categoryDTOs
	 * @param addressDTOs
	 */
	public VendorEventDetails(String eventImageUrl, String eventDescription, Integer vendorId, String vendorName, String vendorImageUrl, String phoneNumber,
			List<VendorEventCategoryDTO> categoryDTOs, List<VendorAddressDTO> addressDTOs) {
		super();
		this.eventImageUrl = eventImageUrl;
		this.eventDescription = eventDescription;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorImageUrl = vendorImageUrl;
		this.phoneNumber = phoneNumber;
		this.categoryDTOs = categoryDTOs;
		this.addressDTOs = addressDTOs;
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param eventImageUrl
	 * @param eventDescription
	 * @param vendorId
	 * @param vendorName
	 * @param vendorImageUrl
	 * @param phoneNumber
	 * @param addressDTOs
	 */
	public VendorEventDetails(Integer eventId, String eventName, String eventImageUrl, String eventDescription, Integer vendorId, String vendorName, String vendorImageUrl,
			String phoneNumber, List<VendorAddressDTO> addressDTOs) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventImageUrl = eventImageUrl;
		this.eventDescription = eventDescription;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorImageUrl = vendorImageUrl;
		this.phoneNumber = phoneNumber;
		this.addressDTOs = addressDTOs;
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param eventImageUrl
	 * @param eventDescription
	 * @param vendorId
	 * @param vendorName
	 * @param vendorImageUrl
	 * @param phoneNumber
	 * @param eventStatus
	 * @param offerTime
	 * @param addressDTOs
	 */
	public VendorEventDetails(Integer eventId, String eventName, String eventImageUrl, String eventDescription, Integer vendorId, String vendorName, String vendorImageUrl,
			String phoneNumber, boolean eventStatus, String offerTime, List<VendorAddressDTO> addressDTOs, String eventDate) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventImageUrl = eventImageUrl;
		this.eventDescription = eventDescription;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorImageUrl = vendorImageUrl;
		this.phoneNumber = phoneNumber;
		this.eventStatus = eventStatus;
		this.offerTime = offerTime;
		this.addressDTOs = addressDTOs;
		this.eventDate = eventDate;
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param eventImageUrl
	 * @param eventDescription
	 * @param vendorId
	 * @param vendorName
	 * @param vendorImageUrl
	 * @param phoneNumber
	 * @param eventStatus
	 * @param offerTime
	 * @param categoryDTOs
	 * @param addressDTOs
	 */
	public VendorEventDetails(Integer eventId, String eventName, String eventImageUrl, String eventDescription, Integer vendorId, String vendorName, String vendorImageUrl,
			String phoneNumber, boolean eventStatus, String offerTime, List<VendorEventCategoryDTO> categoryDTOs, List<VendorAddressDTO> addressDTOs) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventImageUrl = eventImageUrl;
		this.eventDescription = eventDescription;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorImageUrl = vendorImageUrl;
		this.phoneNumber = phoneNumber;
		this.eventStatus = eventStatus;
		this.offerTime = offerTime;
		this.categoryDTOs = categoryDTOs;
		this.addressDTOs = addressDTOs;
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param eventImageUrl
	 * @param eventDescription
	 * @param vendorId
	 * @param vendorName
	 * @param vendorImageUrl
	 * @param phoneNumber
	 * @param categoryDTOs
	 * @param addressDTOs
	 */
	public VendorEventDetails(Integer eventId, String eventName, String eventImageUrl, String eventDescription, Integer vendorId, String vendorName, String vendorImageUrl,
			String phoneNumber, List<VendorEventCategoryDTO> categoryDTOs, List<VendorAddressDTO> addressDTOs) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventImageUrl = eventImageUrl;
		this.eventDescription = eventDescription;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorImageUrl = vendorImageUrl;
		this.phoneNumber = phoneNumber;
		this.categoryDTOs = categoryDTOs;
		this.addressDTOs = addressDTOs;
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param eventImageUrl
	 * @param eventDescription
	 * @param vendorId
	 * @param vendorName
	 * @param vendorImageUrl
	 * @param phoneNumber
	 * @param eventStatus
	 * @param offerTime
	 * @param categoryDTOs
	 * @param addressDTOs
	 * @param eventDate
	 */
	public VendorEventDetails(Integer eventId, String eventName, String eventImageUrl, String eventDescription, Integer vendorId, String vendorName, String vendorImageUrl,
			String phoneNumber, boolean eventStatus, String offerTime, List<VendorEventCategoryDTO> categoryDTOs, List<VendorAddressDTO> addressDTOs, String eventDate) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventImageUrl = eventImageUrl;
		this.eventDescription = eventDescription;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorImageUrl = vendorImageUrl;
		this.phoneNumber = phoneNumber;
		this.eventStatus = eventStatus;
		this.offerTime = offerTime;
		this.categoryDTOs = categoryDTOs;
		this.addressDTOs = addressDTOs;
		this.eventDate = eventDate;
	}

	/**
	 * @return the eventImageUrl
	 */
	public String getEventImageUrl() {
		return eventImageUrl;
	}

	/**
	 * @param eventImageUrl
	 *            the eventImageUrl to set
	 */
	public void setEventImageUrl(String eventImageUrl) {
		this.eventImageUrl = eventImageUrl;
	}

	/**
	 * @return the eventDescription
	 */
	public String getEventDescription() {
		return eventDescription;
	}

	/**
	 * @param eventDescription
	 *            the eventDescription to set
	 */
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * @param vendorName
	 *            the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * @return the vendorImageUrl
	 */
	public String getVendorImageUrl() {
		return vendorImageUrl;
	}

	/**
	 * @param vendorImageUrl
	 *            the vendorImageUrl to set
	 */
	public void setVendorImageUrl(String vendorImageUrl) {
		this.vendorImageUrl = vendorImageUrl;
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
	 * @return the addressDTOs
	 */
	public List<VendorAddressDTO> getAddressDTOs() {
		return addressDTOs;
	}

	/**
	 * @param addressDTOs
	 *            the addressDTOs to set
	 */
	public void setAddressDTOs(List<VendorAddressDTO> addressDTOs) {
		this.addressDTOs = addressDTOs;
	}

	/**
	 * @return the vendorId
	 */
	public Integer getVendorId() {
		return vendorId;
	}

	/**
	 * @param vendorId
	 *            the vendorId to set
	 */
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	/**
	 * @return the categoryDTOs
	 */
	public List<VendorEventCategoryDTO> getCategoryDTOs() {
		return categoryDTOs;
	}

	/**
	 * @param categoryDTOs
	 *            the categoryDTOs to set
	 */
	public void setCategoryDTOs(List<VendorEventCategoryDTO> categoryDTOs) {
		this.categoryDTOs = categoryDTOs;
	}

	/**
	 * @return the eventId
	 */
	public Integer getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName
	 *            the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the eventStatus
	 */
	public boolean isEventStatus() {
		return eventStatus;
	}

	/**
	 * @param eventStatus
	 *            the eventStatus to set
	 */
	public void setEventStatus(boolean eventStatus) {
		this.eventStatus = eventStatus;
	}

	/**
	 * @return the offerTime
	 */
	public String getOfferTime() {
		return offerTime;
	}

	/**
	 * @param offerTime
	 *            the offerTime to set
	 */
	public void setOfferTime(String offerTime) {
		this.offerTime = offerTime;
	}

	/**
	 * @return the eventDate
	 */
	public String getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate
	 *            the eventDate to set
	 */
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VendorEventDetails [eventImageUrl=" + eventImageUrl + ", eventDescription=" + eventDescription + ", vendorName=" + vendorName + ", vendorImageUrl="
				+ vendorImageUrl + ", phoneNumber=" + phoneNumber + "]";
	}

}
