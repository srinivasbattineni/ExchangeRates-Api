package com.exchangerates.response;

import com.exchangerates.constants.Constants;

public class BaseResponse {

	private long timestamp;
	private String status;
	private String message;
	private int statusCode;

	/**
	 * 
	 * @param status
	 * @param message
	 * @param statusCode
	 */
	public BaseResponse(boolean status, String message, int statusCode) {

		this.timestamp = System.currentTimeMillis();
		this.status = status ? Constants.SUCCESS : Constants.FAILURE;
		this.message = message;
		this.statusCode = statusCode;
	}
	
	
	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	
	/**
	 * @param timestamp
	 * set the timestamp
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 * set the status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @param message
	 * set the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 
	 * @return statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * 
	 * @param statusCode
	 * set the statusCode
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "Response [timestamp=" + timestamp + ", status=" + status
				+ ", message=" + message + ", statusCode=" + statusCode + "]";
	}

}
