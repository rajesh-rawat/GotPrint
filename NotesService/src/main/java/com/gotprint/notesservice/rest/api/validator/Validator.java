package com.gotprint.notesservice.rest.api.validator;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gotprint.notesservice.constants.Constants;
import com.gotprint.notesservice.domain.object.Note;
import com.gotprint.notesservice.generic.exception.BusinessException;

/**
 * @author Rajesh Rawat
 *
 * Validator class validates basic validations as well as business validations.
 * 
 */
public class Validator implements Constants {

	/**
	 * Validate hex with regular expression
	 * 
	 * @param email
	 *            email for validation
	 * @return true valid email, false invalid email
	 */
	public static boolean validateEmail(final String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Validates
	 * 1) Title is not more than 50 characters
	 * 2) Note is not more than 1000 characters
	 * 3) Email is valid
	 * 
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	public static boolean validate(Map<String, String> map) throws BusinessException {
		String title = map.get(KEY_TITLE);
		String note = map.get(KEY_NOTE);
		String email = map.get(KEY_EMAIL);
		StringBuffer exceptionString = new StringBuffer();
		if (title == null || title.length() > MAX_TITLE_SIZE) {
			exceptionString
					.append("Title Exceeds Size limit. Max allowed size is " + MAX_TITLE_SIZE + " characters.\n");
		}
		if (note == null || note.length() > MAX_NOTE_SIZE) {
			exceptionString.append("Note Exceeds Size limit. Max allowed size is " + MAX_NOTE_SIZE + " characters.\n");
		}
		if (email == null || !validateEmail(email)) {
			exceptionString.append("Invalid Email.");
		}
		if (exceptionString.length() > 0) {
			throw new BusinessException(exceptionString.toString());
		}
		return true;
	}

	/**
	 * Validates 
	 * 1) Logged in user should not be able to Create/Update/Delete any other users Notes
	 * 
	 * @param authString
	 * @param note
	 * @throws BusinessException
	 */
	public static void vaidateOperationAccess(String authString, Note note) throws BusinessException {
		if (authString == null) {
			throw new BusinessException("Logged In User Could Not be Authenticated");
		}
		final String encodedUserPassword = authString.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();

		if (username == null) {
			throw new BusinessException("Logged In User Could Not be Authenticated");
		}
		if (note.getUser() == null || note.getUser().getEmail() == null) {
			throw new BusinessException("User Not mentioned in User Input");
		}
		if (!username.equalsIgnoreCase(note.getUser().getEmail())) {
			throw new BusinessException("Logged In User " + username
					+ " do not have permissions to create/update/delete Notes for user " + note.getUser().getEmail());
		}
	}

}
