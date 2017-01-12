/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Anbukkani G
 */
@Entity
@Table(name = "vendor_detail")
@XmlRootElement
public class VendorDetail extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Lob
	@Column(name = "description")
	private String description;

	@Lob
	@Column(name = "logo")
	private byte[] logo;

	@Column(name = "logo_url")
	private String logoUrl;

	@Column(name = "facebook_url")
	private String facebookUrl;

	@Column(name = "twitter_url")
	private String twitterUrl;

	@Column(name = "instagram_url")
	private String instagramUrl;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "quicklink_1")
	private String quicklink1;

	@Column(name = "quicklink_2")
	private String quicklink2;

	@Column(name = "quicklink_3")
	private String quicklink3;
	
	@Column(name = "quickLinkText1")
	private String quickLinkText1;

	@Column(name = "quickLinkText2")
	private String quickLinkText2;

	@Column(name = "quickLinkText3")
	private String quickLinkText3;


	@Column(name = "vendor_url")
	private String vendorUrl;

	@Column(name = "business_hours")
	private String businessHours;

	@JoinColumn(name = "vendor_id", referencedColumnName = "id")
	@OneToOne(optional = false)
	@JsonIgnore
	private Vendor vendor;

	// Added By Aariv
	@Column(name = "background_url")
	private String backgroundUrl;

	public VendorDetail() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getFacebookUrl() {
		return facebookUrl;
	}

	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	public String getTwitterUrl() {
		return twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	public String getInstagramUrl() {
		return instagramUrl;
	}

	public void setInstagramUrl(String instagramUrl) {
		this.instagramUrl = instagramUrl;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuicklink1() {
		return quicklink1;
	}

	public void setQuicklink1(String quicklink1) {
		this.quicklink1 = quicklink1;
	}

	public String getQuicklink2() {
		return quicklink2;
	}

	public void setQuicklink2(String quicklink2) {
		this.quicklink2 = quicklink2;
	}

	public String getQuicklink3() {
		return quicklink3;
	}

	public void setQuicklink3(String quicklink3) {
		this.quicklink3 = quicklink3;
	}

	public String getVendorUrl() {
		return vendorUrl;
	}

	public void setVendorUrl(String vendorUrl) {
		this.vendorUrl = vendorUrl;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}
	
	/**
	 * @return the quickLinkText1
	 */
	public String getQuickLinkText1() {
		return quickLinkText1;
	}

	/**
	 * @param quickLinkText1 the quickLinkText1 to set
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
	 * @param quickLinkText2 the quickLinkText2 to set
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
	 * @param quickLinkText3 the quickLinkText3 to set
	 */
	public void setQuickLinkText3(String quickLinkText3) {
		this.quickLinkText3 = quickLinkText3;
	}

	/**
	 * @return the backgroungUrl
	 */
	public String getBackgroundUrl() {
		return backgroundUrl;
	}

	/**
	 * @param backgroundUrl
	 *            the backgroungUrl to set
	 */
	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VendorDetail [description=" + description + ", logo=" + Arrays.toString(logo) + ", logoUrl=" + logoUrl + ", facebookUrl=" + facebookUrl + ", twitterUrl="
				+ twitterUrl + ", instagramUrl=" + instagramUrl + ", phoneNumber=" + phoneNumber + ", email=" + email + ", quicklink1=" + quicklink1 + ", quicklink2=" + quicklink2
				+ ", quicklink3=" + quicklink3 + ", vendorUrl=" + vendorUrl + ", businessHours=" + businessHours + ", vendor=" + vendor + ", backgroungUrl=" + backgroundUrl + "]";
	}

}
