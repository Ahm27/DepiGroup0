Feature: Cart Page

  Scenario: View items in cart
    Given the user has added products to the cart
    When the user clicks the cart icon
    Then the user should see all added products in the cart

  Scenario: Proceed to checkout
    Given the user is on the cart page
    When the user clicks the checkout button
    Then the user should be navigated to the checkout page
