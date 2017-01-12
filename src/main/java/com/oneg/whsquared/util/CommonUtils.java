package com.oneg.whsquared.util;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Kartheeswaran
 *
 */
public class CommonUtils {

	private static Logger logger = Logger.getLogger(CommonUtils.class.getName());

	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}

	public static double convertDistanceUnit(double dist, String fUnit, String tUnit) {
		fUnit = fUnit.trim().toUpperCase();
		tUnit = tUnit.trim().toUpperCase();

		if (fUnit.equals(tUnit))
			return dist;

		if (fUnit.equals("K"))
			dist = dist / 1.609344;
		else if (fUnit.equals("N"))
			dist = dist / 0.8684;

		if (tUnit.equals("K")) {
			dist = dist * 1.609344;
		} else if (tUnit.equals("N")) {
			dist = dist * 0.8684;
		}

		return dist;
	}

	// This function converts decimal degrees to radians
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	// This function converts radians to decimal degrees
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	public static boolean validateString(String value) {
		if (value == null || value.trim().equals("")) {
			return false;
		}
		return true;
	}

	public static boolean validateList(List<?> list) {
		if (list == null || list.isEmpty()) {
			return false;
		}
		return true;
	}

	public static int getRandomNumber(int minimum, int maximum) {
		Random rn = new Random();
		int n = maximum - minimum + 1;
		int i = rn.nextInt() % n;
		return minimum + i;
	}

	public static String generateRandomInteger(int length) {
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			numbers.add(i);
		}

		Collections.shuffle(numbers);

		String result = "";
		for (int i = 0; i < length; i++) {
			result += numbers.get(i).toString();
		}

		return result;
	}

	public static String passwordToHash(String password) {
		if (!CommonUtils.validateString(password))
			password = "fmn@123";
		String passwordToHash = password;
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(passwordToHash.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// System.out.println(generatedPassword);
		return generatedPassword;
	}

	public static String convertDateToStringWithFormat(Date date, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		String dateString = f.format(date);
		return dateString;
	}

	public static Date convertStringToDateWithFormat(String dateString, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date date = new Date();
		try {
			date = f.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public boolean equalLists(List<String> one, List<String> two) {
		if (one == null && two == null) {
			return true;
		}

		if ((one == null && two != null) || one != null && two == null || one.size() != two.size()) {
			return false;
		}

		// to avoid messing the order of the lists we will use a copy
		// as noted in comments by A. R. S.
		one = new ArrayList<String>(one);
		two = new ArrayList<String>(two);

		Collections.sort(one);
		Collections.sort(two);
		return one.equals(two);
	}

	public static String getFile(String fileName) {

		StringBuilder result = new StringBuilder("");

		// Get file from resources folder
		ClassLoader classLoader = CommonUtils.class.getClassLoader();

		// HubCustomerOrderServiceImpl.class
		File file = new File(classLoader.getResource(fileName).getFile());
		System.out.println("File path: " + file.toString());
		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result.toString();

	}

	public static String getObjectMapperWithSerializationFeature(Object obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String res = "";
		try {
			mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			mapper.setSerializationInclusion(Include.NON_NULL);
			res = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public static long getDifferenceInMinutes(String startDateString, String endDateString, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(startDateString);
			d2 = format.parse(endDateString);

			// in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");
			return diffInMinutes;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;

	}

	public static long getDifferenceInHours(Date startDate, Date endDate) {

		try {

			// in milliseconds
			long diff = endDate.getTime() - startDate.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			// long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);
			long diffInHours = TimeUnit.MILLISECONDS.toHours(diff);
			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");

			return diffInHours;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;

	}

	public static Date addHours(Date date, int hours) {
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(date); // sets calendar time/date
		cal.add(Calendar.HOUR_OF_DAY, hours); // adds hour
		return cal.getTime(); // returns new date object, one hour in the future
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(date); // sets calendar time/date
		cal.add(Calendar.DATE, days); // adds days
		return cal.getTime(); // returns new date object, one hour in the future
	}

	public static Date addSeconds(Date date, int seconds) {
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(date); // sets calendar time/date
		cal.add(Calendar.SECOND, seconds); // adds days
		return cal.getTime(); // returns new date object, one hour in the future
	}

	public static Date getFirstDateOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, cal.getActualMinimum(Calendar.DAY_OF_WEEK));
		return cal.getTime();
	}

	public static Date getLastDateOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
		return cal.getTime();
	}

	public static Date getFirstDateOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date getLastDateOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date getFirstDateOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));
		return cal.getTime();
	}

	public static Date getLastDateOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static String formatDouble(double value) {
		DecimalFormat df = new DecimalFormat("#.00");
		String angleFormated = df.format(value);
		if (angleFormated.indexOf(".") == 0)
			angleFormated = "0" + angleFormated;
		else if (angleFormated.indexOf("-") == 0 && angleFormated.indexOf(".") == 1)
			angleFormated = angleFormated.replace("-", "-0");
		return angleFormated;
	}

}
