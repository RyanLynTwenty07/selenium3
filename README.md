# Introduce Framework selenium-java
1. Structure
  - Build framework based on POM
  - Page: Page Object: where to manage the element of page, like and interface(label, textbox, button...), actions(login, selectDateTime, composeEmail)
   -> example: LoginPage, BookingPage, CreditPage...
  - Data: Data object 
  - Test: Test scripts
  - Ultilities: Common, supports reader files and more...
   Suite: config test run
3. Dependencies
   Include in POM
5. Test Run
   - Using TestNG
   - Framework support user is able to run with CHROME, EDGE, FIREFOX, SAFARI
   
7. Paralell
9. Distributed testing (Selenium Grid)
10. CI/CD
   - Migrate and associate with ADO 
11. Report
    Using Allure Report
    User should be installed allure dependencies to generate report

# Set up Environment
1. Install JDK
2. Install InteliJi

# Configure to run scripts
1. To run script for EX 1: VietJetAir
2. To run script for EX 2: AGODA
3. To run script for EX 3: LogiGear Mail
Please set environment variable:
username ="", 
password =""

# Associate with AzureDevops
   - Migrate and associate test run with ADO
   - How to set up CI Tool run test by schedule
   - Send the email report to configured email addresses
