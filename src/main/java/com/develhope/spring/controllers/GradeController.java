package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.GradeException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.GradeDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response> postGrade(@RequestBody GradeDTO grade) {
        try {
            GradeDTO newGrade = gradeService.addGrade(grade);
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "Grade to student: " + newGrade.getStudent_id() +  " added correctly",
                            newGrade)
            );
        } catch (GradeException e) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }
}
