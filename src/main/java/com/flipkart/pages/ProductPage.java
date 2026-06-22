package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    private final By productTitle = By.cssSelector("span.B_NuCI, span.VU-ZEz");
    private final By productPrice = By.cssSelector("div._30jeq3._16Jk6d, div.Nx9bqj.CxhGGd");
    private final By addToCartBtn = By.xpath("//div[text()='Add to cart']");
    private final By buyNowBtn = By.xpath("//button[contains(text(),'BUY NOW')]");
    private final By wishlistIcon = By.cssSelector("div._2lr-3T, button._6tcW0F");
    private final By pincodeInput = By.cssSelector("input[name='pincode'], input._36yFo0");
    private final By checkPincodeBtn = By.xpath("//button[contains(text(),'Check')]");
    private final By deliveryInfo = By.cssSelector("div._16FRp0, div.pZbfee");
    private final By specificationsSection = By.xpath("//div[contains(text(),'Specifications')]");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductTitle() {
        return waitForVisibility(productTitle)
        .getText();
    }

    public String getProductPrice() {
        return waitForVisibility(productPrice)
        		.getText();
    }

    public void addToCart() {
        click(addToCartBtn);
    }

    public void buyNow() {
        click(buyNowBtn);
    }

    public void addToWishlist() {
        click(wishlistIcon);
    }

    public void checkDeliveryByPincode(String pincode) {
        type(pincodeInput, pincode);
        click(checkPincodeBtn);
    }

    public boolean isDeliveryInfoDisplayed() {
        return isDisplayed(deliveryInfo);
    }

    public boolean isAddToCartVisible() {
        return isDisplayed(addToCartBtn);
    }
}
