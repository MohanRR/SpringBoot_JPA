package com.oneg.whsquared.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.entity.TimeZone;
import com.oneg.whsquared.repository.TimeZoneRepository;
import com.oneg.whsquared.util.ResponseType;
import com.oneg.whsquared.util.Util;
import com.oneg.whsquared.util.WHStatus;

@RestController
@RequestMapping(value = "api/timeZone")
public class TimeZoneController {

	@Autowired
	TimeZoneRepository timeZoneRepository;

	@Autowired
	private Util util;

	@Transactional
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody ResponseType getTimeZones() throws Exception {
		List<TimeZone> timeZone = timeZoneRepository.findAll();
		return util.response(WHStatus.SUCCESS.value(), "", timeZone);
	}
}
