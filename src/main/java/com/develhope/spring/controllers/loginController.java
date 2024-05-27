package com.develhope.spring.controllers;

import com.develhope.spring.entities.User;
import com.develhope.spring.models.LoginRequest;
import com.develhope.spring.models.LoginResponse;
import com.develhope.spring.models.UserDetailsImpl;
import com.develhope.spring.services.UserService;
import com.develhope.spring.utilities.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            Optional<User> user = userDetailsService.getUserByUsername(loginRequest.getUsername());
            if(user.isPresent() && Objects.equals(user.get().getPassword(), loginRequest.getPassword())) {
                User newUser = user.get();
                String jwt = jwtUtil.createToken(
                        new UserDetailsImpl(
                                newUser.getUsername(), newUser.getPassword()));
                return ResponseEntity.ok(
                        new LoginResponse(
                                200,
                        "TOKEN CREATED CORRECTLY",
                                jwt));
            }
            else {
                return ResponseEntity.status(400).body(
                        new LoginResponse(
                                400,
                                "IMPOSSIBLE TO CREATE A TOKEN")
                );
            }
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
    }
}
