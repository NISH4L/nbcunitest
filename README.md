## Demo Challenge

#### Technologies Used
1. Java
2. Selenium
3. TestNG
4. Eclipse

#### Test Cases

##### General Test Scenario
 1. Mandatory field like phone number and name should be validated
 2. Error messages should be displayed on top of particular fields preferably
 3. User shouldn't be able to place an order twice in quick succession
 4. Max field value should be checked for input fields
 5. Make sure the reset button changes set default values
 6. Check for the grammar and spelling errors on the page
 7. Validate HTML markup for the page to follow standard compliant

##### GUI and Usability Test Scenario
 1. Verify pizza type is selected before placing the order
 2. Verify that at least one type of topping is selected
 3. Verify the required quantity amount is entered and it is numeric
 4. Name and Phone are mandatory and must be entered
 5. If email is entereed, verify the email format is correct
 6. One of the radio option must be selected. Page should not allow simultaneous selection of radio buttons

##### Performance Testing Scenario
 1. Make sure the page loads within acceptable range
 2. Check the page loading on slow connection
 3. Check for database query execution time when order is placed

##### Security Testing Scenario
 1. Since it is a payment page, it should use HTTPS protocol
 2. Input field should not allow scripting
 3. If the page crashes unexpectedly, it should not reveal server information
 4. If CreditCard payment is enabled, the input should not have autocomplete enabled and should never be stored in cookies
 5. Application should avoid duplicate ordering when "place order" is clicked twice accidentally

##### Defects Observed
 1. Order goes through even without selecting any Pizza or either of the toppings
 2. Quantity takes any characters as input
 3. Email when entered doesn't have any form of validation
 4. Name field takes any characters as input
 5. Phone field takes any characters as input
 6. Both of the radio button can be selected simultaneously
 

