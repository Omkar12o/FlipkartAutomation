package com.flipkart.stepdefinitions;

import com.flipkart.utils.DBUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseSteps {

    private ResultSet resultSet;
    private String orderStatus;
    private double orderPrice;

    @Given("the orders table is seeded with sample data")
    public void the_orders_table_is_seeded_with_sample_data() {
        DBUtils.setupSampleData();
    }

    @When("the user queries the order with id {int}")
    public void the_user_queries_the_order_with_id(Integer orderId) {
        try {
            resultSet = DBUtils.executeQuery("SELECT * FROM orders WHERE order_id = " + orderId);
            if (resultSet.next()) {
                orderStatus = resultSet.getString("status");
                orderPrice = resultSet.getDouble("price");
            } else {
                Assert.fail("No order found with id " + orderId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to query order", e);
        }
    }

    @Then("the order status should be {string}")
    public void the_order_status_should_be(String expectedStatus) {
        Assert.assertEquals(orderStatus, expectedStatus, "Order status did not match expected value");
    }

    @Then("the order price should be greater than {int}")
    public void the_order_price_should_be_greater_than(Integer minPrice) {
        Assert.assertTrue(orderPrice > minPrice, "Order price was not greater than " + minPrice);
    }

    @When("the user queries all orders")
    public void the_user_queries_all_orders() {
        resultSet = DBUtils.executeQuery("SELECT * FROM orders");
    }

    @Then("no order should have a negative price")
    public void no_order_should_have_negative_price() {
        try {
            while (resultSet.next()) {
                double price = resultSet.getDouble("price");
                Assert.assertTrue(price >= 0,
                        "Found order with negative price: " + resultSet.getString("product_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to read query results", e);
        }
    }
}