package com.flipkart.stepdefinitions;

import com.flipkart.pages.HomePage;
import com.flipkart.pages.SearchResultsPage;
import com.flipkart.utils.DriverManager;
import com.flipkart.utils.ExcelUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;

public class DataDrivenSteps {

    WebDriver driver = DriverManager.getDriver();
    HomePage homePage = new HomePage(driver);
    SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

    private List<String[]> testData;

    @Given("test data is loaded from {string} sheet {string}")
    public void test_data_is_loaded_from_sheet(String filePath, String sheetName) {
        testData = ExcelUtils.readSheet(filePath, sheetName);
        Assert.assertFalse(testData.isEmpty(), "No test data found in Excel sheet");
    }

    @And("the user searches for each product from the test data")
    public void the_user_searches_for_each_product_from_test_data() {
        String firstProduct = testData.get(0)[0];
        homePage.searchProduct(firstProduct);
    }

    @Then("each search should return at least the expected minimum number of results")
    public void each_search_should_return_at_least_expected_minimum() {
        int expectedMin = Integer.parseInt(testData.get(0)[1]);
        int actualCount = searchResultsPage.getResultsCount();
        Assert.assertTrue(actualCount >= expectedMin,
                "Expected at least " + expectedMin + " results but got " + actualCount);
    }
}