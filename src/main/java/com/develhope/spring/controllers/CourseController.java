package com.develhope.spring.controllers;


import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.Grade;
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
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/")
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

    @GetMapping("/list")
    public List<Course> getAllCourses() {
        return courseService.getAllCourse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> findCourseById (@PathVariable Long id){
        Optional<Course> c = courseService.getCourseById(id);
        if(c.isPresent()){
            return ResponseEntity.ok().body(
                    new Response(200,
                            "Course found: ",
                            c)
            );
        }else{
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            "Course not found, Id invalid"
                    )
            );
        }
    }
}
