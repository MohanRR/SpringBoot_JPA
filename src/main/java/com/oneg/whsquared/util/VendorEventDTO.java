/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author arivu
 * 
 */
public class VendorEventDTO {

	private Integer eventId;

	private String eventName;

	private String eventImageUrl;

	private String shortDescription;

	private String offerTime;

	private String eventDate;

	private List<EventAddressDTO> addressDTOs = new ArrayList<EventAddressDTO>();

	public VendorEventDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param addressDTOs
	 */
	public VendorEventDTO(Integer eventId, String eventName, List<EventAddressDTO> addressDTOs) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.addressDTOs = addressDTOs;
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param shortDescription
	 * @param offerTime
	 * @param addressDTOs
	 */
	public VendorEventDTO(Integer eventId, String eventName, String shortDescription, String offerTime, List<EventAddressDTO> addressDTOs) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.shortDescription = shortDescription;
		this.offerTime = offerTime;
		this.addressDTOs = addressDTOs;
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param eventImageUrl
	 * @param shortDescription
	 * @param offerTime
	 * @param addressDTOs
	 */
	public VendorEventDTO(Integer eventId, String eventName, String eventImageUrl, String shortDescription, String offerTime, List<EventAddressDTO> addressDTOs, String eventDate) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventImageUrl = eventImageUrl;
		this.shortDescription = shortDescription;
		this.offerTime = offerTime;
		this.addressDTOs = addressDTOs;
		this.eventDate = eventDate;
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param eventImageUrl
	 * @param shortDescription
	 * @param offerTime
	 * @param eventDate
	 * @param addressDTOs
	 */
	public VendorEventDTO(Integer eventId, String eventName, String eventImageUrl, String shortDescription, String offerTime, String eventDate, List<EventAddressDTO> addressDTOs) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventImageUrl = eventImageUrl;
		this.shortDescription = shortDescription;
		this.offerTime = offerTime;
		this.eventDate = eventDate;
		this.addressDTOs = addressDTOs;
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
	 * @return the addressDTOs
	 */
	public List<EventAddressDTO> getAddressDTOs() {
		return addressDTOs;
	}

	/**
	 * @param addressDTOs
	 *            the addressDTOs to set
	 */
	public void setAddressDTOs(List<EventAddressDTO> addressDTOs) {
		this.addressDTOs = addressDTOs;
	}

	/**
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @param shortDescription
	 *            the shortDescription to set
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
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
		return "VendorEventDTO [eventId=" + eventId + ", eventName=" + eventName + ", addressDTOs=" + addressDTOs + "]";
	}

}
