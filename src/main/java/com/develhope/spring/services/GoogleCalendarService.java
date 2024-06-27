package com.develhope.spring.services;

import com.develhope.spring.config.GoogleCalendarConfig;
import com.develhope.spring.entities.CourseSchedule;
import com.develhope.spring.entities.UserToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TimeZone;

@Service
public class GoogleCalendarService {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
//    @Autowired
//    private Calendar calendarService;
    @Autowired
    private GoogleCalendarConfig googleCalendarConfig;

    @Autowired
    private UserTokenService userTokenService;

//    public void addEvent(String summary, String description, String location, DateTime startDateTime, DateTime endDateTime) throws IOException {
//        Event event = new Event()
//                .setSummary(summary)
//                .setDescription(description)
//                .setLocation(location);
//
//        EventDateTime start = new EventDateTime()
//                .setDateTime(new com.google.api.client.util.DateTime(startDateTime.toStringRfc3339()))
//                .setTimeZone(TimeZone.getDefault().getID());
//        event.setStart(start);
//
//        EventDateTime end = new EventDateTime()
//                .setDateTime(new com.google.api.client.util.DateTime(endDateTime.toStringRfc3339()))
//                .setTimeZone(TimeZone.getDefault().getID());
//        event.setEnd(end);
//
//        calendarService.events().insert("primary", event).execute();
//    }

    public void addEvent(UserToken userToken, List<CourseSchedule> listaEventi) throws Exception {
        // Step 1: Get the credentials
        Credential credential = googleCalendarConfig.authorize(userToken);
        userTokenService.setToken(credential, userToken);

        // Step 2: Create the Google Calendar service
        Calendar service = new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
                .setApplicationName("Tech Cannolo")
                .build();

        // Step 3: Create the event
        for(CourseSchedule cs : listaEventi) {
            Event event = new Event()
                    .setSummary(cs.getCourse().getName())
                    .setLocation(cs.getLink());

            EventDateTime start = new EventDateTime()
                    .setDateTime(new com.google.api.client.util.DateTime(cs.getStartDateTime().toStringRfc3339()))
                    .setTimeZone(TimeZone.getDefault().getID());
            event.setStart(start);

            EventDateTime end = new EventDateTime()
                    .setDateTime(new com.google.api.client.util.DateTime(cs.getFinishDateTime().toStringRfc3339()))
                    .setTimeZone(TimeZone.getDefault().getID());
            event.setEnd(end);

            // Add the event to the calendar
            service.events().insert("primary", event).execute();
        }
    }



}