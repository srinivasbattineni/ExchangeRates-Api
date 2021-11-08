  
package com.java.exchangeRatesApi.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.java.exchangeRatesApi.constants.ExchangeRatesConstants;
import com.java.exchangeRatesApi.model.ExchangeRate;
import com.java.exchangeRatesApi.service.ExchangeRatesApiService;

@SpringBootTest
class ExchangeRatesApiTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@MockBean
	private ExchangeRatesApiService exchangeRatesApiService;

	@Test
	void getExchangeRatesInfoByDate() throws Exception {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setBase("EUR");
		exchangeRate.setDate("2021-10-01");
		exchangeRate.setHistorical("true");
		exchangeRate.setSuccess("true");
		exchangeRate.setTimestamp("564687465");
		Map<String, Double> rates = new HashMap<>();
		rates.put("GBP", 0.853913);
		System.out.println("Test case start!!");
		exchangeRate.setRates(rates);
		Mockito.when(exchangeRatesApiService.getExchangeRatesInfoByDate(Mockito.anyString())).thenReturn(exchangeRate);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exchange_rates_details_by_date/date")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"success\":\"true\",\"timestamp\":\"564687465\",\"historical\":\"true\",\"base\":\"EUR\",\"date\":\"2021-10-01\",\"rates\":{\"GBP\":0.853913}}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	void getExchangeRatesInfoByDateNoDataFound() throws Exception {
		Mockito.when(exchangeRatesApiService.getExchangeRatesInfoByDate(Mockito.anyString())).thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exchange_rates_details_by_date/date")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().isEmpty());
	}

	@Test
	void getExchangeRatesInfoByInvalidDate() throws Exception {
		Mockito.when(exchangeRatesApiService.getExchangeRatesInfoByDate(null)).thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exchange_rates_details_by_date/date")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().isEmpty());
	}

	@Test
	void getExchangeRatesInfoByInvalidDateWithData() throws Exception {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setBase("EUR");
		exchangeRate.setDate("2021-10-01");
		exchangeRate.setHistorical("true");
		exchangeRate.setSuccess("true");
		exchangeRate.setTimestamp("564687465");
		Map<String, Double> rates = new HashMap<>();
		rates.put("GBP", 0.853913);
		System.out.println("Test case start!!");
		exchangeRate.setRates(rates);
		Mockito.when(exchangeRatesApiService.getExchangeRatesInfoByDate(null)).thenReturn(exchangeRate);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exchange_rates_details_by_date/date")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().isEmpty());
	}

	@Test
	void getExchangeRatesInBwtDates() throws Exception {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setBase("EUR");
		exchangeRate.setDate("2021-10-01");
		exchangeRate.setHistorical("true");
		exchangeRate.setSuccess("true");
		exchangeRate.setTimestamp("564687465");
		Map<String, Double> rates = new HashMap<>();
		rates.put("GBP", 0.853913);
		System.out.println("Test case start!!");
		exchangeRate.setRates(rates);
		List<Object> exchangeRateList = new ArrayList<>();
		exchangeRateList.add(exchangeRate);
		Mockito.when(exchangeRatesApiService.getExchangeRatesInBwtDates(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(exchangeRateList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exchange_rates_in_bwt_dates/fromdate/todate")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"success\":\"true\",\"timestamp\":\"564687465\",\"historical\":\"true\",\"base\":\"EUR\",\"date\":\"2021-10-01\",\"rates\":{\"GBP\":0.853913}}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	void getExchangeRatesInBwtDatesWithNoData() throws Exception {
		Mockito.when(exchangeRatesApiService.getExchangeRatesInBwtDates(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exchange_rates_in_bwt_dates/fromdate/todate")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().isEmpty());
	}

	@Test
	void getExchangeRates() throws Exception {
		Mockito.when(exchangeRatesApiService.getExchangeRates(Mockito.anyString()))
				.thenReturn(ExchangeRatesConstants.SAVE_MSG);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exchange_rates_data_few_currencies/access_Key")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(ExchangeRatesConstants.SAVE_MSG, result.getResponse().getContentAsString());
	}

	@Test
	void getExchangeRatesWrongAccessKey() throws Exception {
		Mockito.when(exchangeRatesApiService.getExchangeRates(null)).thenReturn(ExchangeRatesConstants.SAVE_MSG);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exchange_rates_data_few_currencies/access_Key")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().isEmpty());
	}

	@Test
	void getExchangeRatesByDate() throws Exception {
		Mockito.when(exchangeRatesApiService.getExchangeRatesByDate(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(ExchangeRatesConstants.SAVE_MSG);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exchange_rates_by_date/access_key/date")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(ExchangeRatesConstants.SAVE_MSG, result.getResponse().getContentAsString());
	}

	@Test
	void getExchangeRatesByDateWrongAccessKey() throws Exception {
		Mockito.when(exchangeRatesApiService.getExchangeRatesByDate(null, null))
				.thenReturn(ExchangeRatesConstants.SAVE_MSG);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exchange_rates_by_date/access_key/date")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().isEmpty());
	}
}
