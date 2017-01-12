/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class CustomerFavouriteDTO {

	private Integer id;

	private String categoryName;

	private String eventName;

	private String eventImage;

	private String eventImageUrl;

	/**
	 * @param id
	 * @param categoryName
	 * @param eventName
	 * @param eventImage
	 */
	public CustomerFavouriteDTO(Integer id, String categoryName, String eventName, String eventImage) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.eventName = eventName;
		this.eventImage = eventImage;
	}

	/**
	 * @param id
	 * @param categoryName
	 * @param eventName
	 * @param eventImage
	 * @param eventImageUrl
	 */
	public CustomerFavouriteDTO(Integer id, String categoryName, String eventName, String eventImage, String eventImageUrl) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.eventName = eventName;
		this.eventImage = eventImage;
		this.eventImageUrl = eventImageUrl;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	 * @return the eventImage
	 */
	public String getEventImage() {
		return eventImage;
	}

	/**
	 * @param eventImage
	 *            the eventImage to set
	 */
	public void setEventImage(String eventImage) {
		this.eventImage = eventImage;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerFavouriteDTO [id=" + id + ", categoryName=" + categoryName + ", eventName=" + eventName + ", eventImage=" + eventImage + ", eventImageUrl=" + eventImageUrl
				+ "]";
	}

}
