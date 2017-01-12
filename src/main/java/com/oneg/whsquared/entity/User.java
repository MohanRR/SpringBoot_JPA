/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 
 * @author Anbukkani G
 */
@Entity
@Table(name = "user")
@XmlRootElement
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "user_name")
	private String userName;
	@JsonIgnore
	@Column(name = "password")
	private String password;
	@Transient
	private String passwordTemp;
	@Basic(optional = false)
	@Column(name = "email")
	private String email;
	@Lob
	@Column(name = "avatar")
	private byte[] avatar;
	@Column(name = "avatar_url")
	private String avatarUrl;
	@JsonManagedReference(value = "user-userAddressList")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserAddress> userAddressList;

	// @JsonIgnore
	@JsonManagedReference(value = "user-vendorList")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserVendors> userVendorList;
	@JsonIgnore
	@OneToMany(mappedBy = "userId")
	private List<PasswordVerification> passwordVerificationList;
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	@ManyToOne
	private Role role;

	public User() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@XmlTransient
	public List<UserAddress> getUserAddressList() {
		return userAddressList;
	}

	public void setUserAddressList(List<UserAddress> userAddressList) {
		this.userAddressList = userAddressList;
	}

	@XmlTransient
	public List<PasswordVerification> getPasswordVerificationList() {
		return passwordVerificationList;
	}

	public void setPasswordVerificationList(List<PasswordVerification> passwordVerificationList) {
		this.passwordVerificationList = passwordVerificationList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPasswordTemp() {
		return passwordTemp;
	}

	public void setPasswordTemp(String passwordTemp) {
		this.passwordTemp = passwordTemp;
	}

	/**
	 * @return the userVendorList
	 */
	public List<UserVendors> getUserVendorList() {
		return userVendorList;
	}

	/**
	 * @param userVendorList
	 *            the userVendorList to set
	 */
	public void setUserVendorList(List<UserVendors> userVendorList) {
		this.userVendorList = userVendorList;
	}

}
