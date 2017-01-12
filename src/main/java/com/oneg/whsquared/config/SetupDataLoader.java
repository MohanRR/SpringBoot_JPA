package com.oneg.whsquared.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.oneg.whsquared.entity.WHVersionDetails;
import com.oneg.whsquared.repository.WHVersionDetailsRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private WHVersionDetailsRepository whVersionDetailsRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createAppVerionDetailsIfNotFound("Android Version", "ANDROID_APP_VERSION", "3.22");
		createAppVerionDetailsIfNotFound("iOS Version", "IOS_APP_VERSION", "1.1");
	}

	private void createAppVerionDetailsIfNotFound(String versionName, String versionKey, String versionValue) {
		WHVersionDetails whVersionDetails = whVersionDetailsRepository.findByVersionName(versionName);
		if (whVersionDetails == null) {
			whVersionDetailsRepository.save(new WHVersionDetails(versionKey, versionName, versionValue));
		}
	}
}