/**
 * 
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Thomas
 * 
 */
@Entity
@Table(name = "wh_version_details")
public class WHVersionDetails extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ANDROID_APP_VERSION
	@Column(name = "version_key")
	private String versionKey;

	// Android Version
	@Column(name = "version_name")
	private String versionName;

	// Version Value Ex : 3.22
	@Column(name = "version_value")
	private String versionValue;

	public WHVersionDetails() {
	}

	/**
	 * @param versionKey
	 * @param versionName
	 * @param versionValue
	 */
	public WHVersionDetails(String versionKey, String versionName, String versionValue) {
		super();
		this.versionKey = versionKey;
		this.versionName = versionName;
		this.versionValue = versionValue;
	}

	/**
	 * @return the versionKey
	 */
	public String getVersionKey() {
		return versionKey;
	}

	/**
	 * @param versionKey
	 *            the versionKey to set
	 */
	public void setVersionKey(String versionKey) {
		this.versionKey = versionKey;
	}

	/**
	 * @return the versionName
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * @param versionName
	 *            the versionName to set
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * @return the versionValue
	 */
	public String getVersionValue() {
		return versionValue;
	}

	/**
	 * @param versionValue
	 *            the versionValue to set
	 */
	public void setVersionValue(String versionValue) {
		this.versionValue = versionValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WHVersionDetails [versionKey=" + versionKey + ", versionName=" + versionName + ", versionValue=" + versionValue + "]";
	}

}
