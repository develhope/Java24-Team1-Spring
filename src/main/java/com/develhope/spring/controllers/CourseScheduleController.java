package com.develhope.spring.controllers;

import com.develhope.spring.entities.CourseSchedule;
import com.develhope.spring.entities.Grade;
import com.develhope.spring.exceptions.CourseScheduleException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.CourseScheduleDTO;
import com.develhope.spring.models.DTO.UserDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.CourseScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/course_schedule")
public class CourseScheduleController {


    @Autowired
    private CourseScheduleService courseScheduleService;

    @PostMapping("/")
    public ResponseEntity<Response> postCourseSchedule(@RequestBody CourseScheduleDTO courseSchedule) {
        try {
            CourseScheduleDTO newCourseSchedule = courseScheduleService.addCourseSchedule(courseSchedule);
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "New Course Schedule added correctly",
                            newCourseSchedule
                    ));
        } catch (CourseScheduleException e) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    ));
        }
    }

    @GetMapping("/list")
    public List<CourseScheduleDTO> getCourseScheduleById() {
        return courseScheduleService.getAllCourseSchedule();
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
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            "Schedule not found, Id invalid"
                    )
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateCourseScheduleById(@PathVariable Long id, @RequestBody CourseScheduleDTO courseScheduleDTO){
        try{
            courseScheduleService.updateCourseScheduleById(id, courseScheduleDTO);
            return ResponseEntity.ok().body(new Response(200, "course schedule updated",courseScheduleDTO));
        }catch(CourseScheduleException e){
            return ResponseEntity.status(404).body(new Response(404, "course schedule id not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCourseScheduleById(@PathVariable Long id) {
        try {
            courseScheduleService.deleteCourseScheduleById(id);
            return ResponseEntity.ok().body(new Response(200, "course schedule deleted"));
        } catch (CourseScheduleException e) {
            return ResponseEntity.status(404).body(new Response(404, "course schedule id not found"));
        }
    }

    @DeleteMapping("/all")
    public void deleteAllCourseSchedules() {
        courseScheduleService.deleteAllCourseSchedules();
    }
}
