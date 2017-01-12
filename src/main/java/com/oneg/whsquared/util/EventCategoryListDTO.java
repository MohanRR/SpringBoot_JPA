/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class EventCategoryListDTO {

	private Integer categoryId;

	private Integer eventId;

	private String eventName;

	private String offers;

	private String image;

	private String offerTime;

	public EventCategoryListDTO() {
	}

	public EventCategoryListDTO(Integer eventId, String eventName, String offers, String image, String offerTime) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.offers = offers;
		this.image = image;
		this.offerTime = offerTime;
	}

	/**
	 * @param categoryId
	 * @param eventId
	 * @param eventName
	 * @param offers
	 * @param image
	 * @param offerTime
	 */
	public EventCategoryListDTO(Integer categoryId, Integer eventId, String eventName, String offers, String image, String offerTime) {
		super();
		this.categoryId = categoryId;
		this.eventId = eventId;
		this.eventName = eventName;
		this.offers = offers;
		this.image = image;
		this.offerTime = offerTime;
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
	 * @return the offers
	 */
	public String getOffers() {
		return offers;
	}

	/**
	 * @param offers
	 *            the offers to set
	 */
	public void setOffers(String offers) {
		this.offers = offers;
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
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventCategoryListDTO [categoryId=" + categoryId + ", eventId=" + eventId + ", eventName=" + eventName + ", offers=" + offers + ", image=" + image + ", offerTime="
				+ offerTime + "]";
	}

}
