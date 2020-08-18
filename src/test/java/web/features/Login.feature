Feature: Login into application

Scenario Outline: Positive login test validation
Given Initiazile the browser with chrome
And Navigate to "http://qaclickacademy.com" site
And Click on login button in homepage to land on sign page
When User enters Username <username> and password <password> and logs in
Then Varify that a user has succesfully logged in
And close browers

Examples:
|username					|password		|
|leratomalatjie@ymail.com	|jump.123		|
|leratom@commandquality.com	|32134			|

Scenario: Landing page Title validation
Given Initiazile the browser with chrome
And Navigate to "http://qaclickacademy.com" site
When User gets on a landing page Title "FEATURED COURSES" should be displayed
Then Varify that a user has succesfully logged in
And close browers