package com.parabank.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

/**
 * OOP Concept: Encapsulation
 * Thread-safe singleton manager for interactive ExtentReports generation.
 */
public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    private ExtentReportManager() {
    }

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("ParaBank Automation Test Report");
            sparkReporter.config().setReportName("Banking Regression Execution Dashboard");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Application", "ParaBank Online Banking Simulation");
            extent.setSystemInfo("Domain", "FinTech / BFSI");
            extent.setSystemInfo("Environment", "QA Demo");
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }

    public static ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }

    public static void setTest(ExtentTest test) {
        extentTestThreadLocal.set(test);
    }

    public static void removeTest() {
        extentTestThreadLocal.remove();
    }
}
