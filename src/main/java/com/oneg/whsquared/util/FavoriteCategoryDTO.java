/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class FavoriteCategoryDTO {

	private Integer customerId;

	private Integer categoryId;

	public FavoriteCategoryDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param customerId
	 * @param categoryId
	 */
	public FavoriteCategoryDTO(Integer customerId, Integer categoryId) {
		super();
		this.customerId = customerId;
		this.categoryId = categoryId;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FavoriteCategoryDTO [customerId=" + customerId + ", categoryId=" + categoryId + "]";
	}

}
