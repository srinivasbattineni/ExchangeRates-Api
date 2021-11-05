package com.java.exchangeRatesApi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.exchangeRatesApi.constants.ExchangeRatesConstants;
import com.java.exchangeRatesApi.exceptions.CustomException;
import com.java.exchangeRatesApi.exceptions.UnauthorizedException;
import com.java.exchangeRatesApi.model.ExchangeRate;
import com.java.exchangeRatesApi.repository.ExchangeRatesApiRepository;
import com.java.exchangeRatesApi.service.ExchangeRatesApiService;
import com.java.exchangeRatesApi.utility.ExchangeRatesUtility;

/**This Service class it have business logic to call the Exchange Rate APi with help of 
 * RestTemplate and will have data into H2 DB and will get data from H2 DB and send to Controller 
 * To display data to user
 * @author Srinivas Battineni
 *
 */
@Service
public class ExchangeRatesApiServiceImpl implements ExchangeRatesApiService {
	Logger logger = LoggerFactory.getLogger(ExchangeRatesApiServiceImpl.class);

	@Autowired
	ExchangeRatesApiRepository exchangeRatesApiRepository;

	/**
	 * This method received the request for GBP/USD/HKD currencies Exchange rate details 
	 * from controller and will call Exchange API and get Exchange rate details and save 
	 * details into H2 DB and sending response back user.
	 */
	public String getExchangeRates(String accessKey) throws JsonProcessingException {
		logger.info("ExchangeRateService : getExchangeRates");
		List<ExchangeRate> exchangeRateData = ExchangeRatesUtility.getExchangeDataFromAPI(accessKey);
		if(!exchangeRateData.isEmpty()) {
			logger.info("getExchangeRates : Inserting data into table");
			exchangeRatesApiRepository.saveAll(exchangeRateData);
			return ExchangeRatesConstants.SAVE_MSG;
		}
		return ExchangeRatesConstants.N0_DATA_FOUND;
	}
	
	/**
	 * This method received the request for GBP currencies for particular date of Exchange rate details
	 * from controller and it will call Exchange API and get Exchange Rates details for particular date 
	 * and save details into H2 DB and sending response back to user.
	 */
	public String getExchangeRatesByDate(String accessKey, String date) throws JsonProcessingException {
		logger.info("ExchangeRateService : getExchangeRatesByDate");
		boolean result = ExchangeRatesUtility.isDateValid(date, ExchangeRatesConstants.DATE_FORMATE);
		try {
		if (result) {
			String url = ExchangeRatesConstants.BASE_URL + date + ExchangeRatesConstants.ACCESS_KEY
					+ accessKey + ExchangeRatesConstants.BASE
					+ ExchangeRatesConstants.SYMBOL;
			RestTemplate rt = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			String json = rt.getForObject(url, String.class);
			ExchangeRate exchangeRate = mapper.readValue(json, ExchangeRate.class);
			if (exchangeRate != null && !exchangeRate.getDate().isEmpty()) {
				logger.info("getExchangeRatesByDate : Inserting data into table");
				exchangeRatesApiRepository.save(exchangeRate);
				return ExchangeRatesConstants.SAVE_MSG;
			}
			else {
				return ExchangeRatesConstants.N0_DATA_FOUND;
			}
		}
		}catch(JsonProcessingException jpe) {
			throw new CustomException(ExchangeRatesConstants.UNKNOW_RESPONSE);
		}catch(HttpClientErrorException hce) {
			throw new UnauthorizedException(ExchangeRatesConstants.INVALID_ACCESS_KEY);
		}catch(ResourceAccessException rae) {
			throw new CustomException(ExchangeRatesConstants.SERVER_ERROR);
		}catch(Exception e) {
			throw new CustomException(ExchangeRatesConstants.BASE_ERROR);
		}
		return ExchangeRatesConstants.INVALID_INPUT;
	}
	
	/**
	 * This method received the request for All currencies Exchange rate details from
	 * Controller and it will call Exchange API and pull the Rate details and save 
	 * details into H2 DB and sending response back to user.
	 */
	public String getExchangeRatesData(String accessKey) throws JsonProcessingException {
		logger.info("ExchangeRateService : getExchangeRatesData");
		List<ExchangeRate> exchangeRateData = ExchangeRatesUtility.getExchangeRatesDataFromAPI(accessKey);
		if(!exchangeRateData.isEmpty()) {
			logger.info("getExchangeRates : Inserting data into table");
			exchangeRatesApiRepository.saveAll(exchangeRateData);
			return ExchangeRatesConstants.SAVE_MSG;
		}
		return ExchangeRatesConstants.N0_DATA_FOUND;
	}
	
	/**
	 *This method received the request for All currencies Exchange rate details for particular date
	 * from controller and it will call H2 DB and pull the details from DB and send details
	 * back to Controller to Display details into user console
	 */
	public Object getExchangeRatesInfoByDate(String date) {
		logger.info("ExchangeRateService : getExchangeRatesInfoByDate");
		Object resultObject = null;
		boolean result = ExchangeRatesUtility.isDateValid(date, ExchangeRatesConstants.DATE_FORMATE);
		if (result) {
			ExchangeRate exchangeRate = exchangeRatesApiRepository.findByDate(date);
			if (exchangeRate !=null && !exchangeRate.getDate().isEmpty()) {
				resultObject = exchangeRate;
			} else {
				resultObject = ExchangeRatesConstants.N0_DATA_FOUND_DATE;
			}
		} else {
			resultObject = ExchangeRatesConstants.INVALID_INPUT;
		}
		return resultObject;
	}

	/**
	 * This method received the request for All currencies Exchange rate details in between two
	 * dates from controller and it will call H2 DB and pull the details from DB and send details
	 * back to Controller to Display details into user console
	 */
	public List<Object> getExchangeRatesInBwtDates(String fromDate, String toDate) {
		logger.info("ExchangeRateService : getExchangeRatesInfoByDate");
		boolean result = ExchangeRatesUtility.isDateValid(fromDate, ExchangeRatesConstants.DATE_FORMATE);
		boolean result1 = ExchangeRatesUtility.isDateValid(toDate, ExchangeRatesConstants.DATE_FORMATE);
		List<Object> exchangeRatesList = new ArrayList<>();
		if(result&&result1){
			exchangeRatesList = exchangeRatesApiRepository.findAllByDateBetween(fromDate, toDate);
			String todayDate = ExchangeRatesUtility.getTodayDate();
			ExchangeRate exchangeRate= exchangeRatesApiRepository.findByDate(todayDate);
			if(!exchangeRatesList.isEmpty()) {
				if(exchangeRate!= null && !exchangeRate.getDate().isEmpty()) {
					exchangeRatesList.add(exchangeRate);
				}
				else {
					exchangeRatesList.add(ExchangeRatesConstants.N0_DATA_FOUND_DATE_TODAY);
				}
			}else {
				if(exchangeRate!= null && !exchangeRate.getDate().isEmpty()) {
					exchangeRatesList.add(exchangeRate);
					exchangeRatesList.add(ExchangeRatesConstants.N0_DATA_FOUND_BTW_DATES);
				}
				else {
					exchangeRatesList.add(ExchangeRatesConstants.N0_DATA_FOUND_BTW_DATES_TODAY_DATE);
				}
			}
			
		}else {
			exchangeRatesList.add(ExchangeRatesConstants.INVALID_INPUT);
		}
		return exchangeRatesList;
	}

}
