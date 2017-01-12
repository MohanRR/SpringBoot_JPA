/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Anbukkani G
 */
@Entity
@Table(name = "customer_notification")
@XmlRootElement
public class CustomerNotification implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected CustomerNotificationPK customerNotificationPK;
	@Column(name = "reason")
	private String reason;
	@JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Customer customer;
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Event eventId;

	public CustomerNotification() {
	}

	public CustomerNotification(CustomerNotificationPK customerNotificationPK) {
		this.customerNotificationPK = customerNotificationPK;
	}

	public CustomerNotification(CustomerNotificationPK customerNotificationPK, String reason) {
		this.customerNotificationPK = customerNotificationPK;
		this.reason = reason;
	}

	public CustomerNotification(int id, int customerId, String type, boolean isSend) {
		this.customerNotificationPK = new CustomerNotificationPK(id, customerId, type, isSend);
	}

	public CustomerNotificationPK getCustomerNotificationPK() {
		return customerNotificationPK;
	}

	public void setCustomerNotificationPK(CustomerNotificationPK customerNotificationPK) {
		this.customerNotificationPK = customerNotificationPK;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Event getEventId() {
		return eventId;
	}

	public void setEventId(Event eventId) {
		this.eventId = eventId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (customerNotificationPK != null ? customerNotificationPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CustomerNotification)) {
			return false;
		}
		CustomerNotification other = (CustomerNotification) object;
		if ((this.customerNotificationPK == null && other.customerNotificationPK != null)
				|| (this.customerNotificationPK != null && !this.customerNotificationPK.equals(other.customerNotificationPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.oneg.whsquared.entity.CustomerNotification[ customerNotificationPK=" + customerNotificationPK + " ]";
	}

}
