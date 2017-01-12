/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class VendorDTO {

	private Integer vendorId;

	private String vendorName;

	private String vendorBackgroundUrl;

	public VendorDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param vendorId
	 * @param vendorName
	 * @param vendorBackgroundUrl
	 */
	public VendorDTO(Integer vendorId, String vendorName, String vendorBackgroundUrl) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorBackgroundUrl = vendorBackgroundUrl;
	}

	/**
	 * @return the vendorId
	 */
	public Integer getVendorId() {
		return vendorId;
	}

	/**
	 * @param vendorId
	 *            the vendorId to set
	 */
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * @param vendorName
	 *            the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * @return the vendorBackgroundUrl
	 */
	public String getVendorBackgroundUrl() {
		return vendorBackgroundUrl;
	}

	/**
	 * @param vendorBackgroundUrl
	 *            the vendorBackgroundUrl to set
	 */
	public void setVendorBackgroundUrl(String vendorBackgroundUrl) {
		this.vendorBackgroundUrl = vendorBackgroundUrl;
	}

}
