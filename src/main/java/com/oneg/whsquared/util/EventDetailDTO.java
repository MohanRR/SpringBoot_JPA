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
public class EventDetailDTO {

	private Integer eventId;

	private String eventName;

	private String startDate;

	private String endDate;

	private String startTime;

	private String endTime;

	private String eventLink;

	private String eventBackground;

	private String phoneNumber;

	private String happyHourMenu;

	private String dinnerMenu;

	private String photoUrl;

	private boolean eventStatus;

	private String eventBackgroundUrl;

	private List<EventAgendaDTO> agendaDTOs = new ArrayList<EventAgendaDTO>();

	private List<EventRateCard> eventRateCards = new ArrayList<EventRateCard>();

	private ReminderDTO reminderDTOs;

	private List<EventAddressDTO> addressDTOs = new ArrayList<EventAddressDTO>();

	private List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();

	public EventDetailDTO() {

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
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the eventLink
	 */
	public String getEventLink() {
		return eventLink;
	}

	/**
	 * @param eventLink
	 *            the eventLink to set
	 */
	public void setEventLink(String eventLink) {
		this.eventLink = eventLink;
	}

	/**
	 * @return the eventBackground
	 */
	public String getEventBackground() {
		return eventBackground;
	}

	/**
	 * @param eventBackground
	 *            the eventBackground to set
	 */
	public void setEventBackground(String eventBackground) {
		this.eventBackground = eventBackground;
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
	 * @return the happyHourMenu
	 */
	public String getHappyHourMenu() {
		return happyHourMenu;
	}

	/**
	 * @param happyHourMenu
	 *            the happyHourMenu to set
	 */
	public void setHappyHourMenu(String happyHourMenu) {
		this.happyHourMenu = happyHourMenu;
	}

	/**
	 * @return the dinnerMenu
	 */
	public String getDinnerMenu() {
		return dinnerMenu;
	}

	/**
	 * @param dinnerMenu
	 *            the dinnerMenu to set
	 */
	public void setDinnerMenu(String dinnerMenu) {
		this.dinnerMenu = dinnerMenu;
	}

	/**
	 * @return the photoUrl
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}

	/**
	 * @param photoUrl
	 *            the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	/**
	 * @return the agendaDTOs
	 */
	public List<EventAgendaDTO> getAgendaDTOs() {
		return agendaDTOs;
	}

	/**
	 * @param agendaDTOs
	 *            the agendaDTOs to set
	 */
	public void setAgendaDTOs(List<EventAgendaDTO> agendaDTOs) {
		this.agendaDTOs = agendaDTOs;
	}

	/**
	 * @return the eventRateCards
	 */
	public List<EventRateCard> getEventRateCards() {
		return eventRateCards;
	}

	/**
	 * @param eventRateCards
	 *            the eventRateCards to set
	 */
	public void setEventRateCards(List<EventRateCard> eventRateCards) {
		this.eventRateCards = eventRateCards;
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
	 * @return the reminderDTOs
	 */
	public ReminderDTO getReminderDTOs() {
		return reminderDTOs;
	}

	/**
	 * @param reminderDTOs
	 *            the reminderDTOs to set
	 */
	public void setReminderDTOs(ReminderDTO reminderDTOs) {
		this.reminderDTOs = reminderDTOs;
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
	 * @return the categoryDTOs
	 */
	public List<CategoryDTO> getCategoryDTOs() {
		return categoryDTOs;
	}

	/**
	 * @param categoryDTOs
	 *            the categoryDTOs to set
	 */
	public void setCategoryDTOs(List<CategoryDTO> categoryDTOs) {
		this.categoryDTOs = categoryDTOs;
	}

	/**
	 * @return the eventBackgroundUrl
	 */
	public String getEventBackgroundUrl() {
		return eventBackgroundUrl;
	}

	/**
	 * @param eventBackgroundUrl
	 *            the eventBackgroundUrl to set
	 */
	public void setEventBackgroundUrl(String eventBackgroundUrl) {
		this.eventBackgroundUrl = eventBackgroundUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventDetailDTO [eventId=" + eventId + ", eventName=" + eventName + ", startDate=" + startDate + ", endDate=" + endDate + ", startTime=" + startTime + ", endTime="
				+ endTime + ", eventLink=" + eventLink + ", eventBackground=" + eventBackground + ", phoneNumber=" + phoneNumber + ", happyHourMenu=" + happyHourMenu
				+ ", dinnerMenu=" + dinnerMenu + ", photoUrl=" + photoUrl + ", eventStatus=" + eventStatus + ", eventBackgroundUrl=" + eventBackgroundUrl + ", agendaDTOs="
				+ agendaDTOs + ", eventRateCards=" + eventRateCards + ", reminderDTOs=" + reminderDTOs + ", addressDTOs=" + addressDTOs + ", categoryDTOs=" + categoryDTOs + "]";
	}

}
