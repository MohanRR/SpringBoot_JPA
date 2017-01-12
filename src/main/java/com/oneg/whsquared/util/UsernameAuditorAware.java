package com.oneg.whsquared.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.User;

public class UsernameAuditorAware implements AuditorAware<String> {

	@Autowired
	private ControllerUtil controllerUtil;

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.AuditorAware#getCurrentAuditor()
	 */
	@Override
	public String getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return "System";
		}
		if (authentication.getPrincipal().equals("anonymousUser")) {
			return "anonymousUser";
		}
		if (authentication.getPrincipal() instanceof User) {
			return ((User) authentication.getPrincipal()).getUserName();
		}
		return ((Customer) authentication.getPrincipal()).getUserName();
	}
}