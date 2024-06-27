package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.GradeException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.GradeDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.models.ResponseInvalid;
import com.develhope.spring.models.ResponseValid;
import com.develhope.spring.services.GradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.develhope.spring.utilities.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;
    @Autowired
    private JWTUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(GradeController.class);


    @PostMapping("/t")
    public ResponseEntity<Response> addGrade(@RequestBody GradeDTO grade, @RequestHeader("Authorization") String authHeader){
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            GradeDTO newGrade = gradeService.addGrade(grade, username);
            logger.info("Voto inserito" + newGrade);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            " added correctly",
                            newGrade)
            );
        }catch (GradeException | CourseException | UserException e){
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/a")
    public ResponseEntity<Response> getAllGrade(){
        try {
            List<GradeDTO> grades = gradeService.getAllGrade();
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "List of grades: ",
                            grades)
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
    public ResponseEntity<Response> findGradeById (@PathVariable Long id, @RequestHeader("Authorization") String authHeader){
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            GradeDTO g = gradeService.getGradeById(id,username);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "Grade found: ",
                            g)
            );
        }catch (GradeException e){
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @PutMapping("/t/{id}")
    public ResponseEntity<Response> updateGradeById(@PathVariable Long id, @RequestBody GradeDTO gradeDTO, @RequestHeader("Authorization") String authHeader){
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try{
            gradeService.updateGradeById(id, gradeDTO, username);
            return ResponseEntity.ok().body(new ResponseValid(200, "grade updated",gradeDTO));
        }catch(GradeException e){
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @DeleteMapping("/a/{id}")
    public ResponseEntity<Response> deleteGradeById(@PathVariable Long id){
        try{
            gradeService.deleteGradeById(id);
            return ResponseEntity.ok().body(new Response(200, "grade deleted"));
        }catch (GradeException e){
            logger.error("errore " + e.getMessage());
            return  ResponseEntity.status(400).body(new ResponseInvalid(400, "grade id not found"));
        }
    }

    @DeleteMapping("/t/{id}")
    public ResponseEntity<Response> deleteYourGradeById(@PathVariable Long id, @RequestHeader("Authorization") String authHeader){
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try{
            gradeService.deleteYourGradeById(id, username);
            return ResponseEntity.ok().body(new Response(200, "grade deleted"));
        }catch (GradeException e){
            return  ResponseEntity.status(400).body(new Response(400, "grade id not found"));
        }
    }

    @GetMapping("/t/tutor/me")
    public ResponseEntity<Response> getGradesByTutor(@RequestHeader("Authorization") String authHeader){
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            List<GradeDTO> grades = gradeService.getGradeByTutor(username);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "grades found: ",
                            grades)
            );
        } catch (GradeException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }
    @GetMapping("/student/me")
    public ResponseEntity<Response> getAllStudentsGrades(@RequestHeader("Authorization") String authHeader){
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        List<GradeDTO> grades = gradeService.getAllStudentsGrades(username);
        return ResponseEntity.ok().body(
                new ResponseValid(200,
                        "List of grades for students: ",
                        grades)
        );
    }
}
