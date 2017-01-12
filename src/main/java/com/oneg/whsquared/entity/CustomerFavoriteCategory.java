/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Anbukkani G
 */
@Entity
@Table(name = "customer_favorite_category")
@XmlRootElement
public class CustomerFavoriteCategory extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "is_notification_enabled")
	private boolean isNotificationEnabled;
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private EventCategoryModal categoryId;
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Customer customerId;

	public CustomerFavoriteCategory() {
	}

	public CustomerFavoriteCategory(boolean isNotificationEnabled, EventCategoryModal categoryId, Customer customerId) {
		super();
		this.isNotificationEnabled = isNotificationEnabled;
		this.categoryId = categoryId;
		this.customerId = customerId;
	}

	public CustomerFavoriteCategory(EventCategoryModal categoryId, Customer customerId) {
		super();
		this.categoryId = categoryId;
		this.customerId = customerId;
	}

	public boolean getIsNotificationEnabled() {
		return isNotificationEnabled;
	}

	public void setIsNotificationEnabled(boolean isNotificationEnabled) {
		this.isNotificationEnabled = isNotificationEnabled;
	}

	public EventCategoryModal getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(EventCategoryModal categoryId) {
		this.categoryId = categoryId;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

}
