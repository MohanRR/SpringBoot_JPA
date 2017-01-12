/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class FavouriteEvent {

	private Integer customerId;

	private Integer eventId;

	/**
	 * @param customerId
	 */
	public FavouriteEvent(Integer customerId) {
		super();
		this.customerId = customerId;
	}

	public FavouriteEvent() {
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
	 * @return the eventId
	 */
	public Integer getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

}
