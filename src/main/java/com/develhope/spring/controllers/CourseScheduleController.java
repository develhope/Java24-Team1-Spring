package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.CourseScheduleException;
import com.develhope.spring.models.DTO.CourseScheduleDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.CourseScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/course_schedule")
public class CourseScheduleController {


    @Autowired
    private CourseScheduleService courseScheduleService;

    @PostMapping("/t")
    public ResponseEntity<Response> postCourseSchedule(@RequestBody CourseScheduleDTO courseSchedule) {
        try {
            CourseScheduleDTO newCourseSchedule = courseScheduleService.addCourseSchedule(courseSchedule);
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "New Course Schedule added correctly",
                            newCourseSchedule
                    ));
        } catch (CourseScheduleException | CourseException e) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    ));
        }
    }

    @GetMapping
    public ResponseEntity<Response> getCourseScheduleById() {
        try {
            List<CourseScheduleDTO> courses = courseScheduleService.getAllCourseSchedule();
            return ResponseEntity.ok().body(
                    new Response(200,
                            "List of courses schedules: ",
                            courses)
            );
        } catch (Exception e) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCourseScheduleById(@PathVariable Long id) {
        try {
            CourseScheduleDTO cs = courseScheduleService.getCourseScheduleById(id);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "Schedule found: ",
                            cs)
            );
        } catch (CourseScheduleException e) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Schedule not found, Id invalid"
                    )
            );
        }
    }

    @PutMapping("/t/{id}")
    public ResponseEntity<Response> updateCourseScheduleById(@PathVariable Long id, @RequestBody CourseScheduleDTO courseScheduleDTO) {
        try {
            courseScheduleService.updateCourseScheduleById(id, courseScheduleDTO);
            return ResponseEntity.ok().body(new Response(200, "course schedule updated", courseScheduleDTO));
        } catch (CourseScheduleException e) {
            return ResponseEntity.status(400).body(new Response(400, "course schedule id not found"));
        } catch (CourseException e) {
            return ResponseEntity.status(400).body(new Response(400, "course id not found"));
        }
    }

    @DeleteMapping("/t/{id}")
    public ResponseEntity<Response> deleteCourseScheduleById(@PathVariable Long id) {
        try {
            courseScheduleService.deleteCourseScheduleById(id);
            return ResponseEntity.ok().body(new Response(200, "course schedule deleted"));
        } catch (CourseScheduleException e) {
            return ResponseEntity.status(400).body(new Response(400, "course schedule id not found"));
        }
    }
}
