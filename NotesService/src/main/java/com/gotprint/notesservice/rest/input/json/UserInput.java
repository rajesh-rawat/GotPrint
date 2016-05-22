package com.gotprint.notesservice.rest.input.json;

import java.io.Serializable;

public class UserInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3173986960698508475L;

	private String email;

	private String note;

	private String title;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
