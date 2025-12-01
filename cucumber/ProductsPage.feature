Feature: Products Page

  Scenario: Add a product to cart
    Given the user is logged in and on the products page
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart icon should show 1 item

  Scenario: Add multiple products to cart
    Given the user is logged in and on the products page
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    Then the cart icon should show 2 items
