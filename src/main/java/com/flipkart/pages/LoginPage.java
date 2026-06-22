package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By mobileOrEmailInput = By.cssSelector("input.r4vIwl, input._2IX_2-");
    private final By requestOtpBtn = By.xpath("//button[contains(text(),'Request OTP')]");
    private final By errorMessage = By.cssSelector("div.alert, div._3eF7zV");
    private final By closeIcon = By.cssSelector("button._2KpZ6l._2doB4z");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterMobileOrEmail(String value) {
        type(mobileOrEmailInput, value);
    }

    public void clickRequestOtp() {
        click(requestOtpBtn);
    }

    public String getErrorMessage() {
        return waitForVisibility(errorMessage)
        .getText();
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public void closeLoginModal() {
        click(closeIcon);
    }

	
}
