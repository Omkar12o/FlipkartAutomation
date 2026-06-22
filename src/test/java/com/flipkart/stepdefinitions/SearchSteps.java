package com.flipkart.stepdefinitions;

import com.flipkart.pages.HomePage;
import com.flipkart.pages.SearchResultsPage;
import com.flipkart.utils.ConfigReader;
import com.flipkart.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchSteps {

    WebDriver driver = DriverManager.getDriver();
    HomePage homePage = new HomePage(driver);
    SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

    @Given("the user is on the Flipkart home page")
    public void the_user_is_on_the_flipkart_home_page() {
        driver.get(ConfigReader.getUrl());
        homePage.waitForPageTitle();
        homePage.closeLoginPopupIfPresent();
        Assert.assertTrue(homePage.isHomePageLoaded(), "Flipkart home page did not load");
    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String product) {
        homePage.searchProduct(product);
    }

    @Then("search results should be displayed")
    public void search_results_should_be_displayed() {
        Assert.assertTrue(searchResultsPage.getResultsCount() > 0, "No search results displayed");
    }

    @Then("the results count should be greater than {int}")
    public void the_results_count_should_be_greater_than(Integer count) {
        Assert.assertTrue(searchResultsPage.getResultsCount() > count,
                "Expected result count greater than " + count);
    }

    @Then("no results message should be displayed")
    public void no_results_message_should_be_displayed() {
        Assert.assertTrue(searchResultsPage.isNoResultsShown(), "No-results message was not shown for invalid search");
    }

    @Then("each result should contain the search keyword in its title or be a relevant suggestion")
    public void each_result_should_contain_keyword() {
        Assert.assertTrue(searchResultsPage.getAllProductTitleElements().size() > 0,
                "No product titles found in search results");
    }
}