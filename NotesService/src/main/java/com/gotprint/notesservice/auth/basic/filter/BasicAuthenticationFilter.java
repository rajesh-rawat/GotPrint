package com.gotprint.notesservice.auth.basic.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gotprint.notesservice.auth.basic.service.BasicAuthenticationService;
import com.gotprint.notesservice.constants.Constants;

/**
 * @author Rajesh Rawat
 * 
 * This is the Authentication Filter Class.
 * Only Basic Authentication is supported.
 *
 */
public class BasicAuthenticationFilter implements Filter, Constants{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;

			BasicAuthenticationService basicAuthenticationService = new BasicAuthenticationService();

			boolean authenticationStatus = basicAuthenticationService.authenticate(httpServletRequest);

			if (authenticationStatus) {
				filter.doFilter(request, response);
			} else {
				if (response instanceof HttpServletResponse) {
					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
