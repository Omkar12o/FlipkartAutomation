Feature: Shopping Cart Operations

  As a Flipkart user
  I want to manage products in my cart
  So that I can purchase the items I select

  Background:
    Given the user is on the Flipkart home page

  @cart @smoke
  Scenario: Add a product to cart from product page
    When the user searches for "watches"
    And clicks on the first product
    And adds the product to cart
    Then the cart should contain at least 1 item

  @cart @regression
  Scenario: Remove a product from the cart
    When the user searches for "watches"
    And clicks on the first product
    And adds the product to cart
    And the user removes the first item from cart
    Then the cart should be empty

  @cart @regression
  Scenario: Verify cart item title matches the added product
    When the user searches for "watches"
    And clicks on the first product
    And adds the product to cart
    Then the cart item title should match the product added
