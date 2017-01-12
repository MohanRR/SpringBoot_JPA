/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public enum RegistrationType {

	FACEBOOK("Facebook"), GOOGLE("Google"), MANUAL("Manual");

	private String name;

	private RegistrationType() {
		// TODO Auto-generated constructor stub
	}

	private RegistrationType(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
