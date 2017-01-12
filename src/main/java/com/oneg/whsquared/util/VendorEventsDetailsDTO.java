/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.List;

/**
 * @author arivu
 * 
 */
public class VendorEventsDetailsDTO {

	private VendorEventDetails vendorEventDetails;

	private List<VendorEventDetails> vendorDetailsByVendor;

	private List<VendorEventDetails> vendorDetailsByCategory;

	public VendorEventsDetailsDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the vendorEventDetails
	 */
	public VendorEventDetails getVendorEventDetails() {
		return vendorEventDetails;
	}

	/**
	 * @param vendorEventDetails
	 *            the vendorEventDetails to set
	 */
	public void setVendorEventDetails(VendorEventDetails vendorEventDetails) {
		this.vendorEventDetails = vendorEventDetails;
	}

	/**
	 * @return the vendorDetailsByVendor
	 */
	public List<VendorEventDetails> getVendorDetailsByVendor() {
		return vendorDetailsByVendor;
	}

	/**
	 * @param vendorDetailsByVendor
	 *            the vendorDetailsByVendor to set
	 */
	public void setVendorDetailsByVendor(List<VendorEventDetails> vendorDetailsByVendor) {
		this.vendorDetailsByVendor = vendorDetailsByVendor;
	}

	/**
	 * @return the vendorDetailsByCategory
	 */
	public List<VendorEventDetails> getVendorDetailsByCategory() {
		return vendorDetailsByCategory;
	}

	/**
	 * @param vendorDetailsByCategory
	 *            the vendorDetailsByCategory to set
	 */
	public void setVendorDetailsByCategory(List<VendorEventDetails> vendorDetailsByCategory) {
		this.vendorDetailsByCategory = vendorDetailsByCategory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VendorEventsDetailsDTO [vendorEventDetails=" + vendorEventDetails + ", vendorDetailsByVendor=" + vendorDetailsByVendor + ", vendorDetailsByCategory="
				+ vendorDetailsByCategory + "]";
	}

}
