# Exchange Rates Application

Exchange rates application basically loads the rates for currencies from external exchange rates API and stores into the database 
and provides the rate information based on clients requirement.

## About the Service  
The service is a REST service. It uses an in-memory database (H2) to store the data.  
It contents below  endpoints:

### 1. Endpoint: / saveRatesByDateAndCurrencies  
The client requests to load the data in database based on the date and list of currencies.

Consumes: Date (Which is in 12 months range) and List of currencies  
Produces: Loads rates for given date and currencies in database and produces JSON.

Scenario 1:  Given date in range and list of currencies  
Request: http://localhost:8810/saveRatesByDateAndCurrencies?date=2020-11-11&currencyList=XAU,XAG  
Response:  
{  
    "timestamp”: 1635722699020, 
    "status”: "SUCCESS",  
    "message”: "Rates Loaded Successfully",  
    "statusCode”:200  
}  

Scenario 2:  Given the date out of range  
Request: http://localhost:8810/saveRatesByDateAndCurrencies?date=2021-11-11&currencyList=XAU,XAG  
Response:  
{  
    "timestamp”: 1635722655597,  
    "status”: "FAILURE",  
    "message”: "DATE OUT OF RANGE",  
    "statusCode”:400  
}  

### 2. Endpoint: /saveRate  
The client requests to load the data in database for the provided Date, currency and base.  
Consumes: Date, Currency, Base  
Produces: Loads rate for given date, currency and base in database and produces Json.  

Scenario 1: Given date, currency and base  
Request: http://localhost:8810/saveRate?date=2019-03-04&currency=GBP&base=EUR  
Response:   
{  
    "timestamp": 1635727869008,  
    "status": "SUCCESS",  
    "message": "Rates Loaded Successfully",  
    "statusCode": 200  
  }


Scenario 2: Given Invalid date format, currency, base  
Request: http://localhost:8810/saveRate?date=2019/03/04&currency=GBP&base=EUR  
 Response:  
{  
    "timestamp": 1635727959672,  
    "status": "FAILURE",  
    "message": "INVALID DATE FORMAT : Expecting yyyy-MM-dd",  
    "statusCode": 400  
}  

### 3. Endpoint: /saveRatesForYear/{currency}
The client requests to load the data in database for the provided currency for whole year.  

Consumes: Currency  
Produces: Loads rates for 12 months for given currency (12 rows / 1 record for a month) and produces JSON  
Scenario 1: Given valid currency  
Request: http://localhost:8810/saveRatesForYear/GBP  
Response:  
{  
    "timestamp": 1635728216819,  
    "status": "SUCCESS",  
    "message": "Rate Loaded Successfully for GBP",  
    "statusCode": 200  
}

Scenario 2. Given invalid currency  
Request: http://localhost:8810/saveRatesForYear/XAR  
Response:  
{  
    "timestamp": 1635728338996,  
    "status": "FAILURE",  
    "message": "Rates not available",  
    "statusCode": 404  
}  



### 4. Endpoint: /fetchRateByDateAndCurrency  
Client requests the rate for given date and currency.  

Consumes: Date, Currency  
Produces: JSON containing rate for given date and currency  

Scenario 1: Given date and currency for which rate is present in database  
Request: http://localhost:8810/fetchRateByDateAndCurrency?date=2020-12-01&currency=XAU  
Response:  
{  
    "timestamp": 1635741177339,  
    "status": "SUCCESS",  
    "message": "Rate is available",  
    "statusCode": 200,  
    "data": 6.32E-4  
}  

### 5. Endpoint: /fetchRateByDate 
Client requests the data from given date to today  

Consumes: Date  
Produces: JSON containing data from given date to today  

Scenario 1: Given valid date   
Request: http://localhost:8810/fetchRateByDate?date=2020-11-11  
Response: 
{  
    "timestamp": 1635741761323,  
    "status": "SUCCESS",  
    "message": "Rates available ",  
    "statusCode": 200,  
    "data": [
        {
            "id": 1,
            "currency": "XAU",
            "rate": 6.32E-4,
            "docDate": "2020-11-11",
            "base": "EUR"
        }
    ]
}

Scenario 2: Given Invalid date format  
Request: http://localhost:8810/fetchRateByDate/?date=2020/11/11  
Response:  
{  
    "timestamp": 1635741840110,  
    "status": "FAILURE",  
    "message": "INVALID DATE FORMAT : Expecting yyyy-MM-dd",  
    "statusCode": 400  
}  
















