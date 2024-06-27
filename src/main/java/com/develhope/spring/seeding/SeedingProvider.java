package com.develhope.spring.seeding;

import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.*;
import com.develhope.spring.enums.CourseType;
import com.develhope.spring.enums.RoleEnum;
import com.google.api.client.util.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class SeedingProvider {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> generateUser() {
        List<User> users = new ArrayList<>();

        // Aggiungere alcuni corsi di esempio con dettagli fittizi
        users.add(new User (null, "Liborio", "Di Stefano", "Lib18",
                "lib18@gmail.it", "3333333333", "GGGPPP33G33I333G", RoleEnum.STUDENT, passwordEncoder.encode("PippoFranco")));
        users.add(new User (null, "Davide", "Busà", "sbrakkius",
                "davide@gmail.it", "3333333333", "GGGPPP33G33I333G", RoleEnum.STUDENT, passwordEncoder.encode("PippoFranco")));
        users.add(new User (null, "Giovanni", "Innaimi", "vocalic oister",
                "vocalic@gmail.it", "3333333333", "GGGPPP33G33I333G", RoleEnum.STUDENT, passwordEncoder.encode("PippoFranco")));
        users.add(new User (null, "Gianluca", "Mulieddi", "aizen",
                "aizen@gmail.it", "3333333333", "GGGPPP33G33I333G", RoleEnum.TUTOR, passwordEncoder.encode("PippoFranco")));
        users.add(new User (null, "Giada", "Fiorito", "jade flower",
                "jade@gmail.it", "3333333333", "GGGPPP33G33I333G", RoleEnum.TUTOR, passwordEncoder.encode("PippoFranco")));
        // Puoi aggiungere altri corsi di esempio qui

        return users;
    }

    public List<Course> generateCourses() {
        List<Course> courses = new ArrayList<>();

        // Aggiungere alcuni corsi di esempio con dettagli fittizi
        courses.add(new Course(null, "Corso di Informatica", "2024-05-20", "2024-08-20", 40, 150.0,
                                "Informatica", "Corso preparatorio per informatici", userDAO.findByUsername("aizen").get(), CourseType.LIVE));
        courses.add(new Course(null, "Corso di Italiano","2024-05-20", "2024-08-20", 50, 120.0,
                                "Italiano", "Corso preparatorio per italiani", userDAO.findByUsername("jade flower").get(), CourseType.ONDEMAND));
        courses.add(new Course(null, "Corso di Inglese", "2024-05-20", "2024-08-20", 30, 80.0,
                                "Inglese", "corso preparatorio per la lingua inglese", userDAO.findByUsername("aizen").get(), CourseType.LIVE));

        // Puoi aggiungere altri corsi di esempio qui

        return courses;
    }

    public List<CourseSchedule> generateCourseSchedules(List<Course> courses) {
        List<CourseSchedule> schedules = new ArrayList<>();

        // Crea programmi di corso per ogni corso
        for (Course course : courses) {
            // Aggiungi date e link fittizi per i programmi di corso
            schedules.add(new CourseSchedule(null, course, new DateTime("2024-07-05T16:00:00+02:00"), new DateTime("2024-07-05T18:00:00+02:00"), "Primo link lezione!"));
            schedules.add(new CourseSchedule(null, course, new DateTime("2024-07-12T16:00:00+02:00"), new DateTime("2024-07-12T18:00:00+02:00"), "Secondo link lezione!"));
            schedules.add(new CourseSchedule(null, course, new DateTime("2024-07-19T16:00:00+02:00"), new DateTime("2024-07-19T18:00:00+02:00"), "Terzo link lezione!"));
        }

        return schedules;
    }

    public List<Grade> generateGrades(List<User> students, List<Course> courses) {
        List<Grade> grades = new ArrayList<>();

        // Simula voti per studenti e corsi (mancano le entità User e logica per assegnarli)
        for (User student : students) {
            // Assegna voti in modo casuale a corsi per questo studente
            for (Course course : courses) {
                // logica per assegnare un voto casuale allo studente per il corso
                grades.add(new Grade(null, student, course, "Votazione del professore!", true));
            }
        }

        return grades;
    }

    public List<Review> generateReviews(List<User> students, List<Course> courses) {
        List<Review> reviews = new ArrayList<>();

        // Simula recensioni per studenti e corsi (mancano le entità User e logica per assegnarli)
        for (User student : students) {
            // Aggiungi recensioni per alcuni corsi seguiti dallo studente
            for (Course course : courses) {
                // logica per verificare se lo studente ha seguito il corso e aggiungere una recensione casuale
                reviews.add(new Review(null, student, course, "Recensione dello studente!"));
            }
        }

        return reviews;
    }
}

