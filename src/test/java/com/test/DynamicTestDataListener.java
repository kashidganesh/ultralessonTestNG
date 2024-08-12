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

                // Example of injecting the first dataset into the test method as a parameter
                if (dataSets.length > 0) {
                    testResult.getTestContext().setAttribute("data", dataSets);
                }
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // Implement any post-invocation logic if needed
    }
}