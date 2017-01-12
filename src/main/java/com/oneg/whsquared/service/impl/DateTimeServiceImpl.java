/**
 * 
 */
package com.oneg.whsquared.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.oneg.whsquared.service.DateTimeService;

/**
 * @author arivu
 * 
 */
@Service
public class DateTimeServiceImpl implements DateTimeService {

	@Override
	public Date getCurrentDateAndTime() {
		return new Date();
	}
}
