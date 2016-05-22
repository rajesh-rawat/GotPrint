package com.gotprint.notesservice.auth.basic.service;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.gotprint.notesservice.constants.Constants;
import com.gotprint.notesservice.domain.object.User;
import com.gotprint.notesservice.service.DaoService;

/**
 * @author Rajesh Rawat
 * 
 * Basic Authentication Service
 * It gets credentials from Request Header and compares it with credentials stored in database
 *
 */
public class BasicAuthenticationService {
	public boolean authenticate(HttpServletRequest httpServletRequest) {

		String authCredentials = httpServletRequest.getHeader(Constants.AUTHENTICATION_HEADER);

		if (null == authCredentials)
			return false;
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		DaoService dao = new DaoService();
		User user = dao.findUserByEmail(username);
		boolean authenticationStatus = (user == null) ? false : user.getPassword().equals(password);
		httpServletRequest.setAttribute(Constants.USER_NAME, username);
		return authenticationStatus;
	}
}
