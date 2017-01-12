/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.request.vendor;

import com.oneg.whsquared.entity.Vendor;
import com.oneg.whsquared.entity.VendorAddress;
import com.oneg.whsquared.entity.VendorDetail;

public class VendorSaveRequest {

	private Vendor vendor;

	private VendorDetail detail;
	// private List<VendorCategory> vendorCategoryList;

	private VendorAddress address;

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public VendorDetail getDetail() {
		return detail;
	}

	public void setDetail(VendorDetail detail) {
		this.detail = detail;
	}

	public VendorAddress getAddress() {
		return address;
	}

	public void setAddress(VendorAddress address) {
		this.address = address;
	}

	/*
	 * public List<VendorCategory> getVendorCategoryList() { return
	 * vendorCategoryList; }
	 * 
	 * public void setVendorCategoryList(List<VendorCategory>
	 * vendorCategoryList) { this.vendorCategoryList = vendorCategoryList; }
	 */

}
