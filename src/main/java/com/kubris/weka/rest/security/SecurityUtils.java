package com.kubris.weka.rest.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * @author paunovicm
 *
 */

public class SecurityUtils {
	
	/*
	public static UserAccount getUserFromContext() {

		SecurityContext securityContext = SecurityContextHolder.getContext();
		Object details = null;

		try {
			details = securityContext.getAuthentication().getPrincipal();
		} catch (NullPointerException e) {
			details = null;
		}

		UserAccount user = null;
		if (details != null && details instanceof UserAccount) {
			user = (UserAccount) details;
		}

		return user;
	}
	*/

}
