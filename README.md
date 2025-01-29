# JustLife Automation Code

## Summary
This repository contains the code for automating the booking flow with certain parameters.

## Tools / Technologies Used
- **Selenium**
- **TestNG**
- **Maven**
- **Cucumber**
- **Java**

## Folder Structure
- **src/main/java/com/justlife/pages :** All locators and low level method are in this folder
- **src/main/java/com/justlife/dataloader and JsonParser:** To load and parse Json data
- **src/main/java/com/justlife/testData:** . This folder will contains all the data that we can input let say phone number and otp
- **src/test/java/com/justlife/stepdefinations:** This will have all the step defination corresponding to the feature file
- **src/test/java/com/justlife/runner:** To define run configurations
- **src/test/java/com/justlife/Hooks:** To initialize driver at the start of execution
- **src/test/java/com/justlife/screenshotUtils:** To define methods to capture screenshots during execution
- **src/test/resources/features:** To define all feature files
- **src/test/resources/testdata.properties:** To define generic data
- **screenshots:** This will have all screenshots during the execution

## Pre Requisite to execute the cases
- Java version 11 and above
- Maven version 3.2 and above
- Intellij installed for local execution

## How to run
- Clone repository https://github.com/iqrabibi/justlife_Case_Study
- Reload Maven dependencies 
- Run command mvn clean test

## Reporting 
- Once test cases executed, report will be generated and you can see the link of cucumber server to access the report
- For HTML and json report  , go to target folder and then cucumber-reports.html / cucumber.json


