package com.flipkart.runner;

import io.cucumber.testng
    .AbstractTestNGCucumberTests;
import io.cucumber.testng
    .CucumberOptions;

@CucumberOptions(
    features = 
        "src/test/resources/features",
    glue = {
        "com.flipkart.stepdefinitions",
        "com.flipkart.hooks"
    },
    plugin = {
        "pretty",
        "html:test-output/cucumberReport/report.html",
        "json:test-output/cucumber-report.json"
    },
    monochrome = true
)
public class TestRunner extends
    AbstractTestNGCucumberTests {
}