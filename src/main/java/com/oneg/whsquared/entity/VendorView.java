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
@Table(name = "vendor_view")
@XmlRootElement
public class VendorView extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "customer_device_id")
	private String customerDeviceId;
	@JoinColumn(name = "vendor_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Vendor vendorId;

	public VendorView() {
	}

	public String getCustomerDeviceId() {
		return customerDeviceId;
	}

	public void setCustomerDeviceId(String customerDeviceId) {
		this.customerDeviceId = customerDeviceId;
	}

	public Vendor getVendorId() {
		return vendorId;
	}

	public void setVendorId(Vendor vendorId) {
		this.vendorId = vendorId;
	}

}
