Feature: Login Functionality

  Scenario: Successful login
    Given the user is on the SauceDemo login page
    When the user enters username "standard_user" and password "secret_sauce"
    And clicks the login button
    Then the user should see the products page

  Scenario: Login with invalid credentials
    Given the user is on the SauceDemo login page
    When the user enters username "wrong_user" and password "wrong_pass"
    And clicks the login button
    Then the user should see an error message "Username and password do not match any user in this service"
