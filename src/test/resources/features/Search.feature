Feature: Product Search on Flipkart

  As a Flipkart user
  I want to search for products
  So that I can find items I want to purchase

  Background:
    Given the user is on the Flipkart home page

  @search @smoke
  Scenario: Search for a valid product returns results
    When the user searches for "watches"
    Then search results should be displayed
    And the results count should be greater than 0

  @search @regression
  Scenario Outline: Search for multiple valid products
    When the user searches for "<product>"
    Then search results should be displayed

    Examples:
      | product    |
      | mobile     |
      | headphones |
      | shoes      |
      | watch      |

  @search @negative
  Scenario: Search for an invalid or nonexistent product
    When the user searches for "zzxxnonexistentproduct123"
    Then no results message should be displayed

  @search @regression
  Scenario: Search results display correct product titles
    When the user searches for "watches"
    Then each result should contain the search keyword in its title or be a relevant suggestion
