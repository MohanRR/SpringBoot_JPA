/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class EventAgendaDTO {

	private String title;

	private String dateTime;

	private String offers;

	private String eventAgendaImage;

	private String eventAgendaImageUrl;

	public EventAgendaDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param dateTime
	 * @param offers
	 * @param eventAngendaImage
	 */
	public EventAgendaDTO(String title, String dateTime, String offers, String eventAgendaImage) {
		super();
		this.title = title;
		this.dateTime = dateTime;
		this.offers = offers;
		this.eventAgendaImage = eventAgendaImage;
	}

	/**
	 * @param title
	 * @param dateTime
	 * @param offers
	 * @param eventAgendaImage
	 * @param eventAgendaImageUrl
	 */
	public EventAgendaDTO(String title, String dateTime, String offers, String eventAgendaImage, String eventAgendaImageUrl) {
		super();
		this.title = title;
		this.dateTime = dateTime;
		this.offers = offers;
		this.eventAgendaImage = eventAgendaImage;
		this.eventAgendaImageUrl = eventAgendaImageUrl;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the dateTime
	 */
	public String getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime
	 *            the dateTime to set
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
	 * @return the eventAgendaImage
	 */
	public String getEventAgendaImage() {
		return eventAgendaImage;
	}

	/**
	 * @param eventAgendaImage
	 *            the eventAgendaImage to set
	 */
	public void setEventAgendaImage(String eventAgendaImage) {
		this.eventAgendaImage = eventAgendaImage;
	}

	/**
	 * @return the eventAgendaImageUrl
	 */
	public String getEventAgendaImageUrl() {
		return eventAgendaImageUrl;
	}

	/**
	 * @param eventAgendaImageUrl
	 *            the eventAgendaImageUrl to set
	 */
	public void setEventAgendaImageUrl(String eventAgendaImageUrl) {
		this.eventAgendaImageUrl = eventAgendaImageUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventAgendaDTO [title=" + title + ", dateTime=" + dateTime + ", offers=" + offers + ", eventAgendaImage=" + eventAgendaImage + ", eventAgendaImageUrl="
				+ eventAgendaImageUrl + "]";
	}

}
