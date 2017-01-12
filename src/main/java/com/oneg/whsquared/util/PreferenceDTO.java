/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class PreferenceDTO {

	private Integer id;

	private String CategoryName;

	private String image;

	private String imageUrl;

	public PreferenceDTO() {

	}

	/**
	 * @param id
	 * @param CategoryName
	 * @param imageUrl
	 */
	public PreferenceDTO(Integer id, String CategoryName, String imageUrl) {
		super();
		this.id = id;
		this.CategoryName = CategoryName;
		this.imageUrl = imageUrl;
	}

	/**
	 * @param id
	 * @param CategoryName
	 * @param image
	 * @param imageUrl
	 */
	public PreferenceDTO(Integer id, String CategoryName, String image, String imageUrl) {
		super();
		this.id = id;
		this.CategoryName = CategoryName;
		this.image = image;
		this.imageUrl = imageUrl;
	}

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
	 * @return the CategoryName
	 */
	public String getCategoryName() {
		return CategoryName;
	}

	/**
	 * @param CategoryName
	 *            the CategoryName to set
	 */
	public void setCategoryName(String CategoryName) {
		this.CategoryName = CategoryName;
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
		return "PreferenceDTO [id=" + id + ", CategoryName=" + CategoryName + ", image=" + image + ", imageUrl=" + imageUrl + "]";
	}

}
