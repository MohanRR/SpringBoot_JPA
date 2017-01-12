package com.oneg.whsquared.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oneg.whsquared.entity.Customer;

@Component("controllerUtil")
public class ControllerUtil {

	public static HttpServletRequest request = null;

	public static boolean isMigrationRequest = Boolean.FALSE;

	@Autowired(required = true)
	private HttpSession session;

	/**
	 * 
	 * @return
	 */
	public Customer getCurrentUser() {
		if (this.session == null || this.session.getAttribute("currentUser") == null)
			return null;
		Customer userDetail = (Customer) this.session.getAttribute("currentUser");
		return userDetail;
	}

	public String getCuser() {
		// Get it from session..
		if (this.session == null)
			return null;

		return (String) this.session.getAttribute("cuser");
	}

	public void addUser(Customer u) {
		if (session.getAttribute("AuthenticatedUser") == null)
			session.setAttribute("AuthenticatedUser", u);
	}

	public HttpSession getSession() {
		return session;
	}

	public void addToSession(String key, Object o) {
		if (session.getAttribute(key) != null)
			session.removeAttribute(key);
		session.setAttribute(key, o);
	}

	public boolean isObjectExist(String key) {
		return session.getAttribute(key) == null ? false : true;
	}

	public Object getObjectFromSesion(String key) {
		return session.getAttribute(key);
	}

	public void removeFromSession(String key) {
		if (session.getAttribute(key) != null)
			session.removeAttribute(key);
	}

	public static void intMigrationRequest(HttpServletRequest servletRequest) {
		request = servletRequest;
		isMigrationRequest = Boolean.TRUE;
	}

	public static void resetMigrationRequest() {
		request = null;
		isMigrationRequest = Boolean.FALSE;
	}

	public void updateRequiredSession(Customer customerFromDb) {
		addToSession("currentUser", customerFromDb);
		addUser(customerFromDb);
		addToSession("cuser", customerFromDb.getUserName());
	}
}
