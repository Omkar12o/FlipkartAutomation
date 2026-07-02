package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartItems = By.cssSelector("div._3dsJAO, div.cPHDOP,div._1AtVbE");
    private final By emptyCartMessage = By.xpath("//div[contains(text(),'empty')]");
    private final By placeOrderBtn = By.xpath("//button[@type='submit'])]");
    private final By removeItemBtn = By.xpath("//div[contains(text(),'REMOVE')]");
    private final By totalAmount = By.cssSelector("div._1eXndQ, div.Z8JjpR");
    private final By incrementQtyBtn = By.cssSelector("div._28v9TX, span._3rXFFb");
    private final By itemTitleInCart = By.cssSelector("div._3vQYRY, div.col.col-7-12 a");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemCount() {
        try {
            return waitForAllVisible(cartItems).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isCartEmpty() {
        return isDisplayed(emptyCartMessage);
    }

    public void removeFirstItem() {
        click(removeItemBtn);
    }

    public void proceedToCheckout() {
        click(placeOrderBtn);
    }

    public String getTotalAmount() {
        return waitForVisibility(totalAmount)
        .getText();
    }

    public List<String> getCartItemTitles() {
        return waitForAllVisible(itemTitleInCart).stream().map(e -> e.getText()).toList();
    }
}
