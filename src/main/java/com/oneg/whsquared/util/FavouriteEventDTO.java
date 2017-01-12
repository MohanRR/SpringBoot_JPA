/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.List;

/**
 * @author arivu
 * 
 */
public class FavouriteEventDTO {

	private Integer customerId;

	private List<Integer> events;

	public FavouriteEventDTO() {
		// TODO Auto-generated constructor stub
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
	 * @return the events
	 */
	public List<Integer> getEvents() {
		return events;
	}

	/**
	 * @param events
	 *            the events to set
	 */
	public void setEvents(List<Integer> events) {
		this.events = events;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FavouriteEventDTO [customerId=" + customerId + ", events=" + events + "]";
	}

}
