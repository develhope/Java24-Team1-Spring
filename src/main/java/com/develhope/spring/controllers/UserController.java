package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.CourseDTO;
import com.develhope.spring.models.DTO.UserDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.UserService;
import com.develhope.spring.utilities.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Response> postUser(@RequestBody UserDTO user) {
        try {
            UserDTO newUser = userService.addUser(user);
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            newUser.getUsername() + " added correctly",
                            newUser)
            );
        } catch (UserException e) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping
    public ResponseEntity<Response> getAllUsers() {
        try {
            List<UserDTO> users = userService.getAllUsers();
            return ResponseEntity.ok().body(
                    new Response(200,
                            "List of users: ",
                            users)
            );
        } catch (Exception e) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Response> findUserById(@RequestHeader("Authorization") String authHeader) {
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            UserDTO user = userService.getUserByUsername(username);
            return ResponseEntity.ok().body(new Response(200, "user found", user));
        } catch (UserException e) {
            return ResponseEntity.status(400).body(new Response(400, "user not found"));
        }
    }

    @DeleteMapping("/me")
    public ResponseEntity<Response> deleteYourUser(@RequestHeader("Authorization") String authHeader) {
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            userService.deleteUserByUsername(username);
            return ResponseEntity.ok().body(new Response(200, "user deleted"));
        } catch (UserException e) {
            return ResponseEntity.status(400).body(new Response(400, "user id not found"));
        }
    }

    @PutMapping("/me")
    public ResponseEntity<Response> updateUserById(@RequestHeader("Authorization") String authHeader, @RequestBody UserDTO userDTO) {
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            userService.updateUserByUsername(username, userDTO);
            return ResponseEntity.ok().body(new Response(200, "user updated", userDTO));
        } catch (UserException e) {
            return ResponseEntity.status(400).body(new Response(400, "user id not found"));
        }
    }
}
