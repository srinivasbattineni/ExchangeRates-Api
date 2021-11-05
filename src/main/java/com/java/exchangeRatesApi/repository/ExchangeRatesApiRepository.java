package com.java.exchangeRatesApi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.exchangeRatesApi.model.ExchangeRate;

/**
 * From this call will call DB and do DB operations 
 * @author Srinivas Battineni
 *
 */
@Repository
public interface ExchangeRatesApiRepository extends JpaRepository<ExchangeRate, Integer>{
	 /**
	 * @param date
	 * @return
	 */
	ExchangeRate findByDate(@Param("date") String date);
	 
	 /**
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	List<Object> findAllByDateBetween(String fromDate,String toDate);
}
