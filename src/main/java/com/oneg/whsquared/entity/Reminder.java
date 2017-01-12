/**
 * 
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author arivu
 * 
 */
@Entity
@Table(name = "reminder")
public class Reminder extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4958786412094053077L;

	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Customer customer;

	@Basic(optional = false)
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Event event;

	@Basic(optional = false)
	@Column(name = "reminder_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reminderDateTime;

	@Basic(optional = false)
	@Column(name = "send_mail_reminder")
	private boolean sendMailReminder;

	@Basic(optional = false)
	@Column(name = "send_push_reminder")
	private boolean sendPushReminder;

	@Column(name = "reminder_sent")
	private boolean reminderSent = false;

	public Reminder() {

	}

	/**
	 * @param customer
	 * @param event
	 * @param reminderDateTime
	 * @param sendMailReminder
	 * @param sendPushReminder
	 * @param reminderSent
	 */
	public Reminder(Customer customer, Event event, Date reminderDateTime, boolean sendMailReminder, boolean sendPushReminder, boolean reminderSent) {
		super();
		this.customer = customer;
		this.event = event;
		this.reminderDateTime = reminderDateTime;
		this.sendMailReminder = sendMailReminder;
		this.sendPushReminder = sendPushReminder;
		this.reminderSent = reminderSent;
	}

	/**
	 * @param customer
	 * @param event
	 * @param reminderDateTime
	 * @param sendMailReminder
	 * @param sendPushReminder
	 */
	public Reminder(Customer customer, Event event, Date reminderDateTime, boolean sendMailReminder, boolean sendPushReminder) {
		super();
		this.customer = customer;
		this.event = event;
		this.reminderDateTime = reminderDateTime;
		this.sendMailReminder = sendMailReminder;
		this.sendPushReminder = sendPushReminder;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * @return the reminderDateTime
	 */
	public Date getReminderDateTime() {
		return reminderDateTime;
	}

	/**
	 * @param reminderDateTime
	 *            the reminderDateTime to set
	 */
	public void setReminderDateTime(Date reminderDateTime) {
		this.reminderDateTime = reminderDateTime;
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

	/**
	 * @return the reminderSent
	 */
	public boolean isReminderSent() {
		return reminderSent;
	}

	/**
	 * @param reminderSent
	 *            the reminderSent to set
	 */
	public void setReminderSent(boolean reminderSent) {
		this.reminderSent = reminderSent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Reminder [customer=" + customer + ", event=" + event + ", reminderDateTime=" + reminderDateTime + ", sendMailReminder=" + sendMailReminder + ", sendPushReminder="
				+ sendPushReminder + ", reminderSent=" + reminderSent + "]";
	}

}
