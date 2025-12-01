Feature: Products Page

  Scenario: Add a product to cart
    Given the user is logged in and on the products page [cite: 4]
    When the user adds "Sauce Labs Backpack" to the cart [cite: 4]
    Then the cart icon should show 1 item [cite: 4]

  Scenario: Add multiple products to cart
    Given the user is logged in and on the products page [cite: 4]
    When the user adds "Sauce Labs Backpack" to the cart [cite: 4]
    And the user adds "Sauce Labs Bike Light" to the cart [cite: 4]
    Then the cart icon should show 2 items [cite: 5]