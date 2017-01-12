package com.oneg.whsquared.util;

/**
 * 
 * @author arivu
 * 
 */
public class ProfileCategoryDTO {

	private Integer CategoryId;

	private String name;

	private String image;

	private Boolean status;

	private String imageUrl;

	public ProfileCategoryDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param CategoryId
	 * @param status
	 */
	public ProfileCategoryDTO(Integer CategoryId, Boolean status) {
		super();
		this.CategoryId = CategoryId;
		this.status = status;
	}

	/**
	 * @param CategoryId
	 * @param name
	 * @param status
	 */
	public ProfileCategoryDTO(Integer CategoryId, String name, Boolean status) {
		super();
		this.CategoryId = CategoryId;
		this.name = name;
		this.status = status;
	}

	/**
	 * @param CategoryId
	 * @param name
	 * @param status
	 * @param imageUrl
	 */
	public ProfileCategoryDTO(Integer CategoryId, String name, Boolean status, String imageUrl) {
		super();
		this.CategoryId = CategoryId;
		this.name = name;
		this.status = status;
		this.imageUrl = imageUrl;
	}

	/**
	 * @param CategoryId
	 * @param name
	 * @param image
	 * @param status
	 */
	public ProfileCategoryDTO(Integer CategoryId, String name, String image, Boolean status) {
		super();
		this.CategoryId = CategoryId;
		this.name = name;
		this.image = image;
		this.status = status;
	}

	/**
	 * @return the CategoryId
	 */
	public Integer getCategoryId() {
		return CategoryId;
	}

	/**
	 * @param CategoryId
	 *            the CategoryId to set
	 */
	public void setCategoryId(Integer CategoryId) {
		this.CategoryId = CategoryId;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
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

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProfileCategoryDTO [CategoryId=" + CategoryId + ", name=" + name + ", image=" + image + ", status=" + status + ", imageUrl=" + imageUrl + "]";
	}

}
