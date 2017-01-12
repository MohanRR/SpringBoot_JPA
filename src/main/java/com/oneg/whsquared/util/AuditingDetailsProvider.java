package com.oneg.whsquared.util;

import java.util.Calendar;

import org.springframework.data.auditing.DateTimeProvider;

import com.oneg.whsquared.service.DateTimeService;

public class AuditingDetailsProvider implements DateTimeProvider {

	private final DateTimeService dateTimeService;

	/**
	 * 
	 */
	public AuditingDetailsProvider(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.auditing.DateTimeProvider#getNow()
	 */
	@Override
	public Calendar getNow() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTimeService.getCurrentDateAndTime());
		return calendar;
	}

}