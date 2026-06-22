package com.flipkart.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.flipkart.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    static ExtentReports extent;
    static ExtentTest test;

    @BeforeAll
    public static void setupReport() {
        ExtentSparkReporter spark = new 
            ExtentSparkReporter("test-output/report.html");
        spark.config().setReportName("Flipkart Test Report");
        spark.config().setDocumentTitle("Automation Report");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Before
    public void setUp(Scenario scenario) {
        test = extent.createTest(scenario.getName());
        DriverManager.getDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = DriverManager.getDriver();
            byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", 
                scenario.getName());
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            test.fail("Failed: " + scenario.getName());
        } else {
            test.pass("Passed: " + scenario.getName());
        }
        DriverManager.quitDriver();
    }

    @AfterAll
    public static void flushReport() {
        extent.flush();
    }
}