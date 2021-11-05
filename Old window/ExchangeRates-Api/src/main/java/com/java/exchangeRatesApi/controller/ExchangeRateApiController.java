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
 * ExchangeRateController is the controller for Exchange Rate application
 * If we got any request to application this class is the entry point for all the request
 * Based on the request will send response back.
 */
@RestController
public class ExchangeRateApiController {
	Logger logger = LoggerFactory.getLogger(ExchangeRateApiController.class);

	@Autowired
	ExchangeRatesApiService exchangeRatesApiService;
	
	/**
	 * This method received the request for GBP/USD/HKD currencies Exchange rate details
	 * and it will call service layer and pull rates details from Exchange API and save 
	 * details into H2 DB and sending response back to user.
	 * 
	 * @param accessKey
	 * @return Success message as a response
	 * @throws JsonProcessingException
	 */
	@GetMapping(value="/exchange_Rates_Data_Few_Currencies/{access_Key}")
	private String getExchangeRates(@PathVariable("access_Key") String accessKey) throws JsonProcessingException {
		logger.info("ExchangeRateController : getExchangeRates Entry");
		return exchangeRatesApiService.getExchangeRates(accessKey);
	}
	
	/**
	 * This method received the request for GBP currencies for particular date Exchange rate details
	 * and it will call service layer and pull rates details for that date from Exchange API and save 
	 * details into H2 DB and sending response back to user.
	 * 
	 * @param accessKey
	 * @param date
	 * @return Success message response
	 * @throws JsonProcessingException
	 */
	@GetMapping(value="/exchange_Rates_By_Date/{accessKey}/{date}")
	private String getExchangeRatesByDate(@PathVariable("accessKey") String accessKey,@PathVariable("date") String date) throws JsonProcessingException {
		logger.info("ExchangeRateController : getExchangeRatesByDate Entry ");
		return exchangeRatesApiService.getExchangeRatesByDate(accessKey,date);
	}
	
	/**
	 * This method received the request for All currencies Exchange rate details
	 * and it will call service layer and pull rates details Exchange API and save 
	 * details into H2 DB and sending response back to user.
	 * 
	 * @param accessKey
	 * @return Success message response
	 * @throws JsonProcessingException
	 */
	@GetMapping(value="/exchange_Rates_Data_All_Currencies/{accessKey}")
	private String getExchangeRatesData(@PathVariable("accessKey") String accessKey) throws JsonProcessingException {
		logger.info("ExchangeRateController : getExchangeRatesData Entry");
		return exchangeRatesApiService.getExchangeRatesData(accessKey);
	}
	
	/**
	 * This method received the request for All currencies Exchange rate details for particular date
	 * and it will call service layer and pull rates details from H2 DB and Display 
	 * details into user console
	 * 
	 * @param date
	 * @return Exchange Rates 
	 */
	@GetMapping(value="/exchange_Rates_Details_By_Date/{date}")
	private Object getExchangeRatesInfoByDate(@PathVariable("date") String date) {
		logger.info("ExchangeRateController : getExchangeRatesInfoByDate Entry ");
		return exchangeRatesApiService.getExchangeRatesInfoByDate(date);//2021-10-08
	}
	
	/**
	 * This method received the request for All currencies Exchange rate details in between two
	 * dates and it will call service layer and pull rates details from H2 DB and Display 
	 * details into user console
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return List of Exchange Rates
	 */
	@GetMapping(value="/exchange_Rates_In_Bwt_Dates/{fromDate}/{toDate}")
	private List<Object> getExchangeRatesInBwtDates(@PathVariable("fromDate") String fromDate,@PathVariable("toDate") String toDate) {
		logger.info("ExchangeRateController : getExchangeRatesInBwtDates Entry");
		return exchangeRatesApiService.getExchangeRatesInBwtDates(fromDate,toDate);//2021-10-08
	}
	
}
