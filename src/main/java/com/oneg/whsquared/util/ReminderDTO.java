/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class ReminderDTO {

	private Integer customerId;

	private Integer eventId;

	private String dateTime;

	private boolean sendMailReminder;

	private boolean sendPushReminder;

	private String eventTitle;

	private String eventImage;

	private String eventStartDate;

	private String categoryName;

	private String eventImageUrl;

	public ReminderDTO() {

	}

	/**
	 * @param customerId
	 * @param eventId
	 * @param dateTime
	 * @param sendMailReminder
	 * @param sendPushReminder
	 */
	public ReminderDTO(Integer customerId, Integer eventId, String dateTime, boolean sendMailReminder, boolean sendPushReminder) {
		super();
		this.customerId = customerId;
		this.eventId = eventId;
		this.dateTime = dateTime;
		this.sendMailReminder = sendMailReminder;
		this.sendPushReminder = sendPushReminder;
	}

	/**
	 * @param customerId
	 * @param eventId
	 * @param dateTime
	 * @param sendMailReminder
	 * @param sendPushReminder
	 * @param eventTitle
	 */
	public ReminderDTO(Integer customerId, Integer eventId, String dateTime, boolean sendMailReminder, boolean sendPushReminder, String eventTitle) {
		super();
		this.customerId = customerId;
		this.eventId = eventId;
		this.dateTime = dateTime;
		this.sendMailReminder = sendMailReminder;
		this.sendPushReminder = sendPushReminder;
		this.eventTitle = eventTitle;
	}

	/**
	 * @param customerId
	 * @param eventId
	 * @param dateTime
	 * @param sendMailReminder
	 * @param sendPushReminder
	 * @param eventTitle
	 * @param eventImage
	 */
	public ReminderDTO(Integer customerId, Integer eventId, String dateTime, boolean sendMailReminder, boolean sendPushReminder, String eventTitle, String eventImage) {
		super();
		this.customerId = customerId;
		this.eventId = eventId;
		this.dateTime = dateTime;
		this.sendMailReminder = sendMailReminder;
		this.sendPushReminder = sendPushReminder;
		this.eventTitle = eventTitle;
		this.eventImage = eventImage;
	}

	/**
	 * @param customerId
	 * @param eventId
	 * @param dateTime
	 * @param sendMailReminder
	 * @param sendPushReminder
	 * @param eventTitle
	 * @param eventImage
	 * @param eventStartDate
	 */
	public ReminderDTO(Integer customerId, Integer eventId, String dateTime, boolean sendMailReminder, boolean sendPushReminder, String eventTitle, String eventImage,
			String eventStartDate) {
		super();
		this.customerId = customerId;
		this.eventId = eventId;
		this.dateTime = dateTime;
		this.sendMailReminder = sendMailReminder;
		this.sendPushReminder = sendPushReminder;
		this.eventTitle = eventTitle;
		this.eventImage = eventImage;
		this.eventStartDate = eventStartDate;
	}

	/**
	 * @param customerId
	 * @param eventId
	 * @param dateTime
	 * @param sendMailReminder
	 * @param sendPushReminder
	 * @param eventTitle
	 * @param eventImage
	 * @param eventStartDate
	 * @param categoryName
	 */
	public ReminderDTO(Integer customerId, Integer eventId, String dateTime, boolean sendMailReminder, boolean sendPushReminder, String eventTitle, String eventImage,
			String eventStartDate, String categoryName) {
		super();
		this.customerId = customerId;
		this.eventId = eventId;
		this.dateTime = dateTime;
		this.sendMailReminder = sendMailReminder;
		this.sendPushReminder = sendPushReminder;
		this.eventTitle = eventTitle;
		this.eventImage = eventImage;
		this.eventStartDate = eventStartDate;
		this.categoryName = categoryName;
	}

	/**
	 * @param customerId
	 * @param eventId
	 * @param dateTime
	 * @param sendMailReminder
	 * @param sendPushReminder
	 * @param eventTitle
	 * @param eventImage
	 * @param eventStartDate
	 * @param categoryName
	 * @param eventImageUrl
	 */
	public ReminderDTO(Integer customerId, Integer eventId, String dateTime, boolean sendMailReminder, boolean sendPushReminder, String eventTitle, String eventImage,
			String eventStartDate, String categoryName, String eventImageUrl) {
		super();
		this.customerId = customerId;
		this.eventId = eventId;
		this.dateTime = dateTime;
		this.sendMailReminder = sendMailReminder;
		this.sendPushReminder = sendPushReminder;
		this.eventTitle = eventTitle;
		this.eventImage = eventImage;
		this.eventStartDate = eventStartDate;
		this.categoryName = categoryName;
		this.eventImageUrl = eventImageUrl;
	}

	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
	 * @return the sendMailReminder
	 */
	public boolean isSendMailReminder() {
		return sendMailReminder;
	}

	/**
	 * @param sendMailReminder
	 *            the sendMailReminder to set
	 */
	public void setSendMailReminder(boolean sendMailReminder) {
		this.sendMailReminder = sendMailReminder;
	}

	/**
	 * @return the sendPushReminder
	 */
	public boolean isSendPushReminder() {
		return sendPushReminder;
	}

	/**
	 * @param sendPushReminder
	 *            the sendPushReminder to set
	 */
	public void setSendPushReminder(boolean sendPushReminder) {
		this.sendPushReminder = sendPushReminder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/**
	 * @return the eventTitle
	 */
	public String getEventTitle() {
		return eventTitle;
	}

	/**
	 * @param eventTitle
	 *            the eventTitle to set
	 */
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
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
	 * @return the eventStartDate
	 */
	public String getEventStartDate() {
		return eventStartDate;
	}

	/**
	 * @param eventStartDate
	 *            the eventStartDate to set
	 */
	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
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
		return "ReminderDTO [customerId=" + customerId + ", eventId=" + eventId + ", dateTime=" + dateTime + ", sendMailReminder=" + sendMailReminder + ", sendPushReminder="
				+ sendPushReminder + ", eventTitle=" + eventTitle + ", eventImage=" + eventImage + ", eventStartDate=" + eventStartDate + ", categoryName=" + categoryName
				+ ", eventImageUrl=" + eventImageUrl + "]";
	}

}
