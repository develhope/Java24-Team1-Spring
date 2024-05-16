package com.develhope.spring.controllers;

import com.develhope.spring.services.CourseScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course_schedule")
public class CourseScheduleController {

    @Autowired
    private CourseScheduleService courseScheduleService;
}
