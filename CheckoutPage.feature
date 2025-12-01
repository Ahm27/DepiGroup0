Feature: Checkout Process

  Scenario: Enter checkout information and continue
    Given the user is on the checkout page
    When the user enters first name "Shahd", last name "Ashry", and postal code "12345"
    And clicks continue
    Then the user should be navigated to the overview page

  Scenario: Complete purchase
    Given the user is on the overview page
    When the user clicks finish
    Then the user should see the order confirmation page
