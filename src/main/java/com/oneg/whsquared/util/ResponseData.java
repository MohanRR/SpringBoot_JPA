package com.oneg.whsquared.util;

/**
 * 
 * @author arivu
 * 
 */
public class ResponseData {

	private String message;
	private boolean result;
	private Object data;

	public ResponseData() {

	}

	/**
	 * 
	 * @param message
	 * @param result
	 * @param data
	 */
	public ResponseData(String message, boolean result, Object data) {
		super();
		this.message = message;
		this.result = result;
		this.data = data;
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
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

}