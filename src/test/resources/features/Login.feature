Feature: Login Functionality

  As a Flipkart user
  I want to log in using my mobile number or email
  So that I can access my account

  Background:
    Given the user is on the Flipkart home page
    And the user clicks on the Login button

  @login @smoke
  Scenario: Login with valid mobile number format triggers OTP request
    When the user enters mobile number "9876543210"
    And clicks Request OTP
    Then an OTP input screen or confirmation should appear

  @login @negative
  Scenario: Login with invalid mobile number shows validation error
    When the user enters mobile number "12345"
    And clicks Request OTP
    Then an error message should be displayed

  @login @negative
  Scenario Outline: Login with various invalid inputs
    When the user enters mobile number "<input>"
    And clicks Request OTP
    Then an error message should be displayed

    Examples:
      | input        |
      | abcdefghij   |
      | 999999999999 |
      | !@#$%^&amp;*()   |
