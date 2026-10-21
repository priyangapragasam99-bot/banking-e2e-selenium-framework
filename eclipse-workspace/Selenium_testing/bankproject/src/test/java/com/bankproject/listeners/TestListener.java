package com.bankproject.listeners;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.bankproject.reports.ExtentReportManager;

import com.bankproject.basetest.BaseTest;
import com.bankproject.utils.ScreenShotUtils;

public class TestListener implements ITestListener {

    private static final ExtentReports extent =
            ExtentReportManager.getInstance();

    private static final Map<Integer, ExtentTest> testMap =
            new ConcurrentHashMap<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test =
                extent.createTest(result.getMethod().getMethodName());

        testMap.put(
                System.identityHashCode(result),
                test
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getTest(result).pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = getTest(result);

        test.fail(result.getThrowable());

        Object testInstance = result.getInstance();

        if (testInstance instanceof BaseTest) {
            WebDriver driver =
                    ((BaseTest) testInstance).driver;

            if (driver != null) {
                try {
                    String screenshotPath =
                            ScreenShotUtils.captureScreenshot(
                                    driver,
                                    result.getMethod().getMethodName()
                            );

                    test.info("Screenshot saved: " + screenshotPath);
                } catch (Exception e) {
                    test.warning(
                            "Screenshot capture failed: "
                                    + e.getMessage()
                    );
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getTest(result).skip("Test skipped");
    }

    @Override
    public void onFinish(
            org.testng.ITestContext context) {
        extent.flush();
    }

    private ExtentTest getTest(ITestResult result) {
        return testMap.get(System.identityHashCode(result));
    }
}