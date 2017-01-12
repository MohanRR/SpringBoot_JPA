/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 
 * @author Anbukkani G
 */
@Entity
@Table(name = "event")
@XmlRootElement
public class Event extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "name")
	private String name;

	@Lob
	@Column(name = "description")
	private String description;

	@Basic(optional = false)
	@Column(name = "short_description")
	private String shortDescription;

	@Column(name = "is_paid")
	private Boolean isPaid;

	@Basic(optional = false)
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Basic(optional = false)
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Basic(optional = false)
	@Column(name = "start_time")
	@Temporal(TemporalType.TIME)
	private Date startTime;

	@Basic(optional = false)
	@Column(name = "end_time")
	@Temporal(TemporalType.TIME)
	private Date endTime;

	@Basic(optional = false)
	@Column(name = "active")
	private boolean active;

	@OneToMany(mappedBy = "eventId")
	@JsonIgnore
	private List<CalendarEvent> calendarEventList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "eventId")
	private List<EventView> eventViewList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	@JsonIgnore
	// @JsonManagedReference(value="event_eventAddressList")
	private List<EventAddress> eventAddressList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	@JsonManagedReference(value = "event_categoryList")
	private List<EventCategory> eventCategoryList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "eventId")
	// @JsonManagedReference(value="event_rateCardList")
	@JsonIgnore
	private List<RateCard> rateCardList;

	// @JsonIgnore
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventId")
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "eventId")
	// @JsonIgnore
	// @JoinColumn(name = "event_id", referencedColumnName = "id")
	private EventDetail eventDetail;

	@JoinColumn(name = "vendor_id", referencedColumnName = "id")
	@ManyToOne
	private Vendor vendorId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "eventId")
	@JsonIgnore
	private List<CustomerNotification> customerNotificationList;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "eventId")
	private EventCalender eventCalender;

	// Added by Aariv
	@Basic(optional = false)
	@Column(name = "showToday")
	private boolean showToday = false;

	/*
	 * @JsonManagedReference(value = "event-vendorList")
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "event") private
	 * List<UserVendors> userVendorList;
	 */

	public Event() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		if (startTime == null) {

		}
		String[] time = startTime.split(":");
		this.startTime = new Date(2016, 0, 1, Integer.parseInt(time[0]), Integer.parseInt(time[1]));
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		if (endTime == null) {

		}
		String[] time = endTime.split(":");
		String type = "";
		if (time[1] != null) {
			String[] types = time[1].split(" ");
			time[1] = types[0];
			type = types[0];
			if (type != null && type.equalsIgnoreCase("PM")) {
				time[1] = (Integer.parseInt(time[1]) + 12) + "";
			}
		}
		this.endTime = new Date(2016, 0, 1, Integer.parseInt(time[0]), Integer.parseInt(time[1]));
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@XmlTransient
	public List<CalendarEvent> getCalendarEventList() {
		return calendarEventList;
	}

	public void setCalendarEventList(List<CalendarEvent> calendarEventList) {
		this.calendarEventList = calendarEventList;
	}

	@XmlTransient
	public List<EventView> getEventViewList() {
		return eventViewList;
	}

	public void setEventViewList(List<EventView> eventViewList) {
		this.eventViewList = eventViewList;
	}

	@XmlTransient
	public List<EventAddress> getEventAddressList() {
		return eventAddressList;
	}

	public void setEventAddressList(List<EventAddress> eventAddressList) {
		this.eventAddressList = eventAddressList;
	}

	@XmlTransient
	public List<EventCategory> getEventCategoryList() {
		return eventCategoryList;
	}

	public void setEventCategoryList(List<EventCategory> eventCategoryList) {
		this.eventCategoryList = eventCategoryList;
	}

	@XmlTransient
	public List<RateCard> getRateCardList() {
		return rateCardList;
	}

	public void setRateCardList(List<RateCard> rateCardList) {
		this.rateCardList = rateCardList;
	}

	@XmlTransient
	public EventDetail getEventDetail() {
		return eventDetail;
	}

	public void setEventDetail(EventDetail eventDetail) {
		this.eventDetail = eventDetail;
	}

	public Vendor getVendorId() {
		return vendorId;
	}

	public void setVendorId(Vendor vendorId) {
		this.vendorId = vendorId;
	}

	@XmlTransient
	public List<CustomerNotification> getCustomerNotificationList() {
		return customerNotificationList;
	}

	public void setCustomerNotificationList(List<CustomerNotification> customerNotificationList) {
		this.customerNotificationList = customerNotificationList;
	}

	/**
	 * @return the startDate
	 */

	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */

	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the eventCalender
	 */
	public EventCalender getEventCalender() {
		return eventCalender;
	}

	/**
	 * @param eventCalender
	 *            the eventCalender to set
	 */
	public void setEventCalender(EventCalender eventCalender) {
		this.eventCalender = eventCalender;
	}

	/**
	 * @return the showToday
	 */
	public boolean isShowToday() {
		return showToday;
	}

	/**
	 * @param showToday
	 *            the showToday to set
	 */
	public void setShowToday(boolean showToday) {
		this.showToday = showToday;
	}
}
