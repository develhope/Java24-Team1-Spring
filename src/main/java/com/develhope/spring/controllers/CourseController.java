package com.develhope.spring.controllers;


import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.entities.Course;
import com.develhope.spring.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseDAO.findAll();
    }

    @PostMapping("/add")
    public void postCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }
}
