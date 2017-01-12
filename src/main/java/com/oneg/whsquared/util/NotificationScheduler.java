/**
 * 
 */
package com.oneg.whsquared.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.oneg.whsquared.entity.CustomerFavoriteEvents;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.Reminder;
import com.oneg.whsquared.repository.CustomerFavoriteRepository;
import com.oneg.whsquared.repository.EventRepository;
import com.oneg.whsquared.repository.ReminderRepository;

/**
 * @author arivu
 * 
 */
@Component
public class NotificationScheduler {

	public final static String AUTH_KEY_FCM = "AIzaSyAm7X2MDsDnk0p-SLM7nFlwG7S-6QUZbrw";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	@Autowired
	private ReminderRepository reminderRepository;

	@Autowired
	private CustomerFavoriteRepository customerFavoriteRepository;

	@Autowired
	private EventRepository eventRepository;

	// run every 5 seconds
	// @Scheduled(fixedRate = 5000)
	// run every 20 minutes
	@Scheduled(fixedRate = 1000 * 60 * 20)
	public void execute() throws ParseException {
		updateTodaysEvents();
		mailNotificationForFavoriteEvents();
	}

	public void updateTodaysEvents() throws ParseException {
		List<Event> events = eventRepository.findAll();
		if (events != null && !events.isEmpty()) {
			for (Event event : events) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm a");
				String endFromDb = DateUtils.convertDateToString(event.getEventCalender().getEndDate());
				String currentDate = DateUtils.convertDateToString(new Date());
				Date endDate = sdf.parse(endFromDb);
				Date today = sdf.parse(currentDate);
				if (today.before(endDate)) {
					event.setShowToday(true);
				} else {
					event.setShowToday(false);
				}
				eventRepository.save(event);
			}
		}
	}

	public void mailNotificationForFavoriteEvents() throws ParseException {
		List<CustomerFavoriteEvents> customerFavoriteEvents = customerFavoriteRepository.findByMailSent();
		for (CustomerFavoriteEvents customerFavoriteEventsFromDb : customerFavoriteEvents) {
			if (customerFavoriteEventsFromDb != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm a");
				String dateFromDb = DateUtils.convertDateToString(customerFavoriteEventsFromDb.getEvent().getEventCalender().getStartDate());
				String currentDate = DateUtils.convertDateToString(new Date());
				Date endDate = sdf.parse(dateFromDb);
				Date today = sdf.parse(currentDate);
				if (today.before(endDate)) {
					if (sendEmailNotification("Test Content", "arivbits@gmail.com")) {
						customerFavoriteEventsFromDb.setMailSent(true);
						customerFavoriteRepository.save(customerFavoriteEventsFromDb);
					}
				}
			}
		}
	}

	public void reminderNotification() {
		List<Reminder> reminders = reminderRepository.findByReminderSent();
		if (reminders != null && !reminders.isEmpty()) {
			for (Reminder reminder : reminders) {
				String dateFromDb = DateUtils.convertDateToString(reminder.getReminderDateTime());
				String currentDate = DateUtils.convertDateToString(new Date());
				System.out.println(dateFromDb);
				System.out.println(currentDate);
				if (dateFromDb.equalsIgnoreCase(currentDate)) {
					if (reminder.isSendPushReminder() || reminder.isSendMailReminder()) {
						SendPushNotification(reminder.getCustomer().getDeviceId());
						sendEmailNotification("Hello Test Mail Notification", reminder.getCustomer().getEmail());
						System.out.println("Notification Sent");
						reminder.setReminderSent(true);
						reminderRepository.save(reminder);
					}
				}
			}
		}
	}

	public static boolean SendPushNotification(String deviceId) {
		InputStream is = null;
		try {
			// URL url = new URL("https://api.pushbullet.com/v2/pushes");
			URL url = new URL(API_URL_FCM);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			JSONObject json = new JSONObject();
			try {
				json.put("to", deviceId);
				JSONObject info = new JSONObject();
				info.put("title", "Message From Local"); // Notification title
				info.put("body", "This is test push notification"); // Notification
																	// body
				json.put("notification", info);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();
			conn.getInputStream();
			is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			try {
				String line;
				while ((line = reader.readLine()) != null)
					System.out.println(line);
				return true;
			} finally {
				reader.close();
			}
		} catch (MalformedURLException mue) {
			System.out.println("Incorrect URL");
			return false;
		} catch (IOException ioex) {
			System.out.println("I/O Error");
			return false;
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException ignored) {
				}
		}

	}

	public void pushFCMNotification(String userDeviceIdKey) throws Exception {

		String authKey = AUTH_KEY_FCM; // You FCM AUTH key
		String FMCurl = API_URL_FCM;

		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + authKey);
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("to", userDeviceIdKey.trim());
		JSONObject info = new JSONObject();
		info.put("title", "Notificatoin Title"); // Notification title
		info.put("body", "Hello Test notification"); // Notification body
		json.put("notification", info);

		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		wr.flush();
		conn.getInputStream();
	}

	public static boolean sendEmailNotification(String content, String to) {
		EmailFromLocal.sendEmail(content, to);
		return true;
	}

}
