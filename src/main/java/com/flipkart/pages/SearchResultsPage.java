package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage extends BasePage {

    private final By productCards = By.cssSelector("div._1AtVbE, div._4ddWXP, div.cPHDOP");
    private final By productTitles = By.cssSelector("a[href*='/p/']");
    private final By productPrices = By.cssSelector("div._30jeq3, div.Nx9bqj");
    private final By sortRelevance = By.xpath("//div[text()='Relevance']");
    private final By sortPriceLowHigh = By.xpath("//div[text()='Price -- Low to High']");
    private final By sortPriceHighLow = By.xpath("//div[text()='Price -- High to Low']");
    private final By ratingFilter4Star = By.xpath("//div[contains(text(),'4★ & above')]");
    private final By noResultsMessage = By.xpath("//div[contains(text(),'did not match any products')]");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public int getResultsCount() {
        try {
            return waitForAllVisible(productCards).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isNoResultsShown() {
        return isDisplayed(noResultsMessage);
    }

    public List<WebElement> getAllProductTitleElements() {
        return waitForAllVisible(productTitles);
    }

    public List<Double> getAllDisplayedPrices() {
        List<WebElement> priceElements = waitForAllVisible(productPrices);
        return priceElements.stream()
                .map(e -> e.getText().replaceAll("[^0-9]", ""))
                .filter(s -> !s.isEmpty())
                .map(Double::parseDouble)
                .toList();
    }

    public void sortByPriceLowToHigh() {
        click(sortPriceLowHigh);
    }

    public void sortByPriceHighToLow() {
        click(sortPriceHighLow);
    }

    public void applyFourStarFilter() {
        click(ratingFilter4Star);
    }

    public void clickFirstProduct() {
        waitForAllVisible(productTitles).get(0).click();
    }

    public String getFirstProductTitle() {
        return waitForAllVisible(productTitles).get(0).getText();
    }
}
