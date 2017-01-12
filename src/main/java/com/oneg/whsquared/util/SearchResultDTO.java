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
public class SearchResultDTO {

	private List<EventListDTO> eventDetails = new ArrayList<>();

	private List<CategoryDTO> categoryDetails = new ArrayList<CategoryDTO>();

	private List<VendorDetailDTO> vendorDetailDTOs = new ArrayList<VendorDetailDTO>();

	public SearchResultDTO() {
	}

	/**
	 * @return the eventDetails
	 */
	public List<EventListDTO> getEventDetails() {
		return eventDetails;
	}

	/**
	 * @param eventDetails
	 *            the eventDetails to set
	 */
	public void setEventDetails(List<EventListDTO> eventDetails) {
		this.eventDetails = eventDetails;
	}

	/**
	 * @return the categoryDetails
	 */
	public List<CategoryDTO> getCategoryDetails() {
		return categoryDetails;
	}

	/**
	 * @param categoryDetails
	 *            the categoryDetails to set
	 */
	public void setCategoryDetails(List<CategoryDTO> categoryDetails) {
		this.categoryDetails = categoryDetails;
	}

	/**
	 * @return the vendorDetailDTOs
	 */
	public List<VendorDetailDTO> getVendorDetailDTOs() {
		return vendorDetailDTOs;
	}

	/**
	 * @param vendorDetailDTOs
	 *            the vendorDetailDTOs to set
	 */
	public void setVendorDetailDTOs(List<VendorDetailDTO> vendorDetailDTOs) {
		this.vendorDetailDTOs = vendorDetailDTOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchResultDTO [eventDetails=" + eventDetails + ", categoryDetails=" + categoryDetails + "]";
	}

}
