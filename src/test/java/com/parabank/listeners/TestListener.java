package com.parabank.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.parabank.utils.DriverManager;
import com.parabank.utils.ExtentReportManager;
import com.parabank.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * OOP Concept: Runtime Polymorphism (Method Overriding)
 * Implements TestNG's ITestListener to intercept test execution lifecycles,
 * dynamically log results to ExtentReports, and capture failure screenshots.
 */
public class TestListener implements ITestListener {

    private static final ExtentReports extent = ExtentReportManager.getInstance();

    @Override
    public void onStart(ITestContext context) {
        System.out.println(">>> Starting Test Suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println(">>> Finished Test Suite: " + context.getName());
        extent.flush(); // Flushes all data to reports/ExtentReport.html
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        ExtentTest test = extent.createTest(testName, description);
        ExtentReportManager.setTest(test);
        ExtentReportManager.getTest().log(Status.INFO, "Started execution: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
        ExtentReportManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentReportManager.getTest().log(Status.FAIL, "Test Failed: " + testName);
        ExtentReportManager.getTest().log(Status.FAIL, result.getThrowable());

        // Capture screenshot upon test failure
        String screenshotPath = ScreenshotUtil.captureScreenshot(DriverManager.getDriver(), testName);
        if (screenshotPath != null) {
            ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot: " + testName);
        }

        ExtentReportManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
        ExtentReportManager.removeTest();
    }
}
