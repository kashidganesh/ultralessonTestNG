package com.test;

import com.eventplanner.pages.Event;
import com.eventplanner.pages.Venue;
import com.eventplanner.events.EventPlanner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Attendee {

    com.eventplanner.pages.Attendee attendee;
    EventPlanner eventPlanner;

    @BeforeMethod
    public void setUp() {
        attendee = new com.eventplanner.pages.Attendee(1, "John Doe", "john.doe@example.com");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Invalid email format", groups = {"validation"})
    public void shouldThrowExceptionForInvalidEmail() {
        com.eventplanner.pages.Attendee invalidAttendee = new com.eventplanner.pages.Attendee(5, "Ishan Kumar", "example.com");
    }

    @Test(groups = {"validateAttendee"})
    public void testAttendeeProperties() {
        assertEquals(attendee.getId(), 1, "Attendee ID does not match");
        assertEquals(attendee.getName(), "John Doe", "Attendee name does not match");
        assertEquals(attendee.getEmail(), "john.doe@example.com", "Attendee email does not match");
    }


    @Test(expectedExceptions = IllegalArgumentException.class, groups = {"validation"})
    public void testAddInvalidAttendeeToEvent() {
        com.eventplanner.pages.Attendee invalidAttendee = new com.eventplanner.pages.Attendee(4, "", "");
    }

    @Test(expectedExceptions = Exception.class, groups = {"addAttendee"})
    public void testAddAttendeeToNonExistentEvent() {
        EventPlanner eventPlanner = new EventPlanner();
        Venue venue = new Venue(1, "Conference Center", null, 500);
        Event fakeEvent = new Event(99, "Non-Existent Event", "This event does not exist", venue);

        com.eventplanner.pages.Attendee attendee = new com.eventplanner.pages.Attendee(5, "Charlie Green", "charlie.green@example.com");
        fakeEvent.addAttendee(attendee);
    }

    @Test(groups = {"removeAttendee"})
    public void testRemoveAttendeeFromEvent() {
        EventPlanner eventPlanner = new EventPlanner();
        Venue venue = new Venue(1, "Conference Center", "New York Central", 500);
        Event event = new Event(1, "Tech Conference", "A conference about technology", venue);
        eventPlanner.addEvent(event);

        com.eventplanner.pages.Attendee attendee = new com.eventplanner.pages.Attendee(3, "Alice Brown", "alice.brown@example.com");
        event.addAttendee(attendee);

        event.removeAttendee(attendee);
        Assert.assertFalse(event.getAttendees().contains(attendee), "Attendee should have been removed from the event");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = {"validation"})
    public void shouldNotAllowAddingAttendeeWithNullName() {
        Venue venue = new Venue(1, "Conference Center", "New York Central", 500);
        Event event = new Event(2, "Tech Conference", "A comprehensive tech event", venue);
        com.eventplanner.pages.Attendee attendee = new com.eventplanner.pages.Attendee(3, null, "nullname@example.com");
        event.addAttendee(attendee);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = {"validation"})
    public void shouldNotAllowAddingAttendeeToNonexistentEvent() {
        EventPlanner eventPlanner = new EventPlanner();
        Event nonExistentEvent = new Event(99, "Ghost Event", "This is a phantom event", new Venue(10, null, "Nowhere", 0));
        com.eventplanner.pages.Attendee attendee = new com.eventplanner.pages.Attendee(4, "Alice Wonder", "alice.wonder@example.com");
        // This method should ideally check if the event exists in the event planner before adding an attendee.
        nonExistentEvent.addAttendee(attendee);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateEventWithoutName() {
        Venue venue = new Venue(1, "Conference Center", "New York Central", 500);
        Event event = new Event(0, "", "A conference about technology", venue);
        eventPlanner.createEvent(event);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createTestEvent() {
        Venue venue = new Venue(1, "Conference Center", "New York Central", 500);
        Event event = new Event(1, "Tech Conference", "A conference about technology", venue);
        eventPlanner.addEvent(event);
        Assert.assertNotNull(event, "Event should be created");
    }

    @Test(dependsOnMethods = {"createTestEvent"})
    public void testAddAttendeeToEvent() {
        eventPlanner = new EventPlanner();
        Venue venue = new Venue(1, "Conference Center", "New York Central", 500);
        Event event = new Event(1, "Tech Conference", "A conference about technology", venue);
        eventPlanner.addEvent(event);

        com.eventplanner.pages.Attendee newAttendee = new com.eventplanner.pages.Attendee(3, "Alice Brown", "alice.brown@example.com");
        int initialSize = event.getAttendees().size();
        event.addAttendee(newAttendee);

        Assert.assertTrue(event.getAttendees().contains(newAttendee), "Attendee should be added to the event");
        Assert.assertEquals(event.getAttendees().size(), initialSize + 1, "Attendee count should increase by 1");
        Assert.assertEquals(newAttendee.getName(), "Alice Brown", "Attendee's name should be 'Alice Brown'");
    }

}
