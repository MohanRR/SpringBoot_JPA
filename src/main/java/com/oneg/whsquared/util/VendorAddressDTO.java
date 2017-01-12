/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class VendorAddressDTO {

	private String address;

	private String latitude;

	private String longitude;

	public VendorAddressDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param address
	 * @param latitude
	 * @param longitude
	 */
	public VendorAddressDTO(String address, String latitude, String longitude) {
		super();
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventAddressDTO [address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
