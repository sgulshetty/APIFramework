@RegressionSuite

Feature: Foreign Exchange Rate API

@PositiveScenario
Scenario: Check the latest foreign exchange rates

Given The API URI to get the foreign rates
When User submit the GET request
Then Response code received should be "200"

@PositiveScenario
Scenario: Validate the foreign exchange rate for past date
Given the API to get the foreign exchange rates for past date "2010-01-12"
When User submit the GET request
Then Response code received should be "200"

@PositiveScenario
Scenario Outline: Validate latest foreign exchange rates with base and symbol
Given The API URI with "<Base>" and "<Symbol>" to get the foreign rate
When User submit the GET request
Then Response code received should be "200"
Examples: 
|Base|Symbol|
|USD|EUR|	
|INR|INR|

@PositiveScenario
Scenario Outline: Validate the foreign exchange rate with base and symbol for past date
Given the API to get the foreign exchange rates with "<Base>" and "<Symbol>" and "<PastDate>"
When User submit the GET request
Then Response code received should be "200"
And latest foreign exchange rates should be received as response 
Examples: 
|Base|Symbol|PastDate|
|USD|EUR|2010-01-12|
|INR|USD|2009-01-03|

@NegetScenario
Scenario: Validate Api response for the invalid day
Given the API to get the foreign exchange rates for past date "2020-02-31"
When User submit the GET request
Then Validate the error in the response body "day is out of range for month"
And Response code received should be "400"

@NegetiveScenario
Scenario: Validate Api response for the invalid month
Given the API to get the foreign exchange rates for past date "2020-13-31"
When User submit the GET request
Then Validate the error in the response body "time data '2020-13-31' does not match format '%Y-%m-%d'"
And Response code received should be "400"

@NegetiveScenario
Scenario: Validate response with invalid Base
Given the API URI with invalid Base "XYZ"
When User submit the GET request 
Then Validate the error in the response body "Base 'XYZ' is not supported."
And Response code received should be "400"

@NegetiveScenario
Scenario: Validate response with invalid Symbol
Given the API URI with invalid Symbol "XYZ"
When User submit the GET request 
Then Validate the error in the response body "Symbols 'XYZ' are invalid for date " "yyyy-MM-dd."
And Response code received should be "400"

@EdgeScenario
Scenario: Validate Api response for the date below the data present
Given the API to get the foreign exchange rates for past date "1990-01-12"
When User submit the GET request
Then Validate the error in the response body "There is no data for dates older then 1999-01-04."
And Response code received should be "400" 
