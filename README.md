# Ecommerce Website Automation
This is a project to automate Ecommerce Website using Selenium Webdriver with Java and TestNG.

## Prerequisites
Before you begin, ensure you have met the following requirements:

* Java Development Kit (JDK) 21.0.2  installed on your machine. You can download it from [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
* Maven installed on your machine. You can download it from here [here](https://maven.apache.org/download.cgi).
* An Integrated Development Environment (IDE) such as Eclipse.
* TestNG plugin installed in your IDE.
* Installed browser (chrome, firefox, or edge).

## Setup
1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/your-username/selenium-java-testng-automation.git

2. Open the project in your preferred IDE.
3. Resolve Maven dependencies by running:
   
   ```bash
   mvn clean install

## Usage
To run the test, execute the following command:

   ```bash
   mvn test -P <profile_id> -D broswer=<browser_name>

   e.g : mvn test -P Smoke -D browser=chrome
   ```

This will trigger the test with the specified test profile id and specified browser.

browser_name available : chrome, firefox, edge.

## Disclaimer
**Important:** This project is automate a real ecommerce production website. For the checkout order product test, the automation will stop at the choose payment method page and will not continue to choose the payment method. So no order will be created.

## Test Documentation
All the test case and the summary of the test in this project is documented in [here](https://1drv.ms/x/s!AoHTMgcfutbmgtp2l1hj23j6Ern6PQ?e=bMefea)
