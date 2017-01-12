/**
 * 
 */
package com.oneg.test;

/**
 * @author arivu
 * 
 */
public class FormatPhoneNumber {

	public static void main(String[] args) {
		String number = "(123)-456-7890";
		String result = number.replaceAll("[-+.^:,]", "");
		System.out.println(result);

	}

}
