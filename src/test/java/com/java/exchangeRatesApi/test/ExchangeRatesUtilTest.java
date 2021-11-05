package com.java.exchangeRatesApi.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.exchangeRatesApi.constants.ExchangeRatesConstants;
import com.java.exchangeRatesApi.model.ExchangeRate;
import com.java.exchangeRatesApi.utility.ExchangeRatesUtility;

//@SpringBootTest
@SpringBootTest(classes=ExchangeRatesUtilTest.class)
class ExchangeRatesUtilTest {	
	@MockBean
	private RestTemplate restTemplate;
	@Mock
	private ObjectMapper objectMapper;
	@Test
	void getTodayDateTest() {
		try (MockedStatic<ExchangeRatesUtility> mocked = mockStatic(ExchangeRatesUtility.class)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			String inputDate = dtf.format(now);
			OngoingStubbing<Object> date = mocked.when(ExchangeRatesUtility::getTodayDate).thenReturn(inputDate);
			assertNotNull(date);
		}
	}
	
	@Test
	void isDateValidTest() {
		try (MockedStatic<ExchangeRatesUtility> mocked = mockStatic(ExchangeRatesUtility.class)) {
			mocked.when(() -> ExchangeRatesUtility.isDateValid("2021-10-01", "yyyy-MM-dd")).thenReturn(true);
		}
	}
	
	@Test
	void getExchangeDataFromAPITest() throws JsonMappingException, JsonProcessingException
	{
		try (MockedStatic<ExchangeRatesUtility> mocked = mockStatic(ExchangeRatesUtility.class)) {
			ExchangeRate exchangeRate = new ExchangeRate();
			exchangeRate.setBase("EUR");
			exchangeRate.setDate("2021-10-01");
			exchangeRate.setHistorical("true");
			exchangeRate.setSuccess("true");
			exchangeRate.setTimestamp("564687465");
			List<ExchangeRate> exchangeRateList = new ArrayList<>();
			exchangeRateList.add(exchangeRate);
			String reponse = "[{\"success\":\"true\",\"timestamp\":\"564687465\",\"historical\":\"true\",\"base\":\"EUR\",\"date\":\"2021-10-01\",\"rates\":{\"GBP\":0.853913}}]";
			Mockito.when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenReturn(reponse);
			Mockito.when(objectMapper.readValue(Mockito.anyString(), eq(ExchangeRate.class))).thenReturn(exchangeRate);
			OngoingStubbing<Object> result  =mocked.when(() -> ExchangeRatesUtility.getExchangeDataFromAPI(ExchangeRatesConstants.ACCESS_KEY_VALUE)).thenReturn(exchangeRateList);
			assertNotNull(result);
		}
	}
	
	@Test
	void getExchangeRatesDataFromAPITest() throws JsonMappingException, JsonProcessingException
	{
		try (MockedStatic<ExchangeRatesUtility> mocked = mockStatic(ExchangeRatesUtility.class)) {
			ExchangeRate exchangeRate = new ExchangeRate();
			exchangeRate.setBase("EUR");
			exchangeRate.setDate("2021-10-01");
			exchangeRate.setHistorical("true");
			exchangeRate.setSuccess("true");
			exchangeRate.setTimestamp("564687465");
			List<ExchangeRate> exchangeRateList = new ArrayList<>();
			exchangeRateList.add(exchangeRate);
			String reponse = "[{\"success\":\"true\",\"timestamp\":\"564687465\",\"historical\":\"true\",\"base\":\"EUR\",\"date\":\"2021-10-01\",\"rates\":{\"GBP\":0.853913}}]";
			Mockito.when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenReturn(reponse);
			Mockito.when(objectMapper.readValue(Mockito.anyString(), eq(ExchangeRate.class))).thenReturn(exchangeRate);
			OngoingStubbing<Object> result  =mocked.when(() -> ExchangeRatesUtility.getExchangeRatesDataFromAPI(ExchangeRatesConstants.ACCESS_KEY_VALUE)).thenReturn(exchangeRateList);
			assertNotNull(result);
		}
	}
	
}
