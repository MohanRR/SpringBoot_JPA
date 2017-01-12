/**
 * 
 */
package com.oneg.test;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

/**
 * @author arivu
 * 
 */
public class AddressTest {

	public static void main(String[] args) {
		try {
			for (int i = 0; i <= 5; i++) {
				AddressTest.testAddress();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testAddress() throws Exception {
		WebConversation conversation = new WebConversation();

		WebRequest request = new GetMethodWebRequest("http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address="
				+ "964 Farmington Ave, West Hartford, CT, Connecticut 06107");

		try {
			WebResponse response = conversation.getResource(request);
			JSONObject jsonResp = new JSONObject(response.getText());
			String status = jsonResp.getString("status");
			if (status.equals("OK")) {
				JSONArray results = jsonResp.getJSONArray("results");
				JSONObject location = results.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
				System.out.println(Double.toString(location.getDouble("lat")));
				System.out.println(Double.toString(location.getDouble("lng")));
			} else {
				throw new Exception("Invalid Address.Please enter the valid address.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
