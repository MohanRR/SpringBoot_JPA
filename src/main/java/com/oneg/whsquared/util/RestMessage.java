/**
 * 
 */
package com.oneg.whsquared.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.oneg.whsquared.security.JwtUtil;

/**
 * @author arivu
 * 
 */
public class RestMessage {

	@Autowired
	private JwtUtil jwtUtil;

	public static ResponseData onValidationErrorMessage(String message) {
		return new ResponseData(message, false, "");
	}

	public static ResponseData onSuccessMessage(Object data) {
		return new ResponseData("Success", true, data);
	}

	public static ResponseData onSuccess() {
		return new ResponseData("Success", true, "");
	}

	public static ResponseData onSuccess(String message) {
		return new ResponseData(message, true, "");
	}

	public static CustomerResponseData onErrorMessage(String message) {
		return new CustomerResponseData(message, false, "", "");
	}

	public static CustomerResponseData onSuccessMessage(String message) {
		return new CustomerResponseData(message, true, "", "");
	}

	public static CustomerResponseData onSuccessMessageWithToken(String message) {
		return new CustomerResponseData(message, true, "", "");
	}
}
