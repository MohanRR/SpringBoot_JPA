package com.oneg.whsquared.response.Event;

import java.util.List;

import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.Vendor;

public class EventResponse {

	private Event event;

	private List<Vendor> eventVendor;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Vendor> getEventVendor() {
		return eventVendor;
	}

	public void setEventVendor(List<Vendor> eventVendor) {
		this.eventVendor = eventVendor;
	}

}
