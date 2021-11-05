package com.java.exchangeRatesApi.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author Srinivas Battineni
 *
 */
public interface ExchangeRatesApiService {
	public String getExchangeRates(String accessKey) throws JsonProcessingException;
	public String getExchangeRatesByDate(String accessKey, String date) throws JsonProcessingException;
	public String getExchangeRatesData(String accessKey) throws JsonProcessingException;
	public Object getExchangeRatesInfoByDate(String date);
	public List<Object> getExchangeRatesInBwtDates(String fromDate, String toDate);
}
