package test.hwms.service;

import test.hwms.domain.UserNotFoundException;

public class ResponseError {
	private String path;
	private String error;
	private String message;
	private String exception;
	private long timestamp;

	public ResponseError(String path, Exception e) {
		this(path, e, "Error");
	}

	public ResponseError(String path, UserNotFoundException e) {
		this(path, e, "Not Found");
	}
	
	private ResponseError(String path, Exception e, String error) {
		this.path = path;
		this.error = error;
		message = e.getMessage();
		exception = e.getClass().getName();
		timestamp = System.currentTimeMillis();
	}
	
	public String getPath() {
		return path;
	}
	
	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}
	
	public String getException() {
		return exception;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
}
