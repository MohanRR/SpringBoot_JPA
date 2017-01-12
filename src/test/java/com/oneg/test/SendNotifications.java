package com.oneg.test;

import java.util.Date;
import java.util.Map;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class SendNotifications {
	ApnsService pushService = APNS.newService().withCert("<path to your server p12>", "password to you server p12").withSandboxDestination().build();

	void notify(String message) {
		String payload = APNS.newPayload().alertBody(message).build();
		String token = "<registration id>";
		pushService.push(token, payload);
		Map<String, Date> inactiveDevices = pushService.getInactiveDevices();
		for (String deviceToken : inactiveDevices.keySet()) {
			Date inactiveAsOf = inactiveDevices.get(deviceToken);
			System.out.println("Inactive device token : " + deviceToken);
			System.out.println("Tried to send notification at : " + inactiveAsOf);
		}

	}

	public static void main(String[] args) {
		try {
			SendNotifications object = new SendNotifications();
			object.notify("Notify my iPhone");
		} catch (Exception e) {
			System.out.println("Exception occured : " + e.getMessage());
		}
	}
}