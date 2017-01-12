/**
 * 
 */
package com.oneg.whsquared.entity;

/**
 * @author Thomas
 * 
 */
public class WHAppVersion {

	/**
	 * 
	 */
	private Integer id;
	private String androidVersion;
	private String iOSVersion;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the androidVersion
	 */
	public String getAndroidVersion() {
		return androidVersion;
	}

	/**
	 * @param androidVersion
	 *            the androidVersion to set
	 */
	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}

	/**
	 * @return the iOSVersion
	 */
	public String getiOSVersion() {
		return iOSVersion;
	}

	/**
	 * @param iOSVersion
	 *            the iOSVersion to set
	 */
	public void setiOSVersion(String iOSVersion) {
		this.iOSVersion = iOSVersion;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VelocitiAppVersion [id=" + id + ", androidVersion=" + androidVersion + ", iOSVersion=" + iOSVersion + "]";
	}

}
