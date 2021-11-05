package com.java.exchangeRatesApi.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.java.exchangeRatesApi.model.ExchangeRate;
import com.java.exchangeRatesApi.repository.ExchangeRatesApiRepository;

@DataJpaTest
class ExchangeRatesRepositoryTest {
	@Autowired
    private ExchangeRatesApiRepository exchangeRatesRepository;
	
	 @Autowired
	 private TestEntityManager entityManager;
	
	@Test
	void saveExchangeRates() {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setBase("EUR");
		exchangeRate.setDate("2021-10-01");
		exchangeRate.setHistorical("true");
		exchangeRate.setSuccess("true");
		exchangeRate.setTimestamp("564687465");
		Map<String, Double> rates = new HashMap<>();
		rates.put("GBP", 0.853913);
		exchangeRate.setRates(rates);
		ExchangeRate exchangeRate1 = exchangeRatesRepository.save(exchangeRate);
		assertEquals(exchangeRate.getDate(), exchangeRate1.getDate());
	}
	
	@Test
	void exchangeRatesSaveAll() {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setBase("EUR");
		exchangeRate.setDate("2021-10-01");
		exchangeRate.setHistorical("true");
		exchangeRate.setSuccess("true");
		exchangeRate.setTimestamp("564687465");
		Map<String, Double> rates = new HashMap<>();
		rates.put("GBP", 0.853913);
		exchangeRate.setRates(rates);
		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		exchangeRateList.add(exchangeRate);
		List<ExchangeRate> exchangeRateList1 = exchangeRatesRepository.saveAll(exchangeRateList);
		assertTrue(!exchangeRateList1.isEmpty());
	}
	
	@Test
	void getExchangeRatesBydate() {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setBase("EUR");
		exchangeRate.setDate("2021-10-01");
		exchangeRate.setHistorical("true");
		exchangeRate.setSuccess("true");
		exchangeRate.setTimestamp("564687465");
		Map<String, Double> rates = new HashMap<>();
		rates.put("GBP", 0.853913);
		exchangeRate.setRates(rates);
		entityManager.persist(exchangeRate);
		ExchangeRate exchangeRate1 = exchangeRatesRepository.findByDate("2021-10-01");
		assertEquals(exchangeRate.getDate(), exchangeRate1.getDate());
	}
	
	@Test
	void getExchangeRatesBydates() {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setBase("EUR");
		exchangeRate.setDate("2021-10-01");
		exchangeRate.setHistorical("true");
		exchangeRate.setSuccess("true");
		exchangeRate.setTimestamp("564687465");
		Map<String, Double> rates = new HashMap<>();
		rates.put("GBP", 0.853913);
		exchangeRate.setRates(rates);
		entityManager.persist(exchangeRate);
		List<Object> exchangeRateList1 = exchangeRatesRepository.findAllByDateBetween("2021-01-01", "2021-10-01");
		assertTrue(!exchangeRateList1.isEmpty());
	}
}
