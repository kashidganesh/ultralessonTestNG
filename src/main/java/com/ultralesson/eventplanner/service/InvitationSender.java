package com.ultralesson.eventplanner.service;

import com.ultralesson.eventplanner.model.Attendee;
import com.ultralesson.eventplanner.model.Event;

import java.util.List;

public class InvitationSender {
    public void sendInvitations(Event event) {
        if (event == null)
            throw new IllegalArgumentException("Event cannot be null");

        List<Attendee> attendees = event.getAttendees();
        if (attendees == null || attendees.isEmpty())
            throw new IllegalArgumentException("No attendees to send invitations to");

        for (Attendee attendee : attendees) {
            String email = attendee.getEmail();
            String eventName = event.getName();
            String eventDescription = event.getDescription();
            String venueName = event.getVenue().getName();

            // Deliberate bug: using '==' instead of equals() for string comparison
            if (email.isEmpty() || eventName.isEmpty() || eventDescription.isEmpty() || venueName.isEmpty()) {
                System.out.println("Skipping invitation due to incomplete information.");
                continue;
            }

            System.out.println("Sending invitation to " + email+" Dear " + attendee.getName() + ",\n" +
                    "You are invited to the event: " + event.getName() + "\n" +
                    "Description: " + event.getDescription() + "\n" +
                    "Venue: " + event.getVenue().getName() + "\n");
            // Send the actual invitation (e.g., by calling an email service)
        }
    }
}

