package com.develhope.spring.controllers;

import com.develhope.spring.entities.User;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.responseDTO.UserResponseDTO;
import com.develhope.spring.models.DTO.requestDTO.UserRequestDTO;
import com.develhope.spring.models.LoginRequest;
import com.develhope.spring.models.LoginResponse;
import com.develhope.spring.models.UserDetailsImpl;
import com.develhope.spring.services.UserService;
import com.develhope.spring.utilities.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) {
        try {
            User user = userDetailsService.getUserByUsernameLogin(authenticationRequest.getUsername());
            if (user != null && passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
                final String jwt = jwtUtil.createToken(new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getRole()));
                return ResponseEntity.ok().body(new LoginResponse(jwt, 200, "Token created successfully!"));
            }

        } catch (UserException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new LoginResponse(
                            400,
                            "Impossible to create token. Wrong credentials!"
                    )
            );
        }
        return null;
    }
}
