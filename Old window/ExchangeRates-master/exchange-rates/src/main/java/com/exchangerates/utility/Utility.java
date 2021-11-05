package com.exchangerates.utility;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exchangerates.constants.Constants;
/**
 * 
 * This class provides the utility methods to validate the date
 *
 */
public class Utility {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);
	
	
	/**
	 * This method validates the date if it is in 12 months range
	 * @param userDate
	 * @return status
	 */
	public static String validateDate(String userDate){
		
		LocalDate userLocalDate;
		
		String status = "";
		
		LocalDate currentDate = LocalDate.now();
		
    	LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
    	
    	Optional<LocalDate> validate = Optional.ofNullable(Utility.validateDateFormat(userDate));
		
    	if(validate.isPresent()){
    		userLocalDate =   validate.get();
    	}

    	else {
    		return Constants.INVALID_DATE_FORMAT;
    	}
	    
    	LocalDate minusDate = firstDayOfMonth.minusMonths(12);
    	
    	boolean isAfterCurrentDate = userLocalDate.isAfter(currentDate);
    	
    	if(userLocalDate.isBefore(minusDate)  ) {
			status = Constants.OUT_OF_RANGE;
		}else if(isAfterCurrentDate){
			status = Constants.DATE_NOT_ALLOWED;
		}else {
			status = Constants.SUCCESS;
		}
		
    	return status;
	}
	
	
	/**
	 * This method validates the format of date, the format of the date should be "yyyy-MM-dd"
	 * @param date
	 * @return localDate
	 */
	public static LocalDate validateDateFormat(String date){
		
		String DATE_FORMAT = "yyyy-MM-dd";
		
		LocalDate localDate = null;
		
		  try{
			  	localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
		    	
		    }
		    catch(DateTimeException dt){
		    	 
		    	LOGGER.debug("DateTimeException");
		    }
		 return localDate;
	}

}
