Feature: Data-Driven Product Search

  As a QA engineer
  I want to run the same search test against multiple data sets from an external file
  So that test data is decoupled from test logic and can be updated without code changes

  @datadriven @regression
  Scenario: Search using data-driven inputs from Excel
    Given test data is loaded from "src/test/resources/testdata/SearchTestData.xlsx" sheet "SearchData"
    When the user is on the Flipkart home page
    And the user searches for each product from the test data
    Then each search should return at least the expected minimum number of results