/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.List;

/**
 * @author arivu
 * 
 */
public class PreferenceCategoryDTO {

	private Integer customerId;

	private List<Integer> Categories;

	public PreferenceCategoryDTO() {
		// TODO Auto-generated constructor stub
	}

	public PreferenceCategoryDTO(Integer customerId, List<Integer> Categories) {
		super();
		this.customerId = customerId;
		this.Categories = Categories;
	}

	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the Categories
	 */
	public List<Integer> getCategories() {
		return Categories;
	}

	/**
	 * @param Categories
	 *            the Categories to set
	 */
	public void setCategories(List<Integer> Categories) {
		this.Categories = Categories;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PreferenceCategoryDTO [customerId=" + customerId + ", Categories=" + Categories + "]";
	}

}
