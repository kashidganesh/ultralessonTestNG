package com.eventplanner;

import com.eventplanner.pages.Event;
import com.eventplanner.pages.Schedule;
import com.eventplanner.pages.Attendee;
import com.eventplanner.pages.Venue;
import com.eventplanner.events.EventPlanner;
import com.eventplanner.events.InvitationSender;
import com.eventplanner.events.ScheduleFinder;

import java.time.LocalDateTime;
import java.util.List;

public class AllEvent {
    public static void main(String[] args) {

        Venue venue1 = new Venue(1, "Conference Center", "Newyork Central", 500);
        Venue venue2 = new Venue(2, "Hotel Ballroom", "Washington DC", 200);

        Event event1 = new Event(1, "Tech Conference", "A conference about technology", venue1);
        Event event2 = new Event(2, "Wedding Reception", "A lovely wedding reception", venue2);

        Attendee attendee1 = new Attendee(1, "John Doe", "john.doe@example.com");
        Attendee attendee2 = new Attendee(2, "Jane Smith", "jane.smith@example.com");


        event1.addAttendee(attendee1);
        event1.addAttendee(attendee2);
        event2.addAttendee(attendee1);
        event2.addAttendee(attendee2);


        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.addEvent(event1);
        eventPlanner.addEvent(event2);


        eventPlanner.scheduleEvent(event1, venue1, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3));
        eventPlanner.scheduleEvent(event2, venue2, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3));


        ScheduleFinder scheduleFinder = new ScheduleFinder(eventPlanner);
        List<Schedule> todaysSchedules = scheduleFinder.findSchedulesToday();
        System.out.println("Today's schedules:");
        for (Schedule schedule : todaysSchedules) {
            System.out.println(schedule);
        }


        InvitationSender invitationSender = new InvitationSender();
        invitationSender.sendInvitations(event1);
    }
}