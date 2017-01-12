package com.oneg.whsquared.response.vendor;

import com.oneg.whsquared.entity.Vendor;
import com.oneg.whsquared.entity.VendorAddress;
import com.oneg.whsquared.entity.VendorDetail;

public class VendorSaveResponse {

	private Vendor vendor;

	private VendorDetail detail;

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
}
