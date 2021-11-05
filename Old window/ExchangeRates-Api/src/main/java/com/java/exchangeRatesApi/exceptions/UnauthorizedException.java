package com.java.exchangeRatesApi.exceptions;

/**
 * @author Srinivas Battineni
 *
 */
@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException(String message) {
        super(message);
    }
}
