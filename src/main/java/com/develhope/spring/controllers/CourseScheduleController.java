package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.CourseScheduleException;
import com.develhope.spring.models.DTO.CourseScheduleDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.CourseScheduleService;
import com.develhope.spring.utilities.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/course_schedule")
public class CourseScheduleController {


    @Autowired
    private CourseScheduleService courseScheduleService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Response> postCourseSchedule(@RequestBody CourseScheduleDTO courseSchedule, @RequestHeader("Authorization") String authHeader) {
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            CourseScheduleDTO newCourseSchedule = courseScheduleService.addCourseSchedule(courseSchedule, username);
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
    public ResponseEntity<Response> getAllCourseSchedule() {
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

    @GetMapping("/course/{id}")
    public ResponseEntity<Response> getCourseScheduleByCourseId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "Course schedule for id " + id + " retrieved correctly",
                            courseScheduleService.getAllCourseScheduleByCourse(id)
                    )
            );
        }
        catch (CourseException e) {
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
    public ResponseEntity<Response> updateCourseScheduleById(@PathVariable Long id, @RequestBody CourseScheduleDTO courseScheduleDTO,  @RequestHeader("Authorization") String authHeader) {
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            courseScheduleService.updateCourseScheduleById(id, courseScheduleDTO, username);
            return ResponseEntity.ok().body(new Response(200, "course schedule updated", courseScheduleDTO));
        } catch (CourseScheduleException e) {
            return ResponseEntity.status(400).body(new Response(400, "course schedule id not found"));
        } catch (CourseException e) {
            return ResponseEntity.status(400).body(new Response(400, "course id not found"));
        }
    }

    @DeleteMapping("/a/{id}")
    public ResponseEntity<Response> deleteCourseScheduleById(@PathVariable Long id) {
        try {
            courseScheduleService.deleteCourseScheduleById(id);
            return ResponseEntity.ok().body(new Response(200, "course schedule deleted"));
        } catch (CourseScheduleException e) {
            return ResponseEntity.status(400).body(new Response(400, "course schedule id not found"));
        }
    }

    @DeleteMapping("/t/{id}")
    public ResponseEntity<Response> deleteYourCourseScheduleById(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            courseScheduleService.deleteYourCourseScheduleById(id, username);
            return ResponseEntity.ok().body(new Response(200, "course schedule deleted"));
        } catch (CourseScheduleException e) {
            return ResponseEntity.status(400).body(new Response(400, "course schedule id not found"));
        }
    }
}
