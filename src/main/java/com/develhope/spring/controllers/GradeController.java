package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.GradeException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.GradeDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.GradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    Logger logger = LoggerFactory.getLogger(GradeController.class);

    @PostMapping
    public ResponseEntity<Response> addGrade(@RequestBody GradeDTO grade){
        try {
            GradeDTO newGrade = gradeService.addGrade(grade);
            logger.info("Voto inserito" + newGrade);
            return ResponseEntity.ok().body(
                    new Response(200,
                            " added correctly",
                            newGrade)
            );
        }catch (GradeException | CourseException | UserException e){
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }
    @GetMapping
    public ResponseEntity<Response> getAllGrade(){
        try {
            List<GradeDTO> grades = gradeService.getAllGrade();
            return ResponseEntity.ok().body(
                    new Response(200,
                            "List of grades: ",
                            grades)
            );
        } catch (Exception e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> findGradeById (@PathVariable Long id){
        try {
            GradeDTO g = gradeService.getGradeById(id);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "Grade found: ",
                            g)
            );
        }catch (GradeException e){
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
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
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(new Response(400, "grade id not found"));
        } catch (CourseException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(new Response(400, "course id not found"));
        } catch (UserException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(new Response(400, "user id not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteGradeById(@PathVariable Long id){
        try{
            gradeService.deleteGradeById(id);
            return ResponseEntity.ok().body(new Response(200, "grade deleted"));
        }catch (GradeException e){
            logger.error("errore " + e.getMessage());
            return  ResponseEntity.status(400).body(new Response(400, "grade id not found"));
        }
    }

    @GetMapping("/tutor/{id}")
    public ResponseEntity<Response> getGradesByTutor(@PathVariable Long id){
        try {
            List<GradeDTO> grades = gradeService.getGradeByTutor(id);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "grades found: ",
                            grades)
            );
        } catch (GradeException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<Response> getAllStudentsGrades(@PathVariable Long id){
        List<GradeDTO> grades = gradeService.getAllStudentsGrades(id);
        return ResponseEntity.ok().body(
                new Response(200,
                        "List of grades for students: ",
                        grades)
        );
    }
}
