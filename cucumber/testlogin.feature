Feature: Login Functionality

  Scenario: Successful login
    Given the user is on the SauceDemo login page [cite: 2]
    When the user enters username "standard_user" and password "secret_sauce" [cite: 2]
    And clicks the login button [cite: 2]
    Then the user should see the products page [cite: 2]

  Scenario: Login with invalid credentials
    Given the user is on the SauceDemo login page [cite: 2]
    When the user enters username "wrong_user" and password "wrong_pass" [cite: 2]
    And clicks the login button [cite: 2]
    Then the user should see an error message "Username and password do not match any user in this service" [cite: 3]