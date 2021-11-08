package com.java.exchangeRatesApi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.exchangeRatesApi.service.ExchangeRatesApiService;

/**
 * @author Srinivas Battineni
 * ExchangeRateApiController is the controller class this application
 * 
 * This is the entry point for all the requests and response will be send back.
 */
@RestController
public class ExchangeRateApiController {
	Logger logger = LoggerFactory.getLogger(ExchangeRateApiController.class);

	@Autowired
	ExchangeRatesApiService exchangeRatesApiService;
	
	/**
	 * This method will call the external Api of ExchangeRatesApi and request for GBP/USD/HKD 
	 * currencies Exchange rate details and it will call service layer and pull rates details from Exchange API and save 
	 * details into H2 DB and sending response back to user.
	 * 
	 * @param accessKey
	 * @return Success message as a response
	 * @throws JsonProcessingException
	 */
	@GetMapping(value="/exchange_rates_data_few_currencies/{access_Key}")
	private String getExchangeRates(@PathVariable("access_Key") String accessKey) throws JsonProcessingException {
		logger.info("ExchangeRateController : getExchangeRates Entry");
		return exchangeRatesApiService.getExchangeRates(accessKey);
	}
	
	/**
	 * This method will request for GBP currencies for particular date Exchange rate details
	 * and it will call service layer and pull rates details for that date from Exchange API and save 
	 * details into H2 DB and sending response back to user.
	 * 
	 * @param accessKey
	 * @param date
	 * @return Success message response
	 * @throws JsonProcessingException
	 */
	@GetMapping(value="/exchange_rates_by_date/{accessKey}/{date}")
	private String getExchangeRatesByDate(@PathVariable("accessKey") String accessKey,@PathVariable("date") String date) throws JsonProcessingException {
		logger.info("ExchangeRateController : getExchangeRatesByDate Entry ");
		return exchangeRatesApiService.getExchangeRatesByDate(accessKey,date);
	}
	
	/**
	 * This method will request for All currencies Exchange rate details
	 * and it will call service layer and pull rates details Exchange API and save 
	 * details into H2 DB and sending response back to user.
	 * 
	 * @param accessKey
	 * @return Success message response
	 * @throws JsonProcessingException
	 */
	@GetMapping(value="/exchange_rates_data_all_currencies/{accessKey}")
	private String getExchangeRatesData(@PathVariable("accessKey") String accessKey) throws JsonProcessingException {
		logger.info("ExchangeRateController : getExchangeRatesData Entry");
		return exchangeRatesApiService.getExchangeRatesData(accessKey);
	}
	
	/**
	 * This method will request for All currencies Exchange rate details for particular date
	 * and it will call service layer and pull rates details from H2 DB and Display 
	 * details into user console
	 * 
	 * @param date
	 * @return Exchange Rates 
	 */
	@GetMapping(value="/exchange_rates_details_by_date/{date}")
	private Object getExchangeRatesInfoByDate(@PathVariable("date") String date) {
		logger.info("ExchangeRateController : getExchangeRatesInfoByDate Entry ");
		return exchangeRatesApiService.getExchangeRatesInfoByDate(date);//2021-10-08
	}
	
	/**
	 * This method will request for All currencies Exchange rate details in between two
	 * dates and it will call service layer and pull rates details from H2 DB and Display 
	 * details into user console
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return List of Exchange Rates
	 */
	@GetMapping(value="/exchange_rates_in_bwt_dates/{fromDate}/{toDate}")
	private List<Object> getExchangeRatesInBwtDates(@PathVariable("fromDate") String fromDate,@PathVariable("toDate") String toDate) {
		logger.info("ExchangeRateController : getExchangeRatesInBwtDates Entry");
		return exchangeRatesApiService.getExchangeRatesInBwtDates(fromDate,toDate);//2021-10-08
	}
	
}
