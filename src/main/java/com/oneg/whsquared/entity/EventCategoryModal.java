package com.oneg.whsquared.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "event_category")
@XmlRootElement
public class EventCategoryModal extends BaseEntity implements Serializable {

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

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
	private List<CustomerFavoriteCategory> customerFavoriteCategoryList;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	// @JsonManagedReference(value = "category_subCategory")
	// private List<SubCategory> subCategorys;

	public EventCategoryModal() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getBackground() {
		return background;
	}

	public void setBackground(byte[] background) {
		this.background = background;
	}

	public String getBackgroundUrl() {
		return backgroundUrl;
	}

	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
	}

	public byte[] getBanner() {
		return banner;
	}

	public void setBanner(byte[] banner) {
		this.banner = banner;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public List<CustomerFavoriteCategory> getCustomerFavoriteCategoryList() {
		return customerFavoriteCategoryList;
	}

	public void setCustomerFavoriteCategoryList(List<CustomerFavoriteCategory> customerFavoriteCategoryList) {
		this.customerFavoriteCategoryList = customerFavoriteCategoryList;
	}

	/*
	 * public List<SubCategory> getSubCategorys() { return subCategorys; }
	 * 
	 * public void setSubCategorys(List<SubCategory> subCategorys) {
	 * this.subCategorys = subCategorys; }
	 */

}
