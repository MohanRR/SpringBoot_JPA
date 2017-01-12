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
@Table(name = "vendor_address")
@XmlRootElement
public class VendorAddress extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	// @JsonBackReference(value = "address-vendorAddressList")
	private Address address;
	@JoinColumn(name = "vendor_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	// @JsonBackReference(value = "vendor-vendorAddressList")
	private Vendor vendor;

	public VendorAddress() {
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
        public String getFullAddress() {
                if(address.getAddress2()==null || address.getAddress2().isEmpty())
                    return address.getAddress1() + ", " + address.getCity() + ", " + address.getState().getName() + " " + address.getZip();
                else
                    return address.getAddress1() + ", " + address.getAddress2() + ", "+ address.getCity() + ", " + address.getState().getName() + " " + address.getZip();
	}
}
