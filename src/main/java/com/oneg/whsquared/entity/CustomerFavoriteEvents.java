/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author arivu
 * 
 */
@Entity
@Table(name = "customer_favorite_events")
@XmlRootElement
public class CustomerFavoriteEvents extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Event event;

	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Customer customerId;

	@Column(name = "mail_sent")
	private boolean mailSent = false;

	public CustomerFavoriteEvents() {
	}

	/**
	 * @param event
	 * @param customerId
	 */
	public CustomerFavoriteEvents(Event event, Customer customerId) {
		super();
		this.event = event;
		this.customerId = customerId;
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
	 * @return the customerId
	 */
	public Customer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the mailSent
	 */
	public boolean isMailSent() {
		return mailSent;
	}

	/**
	 * @param mailSent
	 *            the mailSent to set
	 */
	public void setMailSent(boolean mailSent) {
		this.mailSent = mailSent;
	}

}
