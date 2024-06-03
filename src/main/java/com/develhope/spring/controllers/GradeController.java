package com.develhope.spring.controllers;

import com.develhope.spring.entities.Grade;
import com.develhope.spring.exceptions.GradeException;
import com.develhope.spring.exceptions.ReviewException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.GradeDTO;
import com.develhope.spring.models.DTO.ReviewDTO;
import com.develhope.spring.models.DTO.UserDTO;
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
    public List<GradeDTO> getAllGrade(){
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
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateGradeById(@PathVariable Long id, @RequestBody GradeDTO gradeDTO){
        try{
            gradeService.updateGradeById(id, gradeDTO);
            return ResponseEntity.ok().body(new Response(200, "grade updated",gradeDTO));
        }catch(GradeException e){
            return ResponseEntity.status(404).body(new Response(404, "grade id not found"));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteGradeById(@PathVariable Long id){
        try{
            gradeService.deleteGradeById(id);
            return ResponseEntity.ok().body(new Response(200, "grade deleted"));
        }catch (GradeException e){
            return  ResponseEntity.status(404).body(new Response(404, "grade id not found"));
        }
    }
    @DeleteMapping("/list")
    public void deleteAllGrades(){
        gradeService.deleteAllGrades();
    }
    @GetMapping("/tutor{id}")
    public ResponseEntity<Response> getGradesByTutor(@PathVariable Long id){
        try {
            List<GradeDTO> grades = gradeService.getGradeByTutor(id);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "grades found: ",
                            grades)
            );
        } catch (GradeException e) {
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            e.getMessage()
                    )
            );
        }
    }
}
