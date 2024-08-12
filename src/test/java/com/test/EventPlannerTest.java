package com.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class EventPlannerTest { private EventPlanner eventPlanner;
    ;

    @BeforeMethod
    @Parameters({"sharedEventPlanner"}) // Inject a shared EventPlanner instance
    public void setUp(@Optional EventPlanner sharedEventPlanner) {
        this.eventPlanner = sharedEventPlanner != null ? sharedEventPlanner : new EventPlanner();
    }


}