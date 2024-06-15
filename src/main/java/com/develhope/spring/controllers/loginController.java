package com.develhope.spring.controllers;

import com.develhope.spring.entities.User;
import com.develhope.spring.models.LoginRequest;
import com.develhope.spring.models.LoginResponse;
import com.develhope.spring.models.UserDetailsImpl;
import com.develhope.spring.services.UserService;
import com.develhope.spring.utilities.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
public class loginController {
    @Autowired
    private UserService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws BadCredentialsException {
        try {
            User user = userDetailsService.getUserByUsername(authenticationRequest.getUsername());
            if (user != null && passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
                final String jwt = jwtUtil.createToken(new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getRole()));
                return ResponseEntity.ok().body(new LoginResponse(jwt, 200, "Token created successfully!"));
            }

        } catch (BadCredentialsException e) {
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
