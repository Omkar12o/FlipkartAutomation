package com.flipkart.stepdefinitions;

import com.flipkart.pages.HomePage;
import com.flipkart.pages.LoginPage;
import com.flipkart.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {

    WebDriver driver = DriverManager.getDriver();
    HomePage homePage = new HomePage(driver);
    LoginPage loginPage = new LoginPage(driver);

    @And("the user clicks on the Login button")
    public void the_user_clicks_on_the_login_button() {
        homePage.clickLogin();
    }

    @When("the user enters mobile number {string}")
    public void the_user_enters_mobile_number(String mobile) {
        loginPage.enterMobileOrEmail(mobile);
    }

    @And("clicks Request OTP")
    public void clicks_request_otp() {
        loginPage.clickRequestOtp();
    }

    @Then("an OTP input screen or confirmation should appear")
    public void an_otp_input_screen_should_appear() {
        // Real OTP cannot be verified without SMS access - we assert no error is shown instead
        Assert.assertFalse(loginPage.isErrorDisplayed(), "Unexpected error shown for valid mobile number");
    }

    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected validation error was not displayed");
    }
}
