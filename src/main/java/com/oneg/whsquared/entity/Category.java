/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.oneg.whsquared.util.ImageUtil;

/**
 * 
 * @author Anbukkani G
 */
@Entity
@Table(name = "category")
@XmlRootElement
public class Category extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "name")
	private String name;
	@Lob
	@Column(name = "background")
	private byte[] background;

	@Column(name = "background_url")
	private String backgroundUrl;

	@Lob
	@Column(name = "banner")
	private byte[] banner;

	@Column(name = "banner_url")
	private String bannerUrl;

	/*
	 * @JsonIgnore
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId") private
	 * List<CustomerFavoriteCategory> customerFavoriteCategoryList;
	 */

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	@JsonManagedReference(value = "category_subCategory")
	private List<SubCategory> subCategorys;

	public Category() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBackground() {
		return ImageUtil.bytesToBase64(background);
	}

	public void setBackground(String imageString) {
		this.background = ImageUtil.Base64ToBytes(imageString);
	}

	/*
	 * @XmlTransient public List<CustomerFavoriteCategory>
	 * getCustomerFavoriteCategoryList() { return customerFavoriteCategoryList;
	 * }
	 * 
	 * public void
	 * setCustomerFavoriteCategoryList(List<CustomerFavoriteCategory>
	 * customerFavoriteCategoryList) { this.customerFavoriteCategoryList =
	 * customerFavoriteCategoryList; }
	 */

	/**
	 * @return the banner
	 */
	public byte[] getBanner() {
		return banner;
	}

	/**
	 * @param banner
	 *            the banner to set
	 */
	public void setBanner(byte[] banner) {
		this.banner = banner;
	}

	/**
	 * @param background
	 *            the background to set
	 */
	public void setBackground(byte[] background) {
		this.background = background;
	}

	/**
	 * @return the backgroundUrl
	 */
	public String getBackgroundUrl() {
		return backgroundUrl;
	}

	/**
	 * @param backgroundUrl
	 *            the backgroundUrl to set
	 */
	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
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
	 * @return the subCategorys
	 */
	public List<SubCategory> getSubCategorys() {
		return subCategorys;
	}

	/**
	 * @param subCategorys
	 *            the subCategorys to set
	 */
	public void setSubCategorys(List<SubCategory> subCategorys) {
		this.subCategorys = subCategorys;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [name=" + name + ", background=" + Arrays.toString(background) + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(background);
		result = prime * result + ((backgroundUrl == null) ? 0 : backgroundUrl.hashCode());
		result = prime * result + Arrays.hashCode(banner);
		result = prime * result + ((bannerUrl == null) ? 0 : bannerUrl.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subCategorys == null) ? 0 : subCategorys.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (!Arrays.equals(background, other.background))
			return false;
		if (backgroundUrl == null) {
			if (other.backgroundUrl != null)
				return false;
		} else if (!backgroundUrl.equals(other.backgroundUrl))
			return false;
		if (!Arrays.equals(banner, other.banner))
			return false;
		if (bannerUrl == null) {
			if (other.bannerUrl != null)
				return false;
		} else if (!bannerUrl.equals(other.bannerUrl))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subCategorys == null) {
			if (other.subCategorys != null)
				return false;
		} else if (!subCategorys.equals(other.subCategorys))
			return false;
		return true;
	}

}
