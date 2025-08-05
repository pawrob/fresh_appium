package org.fresh.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {


    private static final Logger log = LogManager.getLogger(Logger.class.getName());

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test started: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failed: {}", result.getMethod().getMethodName(), result.getThrowable());
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test skipped: {}", result.getMethod().getMethodName());
    }
}
