package com.java.exchangeRatesApi.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.exchangeRatesApi.constants.ExchangeRatesConstants;
import com.java.exchangeRatesApi.exceptions.CustomException;
import com.java.exchangeRatesApi.exceptions.UnauthorizedException;
import com.java.exchangeRatesApi.model.ExchangeRate;

/**
 * @author Srinivas Battineni
 *
 */

public class ExchangeRatesUtility {
	
	static Logger logger = LoggerFactory.getLogger(ExchangeRatesUtility.class);
	private ExchangeRatesUtility() {

	}

	/**
	 * @return
	 */
	public static String getTodayDate() {
		logger.info("ExchangeRatesUtility : getTodayDate");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
	
	
	/**
	 * @param dateString
	 * @param pattern
	 * @return
	 */
	public static boolean isDateValid(String dateString, String pattern)
	{
		boolean result = dateString.matches("^\\d+\\-\\d+\\-\\d+");
		if (result) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				if (sdf.format(sdf.parse(dateString)).equals(dateString))
					return true;
			} catch (ParseException pe) {
				logger.error("Parseing exception", pe);
			}
		}
		return false;
	}
	
	
	/**
	 * @param accessKey
	 * @return
	 * @throws JsonProcessingException
	 */
	public static List<ExchangeRate> getExchangeDataFromAPI(String accessKey) throws JsonProcessingException {
		logger.info("getExchangeDatFromAPI");
		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		try {
			RestTemplate rt = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			String json = rt.getForObject(ExchangeRatesConstants.URL_TWO + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE + ExchangeRatesConstants.CURRENCY_SYMBOL, String.class);
			ExchangeRate exchangeRate = mapper.readValue(json,ExchangeRate.class);
			exchangeRateList.add(exchangeRate);

			String json1 = rt.getForObject(ExchangeRatesConstants.URL_THREE + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE + ExchangeRatesConstants.CURRENCY_SYMBOL, String.class);
			ExchangeRate exchangeRate1 = mapper.readValue(json1,ExchangeRate.class);
			exchangeRateList.add(exchangeRate1);

			String json2 = rt.getForObject(ExchangeRatesConstants.URL_FOUR + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE + ExchangeRatesConstants.CURRENCY_SYMBOL, String.class);
			ExchangeRate exchangeRate2 = mapper.readValue(json2,ExchangeRate.class);
			exchangeRateList.add(exchangeRate2);

			String json3 = rt.getForObject(ExchangeRatesConstants.URL_FIVE + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE + ExchangeRatesConstants.CURRENCY_SYMBOL, String.class);
			ExchangeRate exchangeRate3 = mapper.readValue(json3,ExchangeRate.class);
			exchangeRateList.add(exchangeRate3);

			String json4 = rt.getForObject(ExchangeRatesConstants.URL_SIX + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE + ExchangeRatesConstants.CURRENCY_SYMBOL, String.class);
			ExchangeRate exchangeRate4 = mapper.readValue(json4,ExchangeRate.class);
			exchangeRateList.add(exchangeRate4);

			String json5 = rt.getForObject(ExchangeRatesConstants.URL_SEVEN + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE + ExchangeRatesConstants.CURRENCY_SYMBOL, String.class);
			ExchangeRate exchangeRate5 = mapper.readValue(json5,ExchangeRate.class);

			String json6 = rt.getForObject(ExchangeRatesConstants.URL_EIGHT + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE + ExchangeRatesConstants.CURRENCY_SYMBOL, String.class);
			ExchangeRate exchangeRate6 = mapper.readValue(json6,ExchangeRate.class);

			String json7 = rt.getForObject(ExchangeRatesConstants.URL_NINE + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE + ExchangeRatesConstants.CURRENCY_SYMBOL, String.class);
			ExchangeRate exchangeRate7 = mapper.readValue(json7,ExchangeRate.class);

			String json8 = rt.getForObject(ExchangeRatesConstants.URL_TEN + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE + ExchangeRatesConstants.CURRENCY_SYMBOL, String.class);
			ExchangeRate exchangeRate8 = mapper.readValue(json8,ExchangeRate.class);

			String json9 = rt.getForObject(ExchangeRatesConstants.URL_ELEVEN + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE + ExchangeRatesConstants.CURRENCY_SYMBOL, String.class);
			ExchangeRate exchangeRate9 = mapper.readValue(json9,ExchangeRate.class);

			String json10 = rt.getForObject(ExchangeRatesConstants.URL_TWELVE + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE + ExchangeRatesConstants.CURRENCY_SYMBOL, String.class);
			ExchangeRate exchangeRate10 = mapper.readValue(json10,ExchangeRate.class);

			String json11 = rt.getForObject(ExchangeRatesConstants.URL + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE + ExchangeRatesConstants.CURRENCY_SYMBOL, String.class);
			ExchangeRate exchangeRate11 = mapper.readValue(json11,ExchangeRate.class);

			exchangeRateList.add(exchangeRate5);
			exchangeRateList.add(exchangeRate6);
			exchangeRateList.add(exchangeRate7);
			exchangeRateList.add(exchangeRate8);
			exchangeRateList.add(exchangeRate9);
			exchangeRateList.add(exchangeRate10);
			exchangeRateList.add(exchangeRate11);
		} catch (JsonProcessingException jpe) {
			throw new CustomException(ExchangeRatesConstants.UNKNOW_RESPONSE);
		} catch (HttpClientErrorException hce) {
			throw new UnauthorizedException(ExchangeRatesConstants.INVALID_ACCESS_KEY);
		} catch (ResourceAccessException rae) {
			throw new CustomException(ExchangeRatesConstants.SERVER_ERROR);
		} catch (Exception e) {
			throw new CustomException(ExchangeRatesConstants.BASE_ERROR);
		}
		return exchangeRateList;
	}

	/**
	 * @param accessKey
	 * @return
	 * @throws JsonProcessingException
	 */
	public static List<ExchangeRate> getExchangeRatesDataFromAPI(String accessKey) throws JsonProcessingException {
		logger.info("getExchangeDatFromAPI");
		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		try {
			RestTemplate rt = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			String json = rt.getForObject(ExchangeRatesConstants.URL_TWO + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE, String.class);
			ExchangeRate exchangeRate = mapper.readValue(json,ExchangeRate.class);
			exchangeRateList.add(exchangeRate);

			String json1 = rt.getForObject(ExchangeRatesConstants.URL_THREE + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE, String.class);
			ExchangeRate exchangeRate1 = mapper.readValue(json1,ExchangeRate.class);
			exchangeRateList.add(exchangeRate1);

			String json2 = rt.getForObject(ExchangeRatesConstants.URL_FOUR + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE, String.class);
			ExchangeRate exchangeRate2 = mapper.readValue(json2,ExchangeRate.class);
			exchangeRateList.add(exchangeRate2);

			String json3 = rt.getForObject(ExchangeRatesConstants.URL_FIVE + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE, String.class);
			ExchangeRate exchangeRate3 = mapper.readValue(json3,ExchangeRate.class);
			exchangeRateList.add(exchangeRate3);

			String json4 = rt.getForObject(ExchangeRatesConstants.URL_SIX + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE, String.class);
			ExchangeRate exchangeRate4 = mapper.readValue(json4,ExchangeRate.class);
			exchangeRateList.add(exchangeRate4);

			String json5 = rt.getForObject(ExchangeRatesConstants.URL_SEVEN + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE, String.class);
			ExchangeRate exchangeRate5 = mapper.readValue(json5,ExchangeRate.class);

			String json6 = rt.getForObject(ExchangeRatesConstants.URL_EIGHT + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE + ExchangeRatesConstants.CURRENCY_SYMBOL, String.class);
			ExchangeRate exchangeRate6 = mapper.readValue(json6,ExchangeRate.class);

			String json7 = rt.getForObject(ExchangeRatesConstants.URL_NINE + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE, String.class);
			ExchangeRate exchangeRate7 = mapper.readValue(json7,ExchangeRate.class);

			String json8 = rt.getForObject(ExchangeRatesConstants.URL_TEN + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE, String.class);
			ExchangeRate exchangeRate8 = mapper.readValue(json8,ExchangeRate.class);

			String json9 = rt.getForObject(ExchangeRatesConstants.URL_ELEVEN + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE, String.class);
			ExchangeRate exchangeRate9 = mapper.readValue(json9,ExchangeRate.class);

			String json10 = rt.getForObject(ExchangeRatesConstants.URL_TWELVE + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE, String.class);
			ExchangeRate exchangeRate10 = mapper.readValue(json10,ExchangeRate.class);

			String json11 = rt.getForObject(ExchangeRatesConstants.URL + ExchangeRatesConstants.ACCESS_KEY + accessKey
					+ ExchangeRatesConstants.BASE, String.class);
			ExchangeRate exchangeRate11 = mapper.readValue(json11,ExchangeRate.class);

			exchangeRateList.add(exchangeRate5);
			exchangeRateList.add(exchangeRate6);
			exchangeRateList.add(exchangeRate7);
			exchangeRateList.add(exchangeRate8);
			exchangeRateList.add(exchangeRate9);
			exchangeRateList.add(exchangeRate10);
			exchangeRateList.add(exchangeRate11);
		} catch (JsonProcessingException jpe) {
			throw new CustomException(ExchangeRatesConstants.UNKNOW_RESPONSE);
		} catch (HttpClientErrorException hce) {
			throw new UnauthorizedException(ExchangeRatesConstants.INVALID_ACCESS_KEY);
		} catch (ResourceAccessException rae) {
			throw new CustomException(ExchangeRatesConstants.SERVER_ERROR);
		} catch (Exception e) {
			throw new CustomException(ExchangeRatesConstants.BASE_ERROR);
		}
		return exchangeRateList;
	}
}
