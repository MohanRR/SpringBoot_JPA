/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class VendorEventCategoryDTO {

	private Integer categoryId;

	private String categoryName;

	/**
	 * @param categoryId
	 * @param categoryName
	 */
	public VendorEventCategoryDTO(Integer categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
