package com.oneg.whsquared.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "uservendor_events")
@XmlRootElement
public class UserVendorEvents extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "vendor_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@JsonBackReference(value = "vendor-eventVendorList")
	private Vendor vendor;

	@JsonBackReference(value = "user-vendorEventList")
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;

	@JsonBackReference(value = "event-vendorList")
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Event event;

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
