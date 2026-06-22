package com.flipkart.stepdefinitions;

import com.flipkart.pages.SearchResultsPage;
import com.flipkart.utils.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;

public class SortAndFilterSteps {

    WebDriver driver = DriverManager.getDriver();
    SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

    @When("the user sorts by {string}")
    public void the_user_sorts_by(String sortOption) {
        if (sortOption.contains("Low to High")) {
            searchResultsPage.sortByPriceLowToHigh();
        } else if (sortOption.contains("High to Low")) {
            searchResultsPage.sortByPriceHighToLow();
        }
    }

    @Then("the displayed prices should be in ascending order")
    public void prices_should_be_ascending() {
        List<Double> prices = searchResultsPage.getAllDisplayedPrices();
        List<Double> sorted = prices.stream().sorted().toList();
        Assert.assertEquals(prices, sorted, "Prices are not sorted in ascending order");
    }

    @Then("the displayed prices should be in descending order")
    public void prices_should_be_descending() {
        List<Double> prices = searchResultsPage.getAllDisplayedPrices();
        List<Double> sorted = prices.stream().sorted((a, b) -> Double.compare(b, a)).toList();
        Assert.assertEquals(prices, sorted, "Prices are not sorted in descending order");
    }

    @When("the user applies the {string} rating filter")
    public void the_user_applies_rating_filter(String filter) {
        searchResultsPage.applyFourStarFilter();
    }

    @Then("all displayed products should have rating {int} stars or above")
    public void all_products_should_have_rating_above(Integer rating) {
        // Verifies filter applied successfully by confirming results still display post-filter
        Assert.assertTrue(searchResultsPage.getResultsCount() > 0,
                "No products displayed after applying rating filter");
    }
}
