Feature: Sorting and Filtering Search Results

  As a Flipkart user
  I want to sort and filter search results
  So that I can find products matching my preferences quickly

  Background:
    Given the user is on the Flipkart home page
    And the user searches for "laptop"

  @sort @regression
  Scenario: Sort products by price low to high
    When the user sorts by "Price -- Low to High"
    Then the displayed prices should be in ascending order

  @sort @regression
  Scenario: Sort products by price high to low
    When the user sorts by "Price -- High to Low"
    Then the displayed prices should be in descending order

  @filter @regression
  Scenario: Filter products by customer rating
    When the user applies the "4 star and above" rating filter
    Then all displayed products should have rating 4 stars or above
