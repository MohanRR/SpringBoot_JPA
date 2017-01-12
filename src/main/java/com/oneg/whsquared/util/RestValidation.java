/**
 * 
 */
package com.oneg.whsquared.util;

/**
 * @author arivu
 * 
 */
public class RestValidation {

	public static boolean validateField(String value) {
		return (value == null || value.isEmpty() ? false : true);
	}

	public static String validate(String value) {
		return (value == null || value.isEmpty() ? "" : value);
	}

	public static int validateForInteger(Integer value) {
		return (value == null ? 0 : value);
	}

	public static boolean onEmptyvalidation(String value) {
		return (value != null && !value.isEmpty() ? true : false);
	}

	public static boolean validateEmptyField(String value) {
		if (value != null && !value.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}