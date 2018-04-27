package com.kubris.weka.rest.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author paunovicm
 *
 */


public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
	

	/* (non-Javadoc)
	 * @see org.springframework.security.web.AuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authException) {

		try {
			response.getWriter().write("Not Authenticated Exception!");
			response.setStatus(401);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}