/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 
 * @author Anbukkani G
 */
@Entity
@Table(name = "address")
@XmlRootElement
public class Address extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "address1")
	private String address1;
	@Column(name = "address2")
	private String address2;
	@Basic(optional = false)
	@Column(name = "city")
	private String city;
	@Basic(optional = false)
	@Column(name = "zip")
	private String zip;
	@Column(name = "lat")
	private String lat;
	@Column(name = "long1")
	private String long1;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@JoinColumn(name = "state_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private State state;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
	@JsonManagedReference(value = "address-userAddressList")
	private List<UserAddress> userAddressList;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
	// @JsonManagedReference(value="address-eventAddressList")
	// @JsonIgnore
	// private List<EventAddress> eventAddressList;
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
	// @JsonManagedReference(value = "address-vendorAddressList")
	// private List<VendorAddress> vendorAddressList;
	// @OneToMany(mappedBy = "address")
	// // @JsonManagedReference(value="address-customerList")
	// @JsonIgnore
	// private List<Customer> customerList;

	public Address() {
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLong1() {
		return long1;
	}

	public void setLong1(String long1) {
		this.long1 = long1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@XmlTransient
	public List<UserAddress> getUserAddressList() {
		return userAddressList;
	}

	public void setUserAddressList(List<UserAddress> userAddressList) {
		this.userAddressList = userAddressList;
	}

	/*
	 * @XmlTransient public List<EventAddress> getEventAddressList() { return
	 * eventAddressList; }
	 * 
	 * public void setEventAddressList(List<EventAddress> eventAddressList) {
	 * this.eventAddressList = eventAddressList; }
	 * 
	 * @XmlTransient public List<VendorAddress> getVendorAddressList() { return
	 * vendorAddressList; }
	 * 
	 * public void setVendorAddressList(List<VendorAddress> vendorAddressList) {
	 * this.vendorAddressList = vendorAddressList; }
	 * 
	 * @XmlTransient public List<Customer> getCustomerList() { return
	 * customerList; }
	 * 
	 * public void setCustomerList(List<Customer> customerList) {
	 * this.customerList = customerList; }
	 */

	public String getFullAddress() {
		return address1 + ", " + address2 + ", "+ city + ", " + state.getName() + " " + zip;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Address [address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", zip=" + zip + ", lat=" + lat + ", long1=" + long1 + ", name=" + name + ", state="
				+ state + ", userAddressList=" + userAddressList + "]";
	}

}
