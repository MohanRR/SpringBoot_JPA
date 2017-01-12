/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author arivu
 * 
 */
public class VendorDetailDTO {

	private Integer vendorId;

	private String vendorName;

	private String vendorBackgroundUrl;

	private String vendorIconUrl;

	private String vendorPhoneNumber;

	private boolean vendorStatus;

	private String bussinessHours;

	private String quickLink1;

	private String quickLink2;

	private String quickLink3;

	private String quickLinkText1;

	private String quickLinkText2;

	private String quickLinkText3;

	private String eventDate;

	private List<VendorAddressDTO> addressDTOs = new ArrayList<VendorAddressDTO>();

	private List<VendorEventCategoryDTO> vendorcategoryDTOs = new ArrayList<VendorEventCategoryDTO>();

	private List<VendorEventDTO> vendorEventDTOs = new ArrayList<VendorEventDTO>();

	private String faceBookUrl;

	private String instagramUrl;

	private String twitterUrl;

	public VendorDetailDTO() {

	}

	/**
	 * @param vendorId
	 * @param vendorName
	 * @param vendorBackgroundUrl
	 * @param vendorIconUrl
	 * @param addressDTOs
	 */
	public VendorDetailDTO(Integer vendorId, String vendorName, String vendorBackgroundUrl, String vendorIconUrl, List<VendorAddressDTO> addressDTOs) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorBackgroundUrl = vendorBackgroundUrl;
		this.vendorIconUrl = vendorIconUrl;
		this.addressDTOs = addressDTOs;
	}

	/**
	 * @param vendorId
	 * @param vendorName
	 * @param vendorBackgroundUrl
	 * @param vendorIconUrl
	 * @param vendorStatus
	 * @param addressDTOs
	 */
	public VendorDetailDTO(Integer vendorId, String vendorName, String vendorBackgroundUrl, String vendorIconUrl, boolean vendorStatus, List<VendorAddressDTO> addressDTOs) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorBackgroundUrl = vendorBackgroundUrl;
		this.vendorIconUrl = vendorIconUrl;
		this.vendorStatus = vendorStatus;
		this.addressDTOs = addressDTOs;
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

	/**
	 * @return the vendorIconUrl
	 */
	public String getVendorIconUrl() {
		return vendorIconUrl;
	}

	/**
	 * @param vendorIconUrl
	 *            the vendorIconUrl to set
	 */
	public void setVendorIconUrl(String vendorIconUrl) {
		this.vendorIconUrl = vendorIconUrl;
	}

	/**
	 * @return the addressDTOs
	 */
	public List<VendorAddressDTO> getAddressDTOs() {
		return addressDTOs;
	}

	/**
	 * @param addressDTOs
	 *            the addressDTOs to set
	 */
	public void setAddressDTOs(List<VendorAddressDTO> addressDTOs) {
		this.addressDTOs = addressDTOs;
	}

	/**
	 * @return the vendorStatus
	 */
	public boolean isVendorStatus() {
		return vendorStatus;
	}

	/**
	 * @param vendorStatus
	 *            the vendorStatus to set
	 */
	public void setVendorStatus(boolean vendorStatus) {
		this.vendorStatus = vendorStatus;
	}

	/**
	 * @return the vendorcategoryDTOs
	 */
	public List<VendorEventCategoryDTO> getVendorcategoryDTOs() {
		return vendorcategoryDTOs;
	}

	/**
	 * @param vendorcategoryDTOs
	 *            the vendorcategoryDTOs to set
	 */
	public void setVendorcategoryDTOs(List<VendorEventCategoryDTO> vendorcategoryDTOs) {
		this.vendorcategoryDTOs = vendorcategoryDTOs;
	}

	/**
	 * @return the vendorEventDTOs
	 */
	public List<VendorEventDTO> getVendorEventDTOs() {
		return vendorEventDTOs;
	}

	/**
	 * @param vendorEventDTOs
	 *            the vendorEventDTOs to set
	 */
	public void setVendorEventDTOs(List<VendorEventDTO> vendorEventDTOs) {
		this.vendorEventDTOs = vendorEventDTOs;
	}

	/**
	 * @return the vendorPhoneNumber
	 */
	public String getVendorPhoneNumber() {
		return vendorPhoneNumber;
	}

	/**
	 * @param vendorPhoneNumber
	 *            the vendorPhoneNumber to set
	 */
	public void setVendorPhoneNumber(String vendorPhoneNumber) {
		this.vendorPhoneNumber = vendorPhoneNumber;
	}

	/**
	 * @return the quickLink1
	 */
	public String getQuickLink1() {
		return quickLink1;
	}

	/**
	 * @param quickLink1
	 *            the quickLink1 to set
	 */
	public void setQuickLink1(String quickLink1) {
		this.quickLink1 = quickLink1;
	}

	/**
	 * @return the quickLink2
	 */
	public String getQuickLink2() {
		return quickLink2;
	}

	/**
	 * @param quickLink2
	 *            the quickLink2 to set
	 */
	public void setQuickLink2(String quickLink2) {
		this.quickLink2 = quickLink2;
	}

	/**
	 * @return the quickLink3
	 */
	public String getQuickLink3() {
		return quickLink3;
	}

	/**
	 * @param quickLink3
	 *            the quickLink3 to set
	 */
	public void setQuickLink3(String quickLink3) {
		this.quickLink3 = quickLink3;
	}

	/**
	 * @return the bussinessHours
	 */
	public String getBussinessHours() {
		return bussinessHours;
	}

	/**
	 * @param bussinessHours
	 *            the bussinessHours to set
	 */
	public void setBussinessHours(String bussinessHours) {
		this.bussinessHours = bussinessHours;
	}

	/**
	 * @return the quickLinkText1
	 */
	public String getQuickLinkText1() {
		return quickLinkText1;
	}

	/**
	 * @param quickLinkText1
	 *            the quickLinkText1 to set
	 */
	public void setQuickLinkText1(String quickLinkText1) {
		this.quickLinkText1 = quickLinkText1;
	}

	/**
	 * @return the quickLinkText2
	 */
	public String getQuickLinkText2() {
		return quickLinkText2;
	}

	/**
	 * @param quickLinkText2
	 *            the quickLinkText2 to set
	 */
	public void setQuickLinkText2(String quickLinkText2) {
		this.quickLinkText2 = quickLinkText2;
	}

	/**
	 * @return the quickLinkText3
	 */
	public String getQuickLinkText3() {
		return quickLinkText3;
	}

	/**
	 * @param quickLinkText3
	 *            the quickLinkText3 to set
	 */
	public void setQuickLinkText3(String quickLinkText3) {
		this.quickLinkText3 = quickLinkText3;
	}

	/**
	 * @return the eventDate
	 */
	public String getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate
	 *            the eventDate to set
	 */
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return the faceBookUrl
	 */
	public String getFaceBookUrl() {
		return faceBookUrl;
	}

	/**
	 * @param faceBookUrl
	 *            the faceBookUrl to set
	 */
	public void setFaceBookUrl(String faceBookUrl) {
		this.faceBookUrl = faceBookUrl;
	}

	/**
	 * @return the instagramUrl
	 */
	public String getInstagramUrl() {
		return instagramUrl;
	}

	/**
	 * @param instagramUrl
	 *            the instagramUrl to set
	 */
	public void setInstagramUrl(String instagramUrl) {
		this.instagramUrl = instagramUrl;
	}

	/**
	 * @return the twitterUrl
	 */
	public String getTwitterUrl() {
		return twitterUrl;
	}

	/**
	 * @param twitterUrl
	 *            the twitterUrl to set
	 */
	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VendorDetailDTO [vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorBackgroundUrl=" + vendorBackgroundUrl + ", vendorIconUrl=" + vendorIconUrl
				+ ", vendorPhoneNumber=" + vendorPhoneNumber + ", vendorStatus=" + vendorStatus + ", bussinessHours=" + bussinessHours + ", quickLink1=" + quickLink1
				+ ", quickLink2=" + quickLink2 + ", quickLink3=" + quickLink3 + ", quickLinkText1=" + quickLinkText1 + ", quickLinkText2=" + quickLinkText2 + ", quickLinkText3="
				+ quickLinkText3 + ", eventDate=" + eventDate + ", addressDTOs=" + addressDTOs + ", vendorcategoryDTOs=" + vendorcategoryDTOs + ", vendorEventDTOs="
				+ vendorEventDTOs + ", faceBookUrl=" + faceBookUrl + ", instagramUrl=" + instagramUrl + ", twitterUrl=" + twitterUrl + "]";
	}

}
