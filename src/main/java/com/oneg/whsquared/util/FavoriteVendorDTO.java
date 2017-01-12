/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class FavoriteVendorDTO {

	private Integer vendorId;

	private String vendorName;

	private String vendorLogoUrl;

	private String vendorImageUrl;

	public FavoriteVendorDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param vendorId
	 * @param vendorName
	 */
	public FavoriteVendorDTO(Integer vendorId, String vendorName) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
	}

	/**
	 * @param vendorId
	 * @param vendorName
	 * @param vendorImageUrl
	 */
	public FavoriteVendorDTO(Integer vendorId, String vendorName, String vendorImageUrl) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorImageUrl = vendorImageUrl;
	}

	/**
	 * @param vendorId
	 * @param vendorName
	 * @param vendorLogoUrl
	 * @param vendorImageUrl
	 */
	public FavoriteVendorDTO(Integer vendorId, String vendorName, String vendorLogoUrl, String vendorImageUrl) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorLogoUrl = vendorLogoUrl;
		this.vendorImageUrl = vendorImageUrl;
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
	 * @return the vendorImageUrl
	 */
	public String getVendorImageUrl() {
		return vendorImageUrl;
	}

	/**
	 * @param vendorImageUrl
	 *            the vendorImageUrl to set
	 */
	public void setVendorImageUrl(String vendorImageUrl) {
		this.vendorImageUrl = vendorImageUrl;
	}

	/**
	 * @return the vendorLogoUrl
	 */
	public String getVendorLogoUrl() {
		return vendorLogoUrl;
	}

	/**
	 * @param vendorLogoUrl
	 *            the vendorLogoUrl to set
	 */
	public void setVendorLogoUrl(String vendorLogoUrl) {
		this.vendorLogoUrl = vendorLogoUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FavoriteVendorDTO [vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorLogoUrl=" + vendorLogoUrl + ", vendorImageUrl=" + vendorImageUrl + "]";
	}

}
