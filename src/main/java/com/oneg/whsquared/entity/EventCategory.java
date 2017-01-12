/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author Anbukkani G
 */
@Entity
@Table(name = "event_category_association")
@XmlRootElement
public class EventCategory extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	// @JsonBackReference(value="category_eventCategoryList")
	// @JsonIgnore
	private EventCategoryModal eventCategory;

	@JoinColumn(name = "event_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@JsonBackReference(value = "event_categoryList")
	// @JsonIgnore
	private Event event;

	/*
	 * @JoinColumn(name="subcategory", referencedColumnName="id")
	 * 
	 * @ManyToOne(optional = false) private SubCategory subCategory;
	 */

	public EventCategory() {
	}

	public EventCategoryModal getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategoryModal eventCategory) {
		this.eventCategory = eventCategory;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	/*
	 * public SubCategory getSubCategory() { return subCategory; }
	 * 
	 * public void setSubCategory(SubCategory subCategory) { this.subCategory =
	 * subCategory; }
	 */

}
