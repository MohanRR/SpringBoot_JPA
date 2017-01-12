package com.oneg.whsquared.util;

/**
 * @author Jaiprabhu
 * This class set and get status,messages and user records.
 */
public class ResponseType<T> {
	private String status;
	private String message;
	private T data;
	
	public ResponseType(String status, String message, T data){
		this.status =  status;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	

}
