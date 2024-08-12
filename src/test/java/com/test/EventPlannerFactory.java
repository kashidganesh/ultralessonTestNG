package com.test;

import org.testng.annotations.Factory;

public class EventPlannerFactory {

    @Factory
    public Object[] createInstances() {
        EventPlanner sharedEventPlanner = new EventPlanner();
        return new Object[] { new EventPlannerTest() };
    }

}
