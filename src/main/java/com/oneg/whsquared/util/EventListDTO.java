/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class EventListDTO {

	private Integer eventId;

	private String eventName;

	private String offerTime;

	private String eventShortDescription;

	private String imageUrl;

	private String vendorName;

	private String eventTime;

	private String eventDate;

	public EventListDTO() {
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param offerTime
	 * @param eventShortDescription
	 * @param imageUrl
	 */
	public EventListDTO(Integer eventId, String eventName, String offerTime, String eventShortDescription, String imageUrl) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.offerTime = offerTime;
		this.eventShortDescription = eventShortDescription;
		this.imageUrl = imageUrl;
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param offerTime
	 * @param eventShortDescription
	 * @param imageUrl
	 * @param vendorName
	 * @param eventTime
	 * @param eventDate
	 */
	public EventListDTO(Integer eventId, String eventName, String offerTime, String eventShortDescription, String imageUrl, String vendorName, String eventTime, String eventDate) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.offerTime = offerTime;
		this.eventShortDescription = eventShortDescription;
		this.imageUrl = imageUrl;
		this.vendorName = vendorName;
		this.eventTime = eventTime;
		this.eventDate = eventDate;
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param offerTime
	 * @param eventShortDescription
	 * @param imageUrl
	 * @param vendorName
	 */
	public EventListDTO(Integer eventId, String eventName, String offerTime, String eventShortDescription, String imageUrl, String vendorName) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.offerTime = offerTime;
		this.eventShortDescription = eventShortDescription;
		this.imageUrl = imageUrl;
		this.vendorName = vendorName;
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
	 * @return the eventShortDescription
	 */
	public String getEventShortDescription() {
		return eventShortDescription;
	}

	/**
	 * @param eventShortDescription
	 *            the eventShortDescription to set
	 */
	public void setEventShortDescription(String eventShortDescription) {
		this.eventShortDescription = eventShortDescription;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	 * @return the eventTime
	 */
	public String getEventTime() {
		return eventTime;
	}

	/**
	 * @param eventTime
	 *            the eventTime to set
	 */
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
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
		return "EventListDTO [eventId=" + eventId + ", eventName=" + eventName + ", offerTime=" + offerTime + ", eventShortDescription=" + eventShortDescription + ", imageUrl="
				+ imageUrl + "]";
	}

}
