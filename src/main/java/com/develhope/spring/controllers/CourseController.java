package com.develhope.spring.controllers;


import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.CourseDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.CourseService;
import com.develhope.spring.utilities.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutor")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private JWTUtil jwtUtil;
    @PostMapping
    public ResponseEntity<Response> addCourse(@RequestBody CourseDTO course, @RequestHeader("Authorization") String authHeader) {
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            CourseDTO newCourse = courseService.addCourse(username, course);
            return ResponseEntity.ok().body(
                    new Response(200,
                            " added correctly",
                            newCourse)
            );
        } catch (CourseException | UserException e) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping
    public ResponseEntity<Response> getAllCourses() {
        try {
            List<CourseDTO> courses = courseService.getAllCourse();
            return ResponseEntity.ok().body(
                    new Response(200,
                            "List of courses: ",
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
    public ResponseEntity<Response> findCourseById(@PathVariable Long id) {
        try {
            CourseDTO c = courseService.getCourseById(id);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "Course found: ",
                            c)
            );
        } catch (CourseException e) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Course not found, Id invalid"
                    )
            );
        }
    }
    @DeleteMapping("/a/{id}")
    public ResponseEntity<Response> deleteCourseById(@PathVariable Long id){
        try{
            courseService.deleteCourseById(id);
            return ResponseEntity.ok().body(new Response(200, "course deleted"));
        }catch (CourseException e){
            return  ResponseEntity.status(404).body(new Response(404, "course id not found"));
        }
    }

    @DeleteMapping("/me/{id}")
    public ResponseEntity<Response> deleteYourCourse(@PathVariable Long id, @RequestHeader("Authorization") String authHeader){
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try{
            courseService.deleteYourCourse(id, username);
            return ResponseEntity.ok().body(new Response(200, "course deleted"));
        }catch (CourseException e){
            return  ResponseEntity.status(404).body(new Response(404, "course id not found"));
        }
    }

    @PutMapping("/t/{id}")
    public ResponseEntity<Response> updateCourseById(@PathVariable Long id, @RequestBody CourseDTO courseDTO, @RequestHeader("Authorization") String authHeader){
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try{
            courseService.updateCourseById(id, courseDTO, username);
            return ResponseEntity.ok().body(new Response(200, "course updated",courseDTO));
        }catch(CourseException e){
            return ResponseEntity.status(400).body(new Response(400, "course id not found"));
        } catch (UserException e) {
            return ResponseEntity.status(400).body(new Response(400, "user id not found"));
        }
    }
    @GetMapping("/active/tutor/{id}")
    public ResponseEntity<Response> getActiveCourseByTutor(@PathVariable Long id){
        try {
           List<CourseDTO> courses = courseService.getActiveCoursesByTutor(id);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "the active courses are:  ",
                            courses)
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

    @GetMapping("/active/tutor/t/me")
    public ResponseEntity<Response> getYourActiveCourse(@RequestHeader("Authorization") String authHeader){
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            List<CourseDTO> courses = courseService.getYourActiveCourse(username);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "the active courses are:  ",
                            courses)
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

    @GetMapping("/active/subject")
    public ResponseEntity<Response> getActiveCourseBySubject(@RequestParam String s){
        try {
            List<CourseDTO> courses = courseService.getActiveCourseBySubject(s);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "the active courses are:  ",
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

}
