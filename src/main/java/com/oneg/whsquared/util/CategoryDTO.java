/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class CategoryDTO {

	private Integer id;

	private String categoryName;

	private String image;

	private String banner;

	private String bannerUrl;

	private String imageUrl;

	private boolean categoryStatus;

	public CategoryDTO() {

	}

	public CategoryDTO(Integer id, String categoryName, String image) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.image = image;
	}

	/**
	 * @param id
	 * @param categoryName
	 * @param image
	 * @param banner
	 */
	public CategoryDTO(Integer id, String categoryName, String image, String banner) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.image = image;
		this.banner = banner;
	}

	/**
	 * @param id
	 * @param categoryName
	 * @param image
	 * @param banner
	 * @param bannerUrl
	 * @param imageUrl
	 */
	public CategoryDTO(Integer id, String categoryName, String image, String banner, String bannerUrl, String imageUrl) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.image = image;
		this.banner = banner;
		this.bannerUrl = bannerUrl;
		this.imageUrl = imageUrl;
	}

	/**
	 * @param id
	 * @param categoryName
	 * @param image
	 * @param banner
	 * @param categoryStatus
	 */
	public CategoryDTO(Integer id, String categoryName, String image, String banner, boolean categoryStatus) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.image = image;
		this.banner = banner;
		this.categoryStatus = categoryStatus;
	}

	/**
	 * @param id
	 * @param categoryName
	 * @param image
	 * @param banner
	 * @param bannerUrl
	 * @param imageUrl
	 * @param categoryStatus
	 */
	public CategoryDTO(Integer id, String categoryName, String image, String banner, String bannerUrl, String imageUrl, boolean categoryStatus) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.image = image;
		this.banner = banner;
		this.bannerUrl = bannerUrl;
		this.imageUrl = imageUrl;
		this.categoryStatus = categoryStatus;
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
	 * @return the banner
	 */
	public String getBanner() {
		return banner;
	}

	/**
	 * @param banner
	 *            the banner to set
	 */
	public void setBanner(String banner) {
		this.banner = banner;
	}

	/**
	 * @return the categoryStatus
	 */
	public boolean isCategoryStatus() {
		return categoryStatus;
	}

	/**
	 * @param categoryStatus
	 *            the categoryStatus to set
	 */
	public void setCategoryStatus(boolean categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	/**
	 * @return the bannerUrl
	 */
	public String getBannerUrl() {
		return bannerUrl;
	}

	/**
	 * @param bannerUrl
	 *            the bannerUrl to set
	 */
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
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
		return "CategoryDTO [id=" + id + ", categoryName=" + categoryName + ", image=" + image + ", banner=" + banner + ", bannerUrl=" + bannerUrl + ", imageUrl=" + imageUrl
				+ ", categoryStatus=" + categoryStatus + "]";
	}

}
