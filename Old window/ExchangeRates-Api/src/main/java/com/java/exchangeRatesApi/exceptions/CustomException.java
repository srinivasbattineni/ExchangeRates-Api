package com.java.exchangeRatesApi.exceptions;

/**
 * This is customException class , when we got custom exception it
 * will raise exception
 * 
 * @author Srinivas Battineni
 *
 */
@SuppressWarnings("serial")
public class CustomException extends RuntimeException {
	public CustomException(String message) {
		super(message);
	}
}
