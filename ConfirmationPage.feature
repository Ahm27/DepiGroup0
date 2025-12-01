Feature: Order Confirmation

  Scenario: Verify order success message
    Given the user has completed the checkout process
    Then the user should see the message "THANK YOU FOR YOUR ORDER"
