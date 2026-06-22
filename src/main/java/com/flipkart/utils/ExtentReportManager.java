package com.flipkart.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
            String reportPath = "test-output/ExtentReport_" + timestamp + ".html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Flipkart Automation Report");
            sparkReporter.config().setReportName("Flipkart End-to-End Test Execution");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Project", "Flipkart Automation");
            extent.setSystemInfo("Tester", "Omkar Mohite");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    public static void createTest(String testName) {
        ExtentTest extentTest = getInstance().createTest(testName);
        test.set(extentTest);
    }

    public static void logPass(String message) {
        if (test.get() != null) {
            test.get().pass(message);
        }
    }

    public static void logFail(String message) {
        if (test.get() != null) {
            test.get().fail(message);
        }
    }

    public static void logInfo(String message) {
        if (test.get() != null) {
            test.get().info(message);
        }
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
