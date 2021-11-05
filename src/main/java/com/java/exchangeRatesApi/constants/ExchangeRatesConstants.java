package com.java.exchangeRatesApi.constants;

/**
 * @author Srinivas Battineni
 *
 */
public class ExchangeRatesConstants {
	private ExchangeRatesConstants() {
		
	}
	public static final String URL_TWO= "http://api.exchangeratesapi.io/v1/2020-11-01";
	public static final String URL_THREE= "http://api.exchangeratesapi.io/v1/2020-12-01";
	public static final String URL_FOUR= "http://api.exchangeratesapi.io/v1/2021-01-01";
	public static final String URL_FIVE= "http://api.exchangeratesapi.io/v1/2021-02-01";
	public static final String URL_SIX= "http://api.exchangeratesapi.io/v1/2021-03-01";
	public static final String URL_SEVEN= "http://api.exchangeratesapi.io/v1/2021-04-01";
	public static final String URL_EIGHT= "http://api.exchangeratesapi.io/v1/2021-05-01";
	public static final String URL_NINE= "http://api.exchangeratesapi.io/v1/2021-06-01";
	public static final String URL_TEN= "http://api.exchangeratesapi.io/v1/2021-07-01";
	public static final String URL_ELEVEN= "http://api.exchangeratesapi.io/v1/2021-08-01";
	public static final String URL_TWELVE= "http://api.exchangeratesapi.io/v1/2021-09-01";
	public static final String URL= "http://api.exchangeratesapi.io/v1/2021-10-01";
	public static final String BASE_URL = "http://api.exchangeratesapi.io/v1/";
	public static final String ACCESS_KEY = "?access_key=";
	public static final String ACCESS_KEY_VALUE = "3c5c516902e02cc4c7fcd18f9448059b";
	public static final String BASE = "&base=EUR";
	public static final String SYMBOL = "&symbols=GBP";
	public static final String CURRENCY_SYMBOL = "&symbols=GBP,USD,HKD";
	public static final String DATE_FORMATE = "yyyy-MM-dd";
	public static final String SAVE_MSG ="Exchange rates data inserted successfully";
	public static final String N0_DATA_FOUND = "Data not recived from Echange Rates Api";
	public static final String INVALID_INPUT  = "Invalid Date";
	public static final String UNKNOW_RESPONSE  ="Unknow data response ";
	public static final String INVALID_ACCESS_KEY  ="Invalid API Access Key";
	public static final String SERVER_ERROR  ="Intrnal Server error";
	public static final String BASE_ERROR  = "Somthing went wrong can you try after sometime";
	public static final String N0_DATA_FOUND_DATE = "No Exchange Rate data found for this Date";
	public static final String N0_DATA_FOUND_DATE_TODAY = "No Exchange Rate data found for today Date";
	public static final String N0_DATA_FOUND_BTW_DATES ="No Exchange Rate data found for in b/w Dates";
	public static final String N0_DATA_FOUND_BTW_DATES_TODAY_DATE  = "No Exchange Rate data found in b/w Dates and Today date";
}
