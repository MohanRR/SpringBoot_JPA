/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Anbukkani G
 */
@Embeddable
public class CustomerNotificationPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7117896602201771223L;
	@Basic(optional = false)
	@Column(name = "id")
	private int id;
	@Basic(optional = false)
	@Column(name = "customer_id")
	private int customerId;
	@Basic(optional = false)
	@Column(name = "type")
	private String type;
	@Basic(optional = false)
	@Column(name = "is_send")
	private boolean isSend;

	public CustomerNotificationPK() {
	}

	public CustomerNotificationPK(int id, int customerId, String type, boolean isSend) {
		this.id = id;
		this.customerId = customerId;
		this.type = type;
		this.isSend = isSend;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getIsSend() {
		return isSend;
	}

	public void setIsSend(boolean isSend) {
		this.isSend = isSend;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) id;
		hash += (int) customerId;
		hash += (type != null ? type.hashCode() : 0);
		hash += (isSend ? 1 : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CustomerNotificationPK)) {
			return false;
		}
		CustomerNotificationPK other = (CustomerNotificationPK) object;
		if (this.id != other.id) {
			return false;
		}
		if (this.customerId != other.customerId) {
			return false;
		}
		if ((this.type == null && other.type != null) || (this.type != null && !this.type.equals(other.type))) {
			return false;
		}
		if (this.isSend != other.isSend) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.oneg.whsquared.entity.CustomerNotificationPK[ id=" + id + ", customerId=" + customerId + ", type=" + type + ", isSend=" + isSend + " ]";
	}

}
