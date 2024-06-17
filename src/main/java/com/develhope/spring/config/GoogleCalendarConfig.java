package com.develhope.spring.config;

import com.develhope.spring.DAO.UserTokenDAO;
import com.develhope.spring.entities.UserToken;
import com.develhope.spring.services.UserTokenService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;

@Configuration
public class GoogleCalendarConfig {

    private static final String APPLICATION_NAME = "TEAM1_Online-Courses";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    @Autowired
    private UserTokenDAO userTokenDAO;
    @Autowired
    private UserTokenService userTokenService;

//    @Bean
//    public Calendar googleCalendarConfigService() throws Exception {
//        //Credential credential = authorize();
//        return new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, null)
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//    }

//    private Credential authorize() throws Exception {
//        InputStream in = getClass().getResourceAsStream("/credentials.json");
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, Collections.singleton(CalendarScopes.CALENDAR))
//                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//                .setAccessType("offline")
//                .build();
//
//        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(5050).build();
//        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
//    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Credential authorize(UserToken userToken) throws Exception {
        try {

            InputStream in = getClass().getResourceAsStream("/credentials.json");
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, Collections.singleton(CalendarScopes.CALENDAR))
                    .setAccessType("offline")
                    .build();

            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(5050).build();

            return new AuthorizationCodeInstalledApp(flow, receiver).authorize(userToken.getUser_id().getUsername());

        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


}