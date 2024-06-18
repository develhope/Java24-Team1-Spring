package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.RegisterException;
import com.develhope.spring.models.DTO.requestDTO.UserRequestDTO;
import com.develhope.spring.models.DTO.responseDTO.UserResponseDTO;
import com.develhope.spring.models.RegistrationDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.RegisterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private RegisterServices registerServices;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        try {
            UserResponseDTO userDTO = registerServices.saveUser(registrationDTO);
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "User " + userDTO.getUsername() + " registered correctly!",
                            userDTO
                    )
            );
        }
        catch (RegisterException e) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

}
