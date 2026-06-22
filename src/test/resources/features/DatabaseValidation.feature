Feature: Database Validation Layer

  As a QA engineer
  I want to validate backend order data directly at the database level
  So that I can catch data integrity issues that the UI alone might hide

  @database @backend
  Scenario: Verify order status is correctly stored in the database
    Given the orders table is seeded with sample data
    When the user queries the order with id 1
    Then the order status should be "CONFIRMED"
    And the order price should be greater than 0

  @database @backend
  Scenario: Verify no orders exist with a negative price
    Given the orders table is seeded with sample data
    When the user queries all orders
    Then no order should have a negative price