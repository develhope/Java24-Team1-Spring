package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.GradeException;
import com.develhope.spring.exceptions.IscrizioneException;
import com.develhope.spring.models.DTO.GradeDTO;
import com.develhope.spring.models.DTO.IscrizioneDTO;
import com.develhope.spring.models.DTO.UserDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.IscrizioneService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/iscrizione")
public class IscrizioneController {
    @Autowired
    private IscrizioneService iscrizioneService;
    @PostMapping
    public ResponseEntity<Response> subscribe(@RequestParam Long userId, @RequestParam Long courseId){
        try {
            IscrizioneDTO iscrizione = iscrizioneService.subscribeToCourse(userId,courseId);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "User " + iscrizione.getUser().getName() + " subscribed to course " + iscrizione.getCourse().getName(),
                            iscrizione)
            );
        }catch (IscrizioneException e){
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id){
        try {
            IscrizioneDTO iscrizione = iscrizioneService.getById(id);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "subscription found: ",
                            iscrizione)
            );
        }catch (IscrizioneException e){
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(){
        try{
            List<IscrizioneDTO> iscrizioneDTOList = iscrizioneService.getAll();
            return ResponseEntity.ok().body(
                    new Response(200,
                            "subscription found: ",
                            iscrizioneDTOList));
        }catch (IscrizioneException e){
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            e.getMessage()
                    )
            );
        }
    }

    @PatchMapping("/pay{id}")
    public ResponseEntity<Response> paySwitch(@PathVariable Long id){
        try{
            IscrizioneDTO iscrizione = iscrizioneService.payedSwitch(id);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "paying status changed: ",
                            iscrizione)
            );
        }catch (IscrizioneException e){
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            e.getMessage()
                    )
            );
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteSub(@PathVariable Long id){
        try{
            iscrizioneService.deleteSubscription(id);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "Subscription deleted"));
        }catch (IscrizioneException e){
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            e.getMessage()
                    )
            );
        }
    }
    @GetMapping("/tutor{id}") // da valutare
    public ResponseEntity<Response> findByTutor(@PathVariable Long id){
        try{
            List<IscrizioneDTO> iscrizioneDTOList = iscrizioneService.getAll();
            return ResponseEntity.ok().body(
                    new Response(200,
                            "subscription found: ",
                            iscrizioneDTOList));
        }catch (IscrizioneException e){
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/course{id}")
    public ResponseEntity<Response> findUserByCourse(@PathVariable Long id){
        try{
            List<UserDTO> userDTOList = iscrizioneService.getUserByCourse(id);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "subscription found: ",
                            userDTOList));
        }catch (IscrizioneException e){
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            e.getMessage()
                    )
            );
        }
    }
}
