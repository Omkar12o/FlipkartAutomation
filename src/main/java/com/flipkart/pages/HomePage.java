package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private final By searchBox = By.name("q");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By loginCloseBtn = By.xpath("//button[text()='✕']");
    private final By accountMenu = By.cssSelector("span._1c70Et, span.wW9X8X");
    private final By cartIcon = By.cssSelector("a[href='/viewcart']");
    private final By loginButton = By.linkText("Login");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void waitForPageTitle() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        } catch (org.openqa.selenium.TimeoutException e) {
            throw new RuntimeException("Search box never appeared. Actual page title was: '"
                    + driver.getTitle() + "'. This usually means Flipkart showed a CAPTCHA/bot-check page "
                    + "instead of the homepage, or the page is loading very slowly on this network.", e);
        }
    }

    public void closeLoginPopupIfPresent() {
        try {
            java.util.List<org.openqa.selenium.WebElement> closeButtons =
                    new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                            .until(d -> {
                                java.util.List<org.openqa.selenium.WebElement> found = d.findElements(loginCloseBtn);
                                return found.isEmpty() ? null : found;
                            });
            closeButtons.get(0).click();
        } catch (Exception ignored) {
        }

        try {
            driver.switchTo().activeElement().sendKeys(org.openqa.selenium.Keys.ESCAPE);
        } catch (Exception ignored) {
        }
    }

    public void searchProduct(String productName) {
        waitForVisibility(searchBox);
        type(searchBox, productName);
        click(searchButton);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void goToCart() {
        click(cartIcon);
    }

    public boolean isHomePageLoaded() {
        try {
            return driver.findElement(searchBox).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    }
