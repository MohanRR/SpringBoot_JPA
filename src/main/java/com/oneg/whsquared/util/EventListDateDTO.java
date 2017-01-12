/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author arivu
 * 
 */
public class EventListDateDTO {

	private String date;

	private List<EventListDTO> eventListDTOs = new ArrayList<>();

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the eventListDTOs
	 */
	public List<EventListDTO> getEventListDTOs() {
		return eventListDTOs;
	}

	/**
	 * @param eventListDTOs
	 *            the eventListDTOs to set
	 */
	public void setEventListDTOs(List<EventListDTO> eventListDTOs) {
		this.eventListDTOs = eventListDTOs;
	}

}
