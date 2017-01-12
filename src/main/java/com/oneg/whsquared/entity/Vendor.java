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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 
 * @author Anbukkani G
 */
@Entity
@Table(name = "vendor")
@XmlRootElement
public class Vendor extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Basic(optional = false)
	@Column(name = "is_paid_vendor")
	private boolean isPaidVendor;
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendor")
	// @JsonManagedReference(value = "vendor-vendorAddressList")
	// private List<VendorAddress> vendorAddressList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vendorId")
	@JsonIgnore
	private List<VendorView> vendorViewList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vendor")
	@JsonManagedReference(value = "vendorCategoryList")
	private List<VendorCategory> vendorCategoryList;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "vendor")
	private VendorDetail vendorDetail;

	// @JoinColumn(name = "user_id", referencedColumnName = "id")
	// @ManyToOne(optional = false)
	// @JsonIgnore
	// private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vendor")
	@JsonManagedReference(value = "vendor-userVendorList")
	private List<UserVendors> userVendorList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vendorId")
	@JsonIgnore
	private List<CustomerFavoriteVendor> customerFavoriteVendorList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vendorId")
	@JsonIgnore
	private List<Event> eventList;

	public Vendor() {
	}

	public Vendor(int id) {
		this.setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsPaidVendor() {
		return isPaidVendor;
	}

	public void setIsPaidVendor(boolean isPaidVendor) {
		this.isPaidVendor = isPaidVendor;
	}

	// @XmlTransient
	// public List<VendorAddress> getVendorAddressList() {
	// return vendorAddressList;
	// }
	//
	// public void setVendorAddressList(List<VendorAddress> vendorAddressList) {
	// this.vendorAddressList = vendorAddressList;
	// }
	@XmlTransient
	public List<VendorView> getVendorViewList() {
		return vendorViewList;
	}

	public void setVendorViewList(List<VendorView> vendorViewList) {
		this.vendorViewList = vendorViewList;
	}

	public VendorDetail getVendorDetail() {
		return vendorDetail;
	}

	public void setVendorDetail(VendorDetail vendorDetail) {
		this.vendorDetail = vendorDetail;
	}

	/*
	 * public User getUser() { return user; }
	 * 
	 * public void setUser(User user) { this.user = user; }
	 */

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<VendorCategory> getVendorCategoryList() {
		return vendorCategoryList;
	}

	public void setVendorCategoryList(List<VendorCategory> vendorCategoryList) {
		this.vendorCategoryList = vendorCategoryList;
	}

	@XmlTransient
	public List<CustomerFavoriteVendor> getCustomerFavoriteVendorList() {
		return customerFavoriteVendorList;
	}

	public void setCustomerFavoriteVendorList(List<CustomerFavoriteVendor> customerFavoriteVendorList) {
		this.customerFavoriteVendorList = customerFavoriteVendorList;
	}

	@XmlTransient
	public List<Event> getEventList() {
		return eventList;
	}

	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}

	/**
	 * @return the userVendorList
	 */
	public List<UserVendors> getUserVendorList() {
		return userVendorList;
	}

	/**
	 * @param userVendorList
	 *            the userVendorList to set
	 */
	public void setUserVendorList(List<UserVendors> userVendorList) {
		this.userVendorList = userVendorList;
	}

	/**
	 * @param isPaidVendor
	 *            the isPaidVendor to set
	 */
	public void setPaidVendor(boolean isPaidVendor) {
		this.isPaidVendor = isPaidVendor;
	}

}
