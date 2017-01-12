/**
 * 
 */
package com.oneg.whsquared.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author arivu
 * 
 */
public class DateUtils {

	private static final String DATE_FORMAT = "MM/DD/YY";
	private static final String _DATE_FORMAT = "dd/MM/yyyy";
	private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
	// private static final String DATE_FORMAT2 = "dd/MMM/yyyy";
	private static final String TIME_FORMAT = "hh:mm a";

	public static Date formatDate(String date) {
		DateFormat df = new SimpleDateFormat(_DATE_FORMAT);
		Date startDate = null;
		try {
			startDate = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startDate;
	}

	public static Date formatDateTime(String date) {
		SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
		Date startDate = null;
		try {
			startDate = df.parse(date);
			String newDateString = df.format(startDate);
			System.out.println(newDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startDate;
	}

	public static Date formatTime(String time) {
		Date date = null;
		try {
			date = new SimpleDateFormat("HH:mm").parse(time);
		} catch (ParseException e) {
			System.out.println("Please enter time in format HH:mm");
		}
		return new Date(date.getTime());
	}

	public static String formatDateToString(Date date) {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		String startDate = df.format(date);
		return startDate;
	}

	public static String formatTimeToString(Date date) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat(TIME_FORMAT);
			String startDate = df.format(date);
			return startDate;
		} else {
			return "";
		}
	}

	public static Date convertStringToDate(String dateInString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		// String dateInString = "31/08/1982 10:20:56";
		// String dateInString = "15/06/2009 12:00 PM";
		Date date = new Date();
		try {
			date = sdf.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String convertDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm a");
		String dateString = sdf.format(date);
		return dateString;
	}

	public static String formatDateTimeFromString(String dateTime) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		String displayValue = "";
		try {
			date = dateFormatter.parse(dateTime);
			// Get time from date
			SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
			displayValue = timeFormatter.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return displayValue;
	}

	public static boolean compareDates(long date1, long date2) {
		if (date1 == date2) {
			return true;
		} else {
			return false;
		}
	}
}
