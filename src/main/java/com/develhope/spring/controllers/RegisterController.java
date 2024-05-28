package com.develhope.spring.controllers;

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
        return registerServices.saveUser(registrationDTO);
    }

}
