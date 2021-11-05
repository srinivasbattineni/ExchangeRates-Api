package com.java.exchangeRatesApi.model;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Srinivas Battineni
 *
 */
@Entity
@Table(name = "EXCHANGE_RATES", schema = "PUBLIC")
public class ExchangeRate {
	@Column
	private String success;
	@Column
	private String timestamp;
	@Column
	private String historical;
	@Column
	private String base;
	@Id
	@Column
	private String date;
	@ElementCollection
	@MapKeyColumn
	@Column
	@CollectionTable
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private Map<String,Double> rates;
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getHistorical() {
		return historical;
	}
	public void setHistorical(String historical) {
		this.historical = historical;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Map<String, Double> getRates() {
		return rates;
	}
	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
	@Override
	public String toString() {
		return "ExchangeRate [success=" + success + ", timestamp=" + timestamp + ", base=" + base + ", date=" + date
				+ ", rates=" + rates + "]";
	}
	
}
