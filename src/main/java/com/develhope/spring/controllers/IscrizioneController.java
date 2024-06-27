package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.IscrizioneException;
import com.develhope.spring.models.DTO.IscrizioneDTO;
import com.develhope.spring.models.DTO.requestDTO.UserRequestDTO;
import com.develhope.spring.models.DTO.responseDTO.UserResponseDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.models.ResponseInvalid;
import com.develhope.spring.models.ResponseValid;
import com.develhope.spring.services.IscrizioneService;
import com.develhope.spring.utilities.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/iscrizione")
public class IscrizioneController {
    @Autowired
    private IscrizioneService iscrizioneService;
    @Autowired
    private JWTUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(IscrizioneController.class);
    @PostMapping
    public ResponseEntity<Response> subscribe(@RequestParam Long courseId, @RequestHeader("Authorization") String authHeader){
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            IscrizioneDTO iscrizione = iscrizioneService.subscribeToCourse(courseId, username);
            logger.info("Iscrizione creata" + iscrizione);

            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "User " + iscrizione.getUser() + " subscribed to course " + iscrizione.getCourse(),
                            iscrizione)
            );
        }catch (IscrizioneException e){
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/a/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id){
        try {
            IscrizioneDTO iscrizione = iscrizioneService.getById(id);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "subscription found: ",
                            iscrizione)
            );
        }catch (IscrizioneException e){
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
    public ResponseEntity<Response> getAll(){
        try{
            List<IscrizioneDTO> iscrizioneDTOList = iscrizioneService.getAll();
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "subscription found: ",
                            iscrizioneDTOList));
        }catch (Exception e){
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @PatchMapping("/t/pay/{id}")
    public ResponseEntity<Response> paySwitch(@PathVariable Long id, @RequestHeader("Authorization") String authHeader){
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try{
            IscrizioneDTO iscrizione = iscrizioneService.payedSwitch(id, username);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "paying status changed: ",
                            iscrizione)
            );
        }catch (IscrizioneException e){
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }
    @DeleteMapping("/a/{id}")
    public ResponseEntity<Response> deleteSub(@PathVariable Long id){
        try{
            iscrizioneService.deleteSubscription(id);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "Subscription deleted"));
        }catch (IscrizioneException e){
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }
    @DeleteMapping("/me/{id}")
    public ResponseEntity<Response> deleteYourSub(@PathVariable Long id, @RequestHeader("Authorization") String authHeader){
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try{
            iscrizioneService.deleteYourSubscription(id, username);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "Subscription deleted"));
        }catch (IscrizioneException e){
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }
    @GetMapping("/t/tutor/{id}") // da valutare
    public ResponseEntity<Response> findByTutor(@PathVariable Long id){
        try{
            List<IscrizioneDTO> iscrizioneDTOList = iscrizioneService.getAllByTutor(id);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "subscription found: ",
                            iscrizioneDTOList));
        }catch (IscrizioneException e){
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/t/course/{id}")
    public ResponseEntity<Response> findUserByCourse(@PathVariable Long id){
        try{
            List<UserResponseDTO> userDTOList = iscrizioneService.getUserByCourse(id);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "subscription found: ",
                            userDTOList));
        }catch (IscrizioneException e){
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }
    @GetMapping("/sub/{id}")
    public ResponseEntity<Response> findCourseByUser(@PathVariable Long id){
        List<IscrizioneDTO> iscrizioneDTOList = iscrizioneService.getSubscribedCourse(id);
        return ResponseEntity.ok(
                new ResponseValid(
                        200,
                        "Iscrizioni",
                        iscrizioneDTOList));
    }
}
