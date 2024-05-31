package com.develhope.spring.seeding;

import com.develhope.spring.entities.*;
import com.develhope.spring.enums.CourseType;
import com.develhope.spring.enums.RoleEnum;
import com.google.api.client.util.DateTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class SeedingProvider {

    public List<User> generateUser() {
        List<User> users = new ArrayList<>();

        // Aggiungere alcuni corsi di esempio con dettagli fittizi
        users.add(new User (null, "Gigi", "Proietti", "Gigino",
                "proiettili@gigi.it", "3333333333", "GGGPPP33G33I333G", RoleEnum.TUTOR, "PippoFranco"));
        users.add(new User (null, "Gigi", "Proietti", "Gigino1",
                "proiettili1@gigi.it", "3333333333", "GGGPPP33G33I333G", RoleEnum.TUTOR, "PippoFranco"));
        users.add(new User (null, "Gigi", "Proietti", "Gigino2",
                "proiettili2@gigi.it", "3333333333", "GGGPPP33G33I333G", RoleEnum.TUTOR, "PippoFranco"));
        // Puoi aggiungere altri corsi di esempio qui

        return users;
    }

    public List<Course> generateCourses() {
        List<Course> courses = new ArrayList<>();

        // Aggiungere alcuni corsi di esempio con dettagli fittizi
        courses.add(new Course(null, "", "2024-05-20", "2024-08-20", 40, 150.0,
                                "", "", null, true, CourseType.LIVE));
        courses.add(new Course(null, "","2024-05-20", "2024-08-20", 50, 120.0,
                                "", "", null, true, CourseType.ONDEMAND));
        courses.add(new Course(null, "", "2024-05-20", "2024-08-20", 30, 80.0,
                                "", "", null, true, CourseType.LIVE));

        // Puoi aggiungere altri corsi di esempio qui

        return courses;
    }

    public List<CourseSchedule> generateCourseSchedules(List<Course> courses) {
        List<CourseSchedule> schedules = new ArrayList<>();

        // Crea programmi di corso per ogni corso
        for (Course course : courses) {
            // Aggiungi date e link fittizi per i programmi di corso
            schedules.add(new CourseSchedule(null, course, new DateTime(new Date()), new DateTime(new Date()), "Test"));
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
                grades.add(new Grade(null, student, course, "", true));
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
                reviews.add(new Review(null, student, course, ""));
            }
        }

        return reviews;
    }
}

