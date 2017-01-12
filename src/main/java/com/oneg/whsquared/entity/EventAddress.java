/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;

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
@Table(name = "event_address")
@XmlRootElement
public class EventAddress extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	// @JsonBackReference(value="address-eventAddressList")
	private Address address;
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	// @JsonManagedReference(value="event_eventAddressList")
	// @JsonIgnore
	private Event event;

	public EventAddress() {
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
