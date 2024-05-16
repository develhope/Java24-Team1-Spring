package com.develhope.spring.controllers;

import com.develhope.spring.entities.Grade;
import com.develhope.spring.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping("/add")
    public void postGrade(@RequestBody Grade grade) {
        gradeService.addGrade(grade);
    }
}
