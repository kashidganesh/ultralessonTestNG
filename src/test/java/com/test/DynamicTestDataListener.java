package com.test;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;



public class DynamicTestDataListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            DynamicTestData dynamicTestData = method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(DynamicTestData.class);
            if (dynamicTestData != null) {
                String[] dataSets = dynamicTestData.dataSets();
                // Code to dynamically inject the specified test data into the test method
                // This may involve altering the parameters passed to the test method or manipulating
                // TestNG's test context to carry the data sets
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // Implement any post-invocation logic if needed
    }
}