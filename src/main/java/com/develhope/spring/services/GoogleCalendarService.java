package com.develhope.spring.services;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.TimeZone;

@Service
public class GoogleCalendarService {

    @Autowired
    private Calendar calendarService;

    public void addEvent(String summary, String description, String location, DateTime startDateTime, DateTime endDateTime) throws IOException {
        Event event = new Event()
                .setSummary(summary)
                .setDescription(description)
                .setLocation(location);

        EventDateTime start = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(startDateTime.toStringRfc3339()))
                .setTimeZone(TimeZone.getDefault().getID());
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(endDateTime.toStringRfc3339()))
                .setTimeZone(TimeZone.getDefault().getID());
        event.setEnd(end);

        calendarService.events().insert("primary", event).execute();
    }

}