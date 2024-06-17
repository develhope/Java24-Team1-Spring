package com.develhope.spring.seeding;

import com.develhope.spring.DAO.*;
import com.develhope.spring.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedingService {

    @Autowired
    private SeedingProvider seedingProvider;
    private CourseDAO courseDAO;
    private CourseScheduleDAO courseScheduleDAO;
    private GradeDAO gradeDAO;
    private ReviewDAO reviewDAO;
    private UserDAO userDAO;
    private IscrizioneDAO iscrizioneDAO;
    private UserTokenDAO userTokenDAO;

    @Autowired
    public SeedingService(CourseDAO courseDAO, CourseScheduleDAO courseScheduleDAO, GradeDAO gradeDAO, ReviewDAO reviewDAO, UserDAO userDAO, IscrizioneDAO iscrizioneDAO, UserTokenDAO userTokenDAO) {
        this.courseDAO = courseDAO;
        this.courseScheduleDAO = courseScheduleDAO;
        this.gradeDAO = gradeDAO;
        this.reviewDAO = reviewDAO;
        this.userDAO = userDAO;
        this.iscrizioneDAO = iscrizioneDAO;
        this.userTokenDAO = userTokenDAO;
    }


    public void initDatabase() {
        this.seedUsers();
        this.seedCourses();
        this.seedCourseSchedules();
        this.seedGrades();
        this.seedReviews();
    }

    public void cleanDatabase() {
        this.cleanSubscriprions();
        this.cleanUserToken();
        this.cleanReviews();
        this.cleanGrades();
        this.cleanCourseSchedules();
        this.cleanCourses();
        this.cleanUsers();
    }

    private void seedCourses() {
        this.courseDAO.saveAll(this.seedingProvider.generateCourses());
    }

    private void seedUsers() {
        this.userDAO.saveAll(this.seedingProvider.generateUser());
    }

    private void seedCourseSchedules() {
        this.courseScheduleDAO.saveAll(this.seedingProvider.generateCourseSchedules(courseDAO.findAll()));

    }

    private void seedGrades() {
        this.gradeDAO.saveAll(this.seedingProvider.generateGrades(userDAO.findAll(), courseDAO.findAll()));

    }

    private void seedReviews() {
        this.reviewDAO.saveAll(this.seedingProvider.generateReviews(userDAO.findAll(), courseDAO.findAll()));
    }

    public void cleanCourses() {
        this.courseDAO.deleteAll();
    }

    public void cleanUsers() {
        this.userDAO.deleteAll();
    }

    public void cleanCourseSchedules() {
        this.courseScheduleDAO.deleteAll();
    }

    public void cleanGrades() {
        this.gradeDAO.deleteAll();
    }

    public void cleanReviews() {
        this.reviewDAO.deleteAll();
    }

    public void cleanSubscriprions() {
        this.iscrizioneDAO.deleteAll();
    }

    public void cleanUserToken() {
        this.userTokenDAO.deleteAll();
    }
}

