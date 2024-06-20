package com.develhope.spring.seeding;


import com.develhope.spring.models.Response;
import org.modelmapper.spi.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@RestController
@RequestMapping("api/seeding")
public class SeedingController {
    private SeedingService seedingService;

    Logger logger = LoggerFactory.getLogger(SeedingController.class);
    @Autowired
    public SeedingController(SeedingService seedingService){


        this.seedingService = seedingService;
}
    @PostMapping("/init")
    public ResponseEntity<Response> initDatabaseData(@RequestBody SeedingObjectDto s){
        if(s == null) return ResponseEntity.badRequest().body(new Response(400, SeedingMessages.SEEDING_DATA_NOT_VALID));
        if(!s.getKey().equals(StaticSeedingData.SEED_KEY)) return ResponseEntity.status(403).body(new Response(403, SeedingMessages.SEEDING_DATA_NOT_VALID));
        this.seedingService.initDatabase();
        logger.info("Database inizializzato ");
        return ResponseEntity.ok(new Response(200, SeedingMessages.SEEDING_DONE));
    }

    @PostMapping("/clean")
    public ResponseEntity<Response> cleanDatabase(@RequestBody SeedingObjectDto s){
        if(s == null) return ResponseEntity.badRequest().body
                (new Response(400, SeedingMessages.SEEDING_DATA_NOT_VALID));
        if(!s.getKey().equals(StaticSeedingData.SEED_KEY))
            return ResponseEntity.status(403).body(new Response
                    (403, SeedingMessages.SEEDING_DATA_NOT_VALID));
        this.seedingService.cleanDatabase();
        logger.info("Database pulito ");
        return ResponseEntity.ok(new Response(200, SeedingMessages.CLEAN_DATABASE));
    }

}


