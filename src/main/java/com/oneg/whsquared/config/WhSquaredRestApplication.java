package com.oneg.whsquared.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Anbukkani G
 * 
 */
@SpringBootApplication
public class WhSquaredRestApplication extends SpringBootServletInitializer {

	private static Logger LOGGER = LoggerFactory.getLogger(WhSquaredRestApplication.class);

	public static void main(String[] args) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("App is started");
		}
		SpringApplication.run(WhSquaredRestApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WhSquaredRestApplication.class);
	}
}
