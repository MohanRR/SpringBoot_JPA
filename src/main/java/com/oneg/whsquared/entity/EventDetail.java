/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Anbukkani G
 */
@Entity
@Table(name = "event_detail")
@XmlRootElement
public class EventDetail extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
//	@Basic(optional = false)
//	@Column(name = "url_link")
//	private String urlLink;

//	@Column(name = "email_id")
//	private String emailId;

//	@Column(name = "phone_number")
//	private String phoneNumber;

	@Column(name = "background_url")
	private String backgroundUrl;

	@Basic(optional = true)
	@Lob
	@Column(name = "background")
	private byte[] background;

	@JsonIgnore
	@Basic(optional = false)
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Event eventId;

//	@Column(name = "happy_hour_menu")
//	private String happyHourMenu;
//
//	@Column(name = "dinner_menu")
//	private String dinnerMenu;
//
//	@Column(name = "photos_url", columnDefinition = "text")
//	private String photosUrl;

	public EventDetail() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getUrlLink() {
//		return urlLink;
//	}
//
//	public String getEmailId() {
//		return emailId;
//	}
//
//	public void setEmailId(String emailId) {
//		this.emailId = emailId;
//	}
//
//	public void setUrlLink(String urlLink) {
//		this.urlLink = urlLink;
//	}

	public byte[] getBackground() {
		return background;
	}

	public void setBackground(byte[] background) {
		this.background = background;
	}

	public String getBackgroundUrl() {
		return backgroundUrl;
	}

	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
	}

	public Event getEventId() {
		return eventId;
	}

	public void setEventId(Event eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the phoneNumber
	 */
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}

//	/**
//	 * @param phoneNumber
//	 *            the phoneNumber to set
//	 */
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	/**
//	 * @return the happyHourMenu
//	 */
//	public String getHappyHourMenu() {
//		return happyHourMenu;
//	}
//
//	/**
//	 * @param happyHourMenu
//	 *            the happyHourMenu to set
//	 */
//	public void setHappyHourMenu(String happyHourMenu) {
//		this.happyHourMenu = happyHourMenu;
//	}
//
//	/**
//	 * @return the dinnerMenu
//	 */
//	public String getDinnerMenu() {
//		return dinnerMenu;
//	}
//
//	/**
//	 * @param dinnerMenu
//	 *            the dinnerMenu to set
//	 */
//	public void setDinnerMenu(String dinnerMenu) {
//		this.dinnerMenu = dinnerMenu;
//	}
//
//	/**
//	 * @return the photosUrl
//	 */
//	public String getPhotosUrl() {
//		return photosUrl;
//	}
//
//	/**
//	 * @param photosUrl
//	 *            the photosUrl to set
//	 */
//	public void setPhotosUrl(String photosUrl) {
//		this.photosUrl = photosUrl;
//	}

}
