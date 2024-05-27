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

import java.util.Optional;

@RestController
public class loginController {
    @Autowired
    private UserService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {
        try {
            Optional<User> user = userDetailsService.getUserByUsername(authenticationRequest.getUsername());
            if(user.isPresent()) {
                final String jwt = jwtUtil.createToken(new UserDetailsImpl(user.get().getUsername(), user.get().getPassword()));
                return ResponseEntity.ok(new LoginResponse(jwt));
            }
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        return null;
    }
}
