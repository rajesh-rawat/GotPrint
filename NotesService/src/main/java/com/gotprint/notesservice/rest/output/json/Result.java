package com.gotprint.notesservice.rest.output.json;

import java.io.Serializable;

/**
 * @author Rajesh Rawat
 *
 */
public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2713686732995195026L;

	private String status;

	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
