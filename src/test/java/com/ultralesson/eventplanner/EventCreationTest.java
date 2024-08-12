package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Venue;
import com.ultralesson.eventplanner.service.EventPlanner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class EventCreationTest {

    private EventPlanner eventPlanner;

    @BeforeMethod
    public void setUp() {
        eventPlanner = new EventPlanner();
    }

    @DataProvider(name = "eventDataProvider")
    public Object[][] eventDataProvider() {
        return new Object[][]{
                {01, "Event 1", "Description 1", new Venue(3, "Phantom Venue", "Nowhere", 0), true},
                {02, "Event 2", "", new Venue(3, "Phantom Venue", "Nowhere", 0), false},  // Invalid case: empty description
                {03, "", "Description 3", new Venue(3, "Phantom Venue", "Nowhere", 0), false},  // Invalid case: empty name
                {04, "Event 4", "Description 4", null, false},  // Invalid case: null description
                {05, null, "Description 5", null, false},  // Invalid case: null name
                {06, "Event 6", "Description 6", new Venue(3, "Phantom Venue", "Nowhere", 0), true}
        };
    }

    @Test(dataProvider = "eventDataProvider")
    public void testCreateEvent(int id, String name, String description, Venue venue, boolean expectedSuccess) {
        try {
            Event event = new Event(id, name, description, venue);
            boolean success = eventPlanner.createEvent(event);
            Assert.assertEquals(success, expectedSuccess, "Event creation success status did not match expected value.");
        } catch (IllegalArgumentException e) {
            Assert.assertFalse(expectedSuccess, "Expected failure but event creation succeeded.");
        }
    }

    @Factory
    public Object[] createInstances() {
        Object[] result = new Object[10];
        for (int i = 0; i < 10; i++) {
            result[i] = new Event(i * 100, "Event " + i, "description " + i, new Venue(i * 100, "Phantom Venue " + i, "Nowhere " + i, 5000));
        }
        return result;
    }

    @Factory
    public Object[] createInstances1() {
        EventPlanner eventPlanner = new EventPlanner();
        Venue venue = new Venue(1, "Conference Center", "New York Central", 500);
        Event event = new Event(1, "Tech Conference", "A conference about technology", venue);

        return new Object[]{
                new EventManagementTest(eventPlanner, event, venue)
        };
    }

    @DataProvider(name = "eventDataProvider1")
    public Object[][] createEventData() {
        EventPlanner eventPlanner = new EventPlanner();
        Venue venue = new Venue(1, "Conference Center", "New York Central", 500);
        Event event = new Event(1, "Tech Conference", "A conference about technology", venue);

        return new Object[][]{
                {eventPlanner, event, venue}
        };
    }

    @Factory(dataProvider = "eventDataProvider1")
    public Object[] createInstances(EventPlanner eventPlanner, Event event, Venue venue) {
        return new Object[]{
                new EventManagementTest(eventPlanner, event, venue)
        };
    }
}
