package com.develhope.spring.controllers;


import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.CourseDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.models.ResponseInvalid;
import com.develhope.spring.models.ResponseValid;
import com.develhope.spring.services.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    Logger logger = LoggerFactory.getLogger(CourseController.class);

    @PostMapping("/t")
    public ResponseEntity<Response> addCourse(@RequestBody CourseDTO course) {
        try {
            CourseDTO newCourse = courseService.addCourse(course);
            logger.info("corso creato" + newCourse);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            " added correctly",
                            newCourse)
            );
        } catch (CourseException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
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
                    new ResponseValid(200,
                            "List of courses: ",
                            courses)
            );
        } catch (Exception e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> findCourseById (@PathVariable Long id) {
        try {
            CourseDTO c = courseService.getCourseById(id);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "Course found: ",
                            c)
            );
        } catch (CourseException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            "Course not found, Id invalid"
                    )
            );
        }
    }


    @PutMapping("/t/{id}")
    public ResponseEntity<Response> updateCourseById(@PathVariable Long id, @RequestBody CourseDTO courseDTO){
        try{
            courseService.updateCourseById(id, courseDTO);
            return ResponseEntity.ok().body(new ResponseValid(200, "course updated",courseDTO));
        }catch(CourseException e){
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "course id not found"));
        } catch (UserException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "user id not found"));
        }
    }

    @DeleteMapping("/t/{id}")
    public ResponseEntity<Response> deleteCourseById(@PathVariable Long id){
        try{
            courseService.deleteCourseById(id);
            return ResponseEntity.ok().body(new Response(200, "course deleted"));
        }catch (CourseException e){
            logger.error("errore " + e.getMessage());
            return  ResponseEntity.status(400).body(new ResponseInvalid(400, "course id not found"));
        }
    }

    @GetMapping("/active/tutor/{id}")
    public ResponseEntity<Response> getActiveCourseByTutor(@PathVariable Long id){
        try {
           List<CourseDTO> courses = courseService.getActiveCoursesByTutor(id);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "the active courses are:  ",
                            courses)
            );
        } catch (CourseException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
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
                    new ResponseValid(200,
                            "the active courses are:  ",
                            courses)
            );
        } catch (Exception e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

}
