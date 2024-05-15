package com.develhope.spring.controllers;


import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseDAO courseDAO;

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseDAO.findAll();
    }
}
