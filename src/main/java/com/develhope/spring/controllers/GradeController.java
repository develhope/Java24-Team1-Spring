package com.develhope.spring.controllers;

import com.develhope.spring.entities.Grade;
import com.develhope.spring.exceptions.GradeException;
import com.develhope.spring.models.DTO.GradeDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping("/")
    public ResponseEntity<Response> addGrade(@RequestBody GradeDTO grade){
        try {
            GradeDTO newGrade = gradeService.addGrade(grade);
            return ResponseEntity.ok().body(
                    new Response(200,
                            " added correctly",
                            newGrade)
            );
        }catch (GradeException e){
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }
    @GetMapping("/list")
    public List<Grade> getAllGrade(){
        return gradeService.getAllGrade();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> findGradeById (@PathVariable Long id){
        Optional<Grade> g = gradeService.getGradeById(id);
        if(g.isPresent()){
            return ResponseEntity.ok().body(
                    new Response(200,
                            "Grade found: ",
                            g)
            );
        }else{
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            "Grade not found, Id invalid"
                    )
            );
        }
    }
}
