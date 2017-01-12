package com.oneg.whsquared.util;

/**
 * 
 * @author arivu
 * 
 */
public class CustomerResponseData {

	private String message;
	private boolean result;
	private Object data;
	public String token;

	public CustomerResponseData() {

	}

	/**
	 * 
	 * @param message
	 * @param result
	 * @param data
	 */
	public CustomerResponseData(String message, boolean result, Object data) {
		super();
		this.message = message;
		this.result = result;
		this.data = data;
	}

	/**
	 * @param message
	 * @param result
	 * @param data
	 * @param token
	 */
	public CustomerResponseData(String message, boolean result, Object data, String token) {
		super();
		this.message = message;
		this.result = result;
		this.data = data;
		this.token = token;
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

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

}