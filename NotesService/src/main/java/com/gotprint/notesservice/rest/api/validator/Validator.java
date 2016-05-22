package com.gotprint.notesservice.rest.api.validator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gotprint.notesservice.constants.Constants;
import com.gotprint.notesservice.generic.exception.BusinessException;

public class Validator implements Constants{

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
	
	public static boolean validate(Map<String, String> map) throws BusinessException{
		String title = map.get(KEY_TITLE);
		String note = map.get(KEY_NOTE);
		String email = map.get(KEY_EMAIL);
		StringBuffer exceptionString = new StringBuffer();
		if(title==null || title.length() > MAX_TITLE_SIZE){
			exceptionString.append("Title Exceeds Size limit. Max allowed size is "+MAX_TITLE_SIZE+" characters.\n");
		}
		if(note==null || note.length() > MAX_NOTE_SIZE){
			exceptionString.append("Note Exceeds Size limit. Max allowed size is "+MAX_NOTE_SIZE+" characters.\n");
		}
		if(email==null || !validateEmail(email)){
			exceptionString.append("Invalid Email.");
		}
		if(exceptionString.length()>0){
			throw new BusinessException(exceptionString.toString());
		}
		return true;
	}
}
