package com.gotprint.notesservice.constants;

/**
 * @author Rajesh Rawat
 *
 */
public interface Constants {

	public static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static final int MAX_TITLE_SIZE=50;
	public static final int MAX_NOTE_SIZE=50;
	
	public static final String KEY_EMAIL="email";
	public static final String KEY_NOTE="note";
	public static final String KEY_TITLE="title";
	
	public static final String STATUS_SUCCESS="SUCCESS";
	public static final String STATUS_FAILURE="FAILURE";
	
	public static final String AUTHENTICATION_HEADER = "Authorization";

	public static final String USER_NAME = "user_name";

}
