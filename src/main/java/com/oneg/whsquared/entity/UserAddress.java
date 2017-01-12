/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author Anbukkani G
 */
@Entity
@Table(name = "user_address")
@XmlRootElement
public class UserAddress extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@JsonBackReference(value = "address-userAddressList")
	private Address address;
        @JsonBackReference(value = "user-userAddressList")
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;

	public UserAddress() {
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
