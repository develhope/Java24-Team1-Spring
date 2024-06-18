package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.RegisterException;
import com.develhope.spring.models.DTO.requestDTO.UserRequestDTO;
import com.develhope.spring.models.DTO.responseDTO.UserResponseDTO;
import com.develhope.spring.models.RegistrationDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.models.ResponseInvalid;
import com.develhope.spring.models.ResponseValid;
import com.develhope.spring.services.RegisterServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private RegisterServices registerServices;
    Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        try {
            UserResponseDTO userDTO = registerServices.saveUser(registrationDTO);
            logger.info("Registrazione effettuata" + userDTO);
            return ResponseEntity.ok().body(
                    new ResponseValid(
                            200,
                            "User " + userDTO.getUsername() + " registered correctly!",
                            userDTO
                    )
            );
        }
        catch (RegisterException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

}
