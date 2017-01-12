/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;

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
@Table(name = "preference")
@XmlRootElement
public class Preference extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private EventCategoryModal category;
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Customer customer;

	public Preference() {
	}

	/**
	 * @param category
	 * @param customer
	 */
	public Preference(EventCategoryModal category, Customer customer) {
		super();
		this.category = category;
		this.customer = customer;
	}

	public EventCategoryModal getCategory() {
		return category;
	}

	public void setCategory(EventCategoryModal category) {
		this.category = category;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
