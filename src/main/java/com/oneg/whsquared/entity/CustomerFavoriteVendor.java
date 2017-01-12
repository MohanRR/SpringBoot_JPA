/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name = "customer_favorite_vendor")
@XmlRootElement
public class CustomerFavoriteVendor extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "is_notification_enabled")
	private boolean isNotificationEnabled;
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Customer customerId;
	@JoinColumn(name = "vendor_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Vendor vendorId;

	public CustomerFavoriteVendor() {
	}

	/**
	 * @param customerId
	 * @param vendorId
	 */
	public CustomerFavoriteVendor(Customer customerId, Vendor vendorId) {
		super();
		this.customerId = customerId;
		this.vendorId = vendorId;
	}

	public boolean getIsNotificationEnabled() {
		return isNotificationEnabled;
	}

	public void setIsNotificationEnabled(boolean isNotificationEnabled) {
		this.isNotificationEnabled = isNotificationEnabled;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public Vendor getVendorId() {
		return vendorId;
	}

	public void setVendorId(Vendor vendorId) {
		this.vendorId = vendorId;
	}

}
