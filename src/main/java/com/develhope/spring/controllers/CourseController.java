package com.develhope.spring.controllers;


import com.develhope.spring.entities.Course;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.models.DTO.CourseDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<Response> addCourse(@RequestBody CourseDTO course) {
        try {
            CourseDTO newCourse = courseService.addCourse(course);
            return ResponseEntity.ok().body(
                    new Response(200,
                            " added correctly",
                            newCourse)
            );
        } catch (CourseException e) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );

        }
    }

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseService.getAllCourse();
    }

    @GetMapping("/{id}")
    public Optional<Course> findCourseById (@PathVariable Long id){
        return courseService.getCourseById(id);
    }
}
