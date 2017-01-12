/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class VendorResponseData {

	private String message;

	private boolean result;

	private Object dataByEvent;

	private Object dataByEventAndVendor;

	private Object dataByEventAndCategory;

	public VendorResponseData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param result
	 * @param dataByEvent
	 * @param dataByEventAndVendor
	 * @param dataByEventAndCategory
	 */
	public VendorResponseData(String message, boolean result, Object dataByEvent, Object dataByEventAndVendor, Object dataByEventAndCategory) {
		super();
		this.message = message;
		this.result = result;
		this.dataByEvent = dataByEvent;
		this.dataByEventAndVendor = dataByEventAndVendor;
		this.dataByEventAndCategory = dataByEventAndCategory;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * @return the dataByEvent
	 */
	public Object getDataByEvent() {
		return dataByEvent;
	}

	/**
	 * @param dataByEvent
	 *            the dataByEvent to set
	 */
	public void setDataByEvent(Object dataByEvent) {
		this.dataByEvent = dataByEvent;
	}

	/**
	 * @return the dataByEventAndVendor
	 */
	public Object getDataByEventAndVendor() {
		return dataByEventAndVendor;
	}

	/**
	 * @param dataByEventAndVendor
	 *            the dataByEventAndVendor to set
	 */
	public void setDataByEventAndVendor(Object dataByEventAndVendor) {
		this.dataByEventAndVendor = dataByEventAndVendor;
	}

	/**
	 * @return the dataByEventAndCategory
	 */
	public Object getDataByEventAndCategory() {
		return dataByEventAndCategory;
	}

	/**
	 * @param dataByEventAndCategory
	 *            the dataByEventAndCategory to set
	 */
	public void setDataByEventAndCategory(Object dataByEventAndCategory) {
		this.dataByEventAndCategory = dataByEventAndCategory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VendorResponseData [message=" + message + ", result=" + result + ", dataByEvent=" + dataByEvent + ", dataByEventAndVendor=" + dataByEventAndVendor
				+ ", dataByEventAndCategory=" + dataByEventAndCategory + "]";
	}

}
