package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.CourseScheduleException;
import com.develhope.spring.models.DTO.requestDTO.CourseScheduleRequestDTO;
import com.develhope.spring.models.DTO.responseDTO.CourseScheduleResponseDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.models.ResponseInvalid;
import com.develhope.spring.models.ResponseValid;
import com.develhope.spring.services.CourseScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/course_schedule")
public class CourseScheduleController {


    @Autowired
    private CourseScheduleService courseScheduleService;

    Logger logger = LoggerFactory.getLogger(CourseScheduleController.class);

    @PostMapping
    public ResponseEntity<Response> postCourseSchedule(@RequestBody CourseScheduleRequestDTO courseSchedule) {
        try {
            CourseScheduleResponseDTO newCourseSchedule = courseScheduleService.addCourseSchedule(courseSchedule);
            logger.info("evento corso creato" + newCourseSchedule);
            return ResponseEntity.ok().body(
                    new ResponseValid(
                            200,
                            "New Course Schedule added correctly",
                            newCourseSchedule
                    ));
        } catch (CourseScheduleException | CourseException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    ));
        }
    }

    @GetMapping
    public ResponseEntity<Response> getCourseScheduleById() {
        try {
            List<CourseScheduleResponseDTO> courses = courseScheduleService.getAllCourseSchedule();
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "List of courses schedules: ",
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
    public ResponseEntity<Response> getCourseScheduleById(@PathVariable Long id) {
        try {
            CourseScheduleResponseDTO cs = courseScheduleService.getCourseScheduleById(id);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "Schedule found: ",
                            cs)
            );
        } catch (CourseScheduleException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            "Schedule not found, Id invalid"
                    )
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateCourseScheduleById(@PathVariable Long id, @RequestBody CourseScheduleRequestDTO courseScheduleDTO) {
        try {
            courseScheduleService.updateCourseScheduleById(id, courseScheduleDTO);
            return ResponseEntity.ok().body(new ResponseValid(200, "course schedule updated", courseScheduleDTO));
        } catch (CourseScheduleException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "course schedule id not found"));
        } catch (CourseException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "course id not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCourseScheduleById(@PathVariable Long id) {
        try {
            courseScheduleService.deleteCourseScheduleById(id);
            return ResponseEntity.ok().body(new Response(200, "course schedule deleted"));
        } catch (CourseScheduleException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "course schedule id not found"));
        }
    }
}
