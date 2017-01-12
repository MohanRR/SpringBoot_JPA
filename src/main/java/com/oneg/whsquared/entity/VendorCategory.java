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

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author Rajan
 */
@Entity
@Table(name = "vendor_category")
@XmlRootElement
public class VendorCategory extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "category", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Category category;
	@JoinColumn(name = "subcategory", referencedColumnName = "id", nullable = true)
	@ManyToOne(optional = true)
	private SubCategory subcategory;
	@JsonBackReference(value = "vendorCategoryList")
	@JoinColumn(name = "vendor", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Vendor vendor;

	public VendorCategory() {
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (getId() != null ? getId().hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof VendorCategory)) {
			return false;
		}
		VendorCategory other = (VendorCategory) object;
		if ((getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.oneg.whsquared.entity.VendorCategory[ id=" + getId() + " ]";
	}

}
