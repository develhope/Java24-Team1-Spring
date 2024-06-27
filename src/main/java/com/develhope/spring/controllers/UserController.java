package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.requestDTO.UserRequestDTO;
import com.develhope.spring.models.DTO.responseDTO.UserResponseDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.models.ResponseInvalid;
import com.develhope.spring.models.ResponseValid;
import com.develhope.spring.services.UserService;
import com.develhope.spring.utilities.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping
    public ResponseEntity<Response> postUser(@RequestBody UserRequestDTO user) {
        try {
            UserResponseDTO newUser = userService.addUser(user);
            logger.info("User inserito "+newUser);
            return ResponseEntity.ok().body(
                    new ResponseValid(
                            200,
                            newUser.getUsername() + " added correctly",
                            newUser)
            );
        } catch (UserException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping
    public ResponseEntity<Response> getAllUsers() {
        try {
            List<UserResponseDTO> users = userService.getAllUsers();
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "List of users: ",
                            users)
            );
        } catch (Exception e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
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
            UserResponseDTO user = userService.getUserByUsername(username);
            return ResponseEntity.ok().body(new ResponseValid(200, "user found", user));
        } catch (UserException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "user not found"));
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
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "user id not found"));
        }
    }

    @PutMapping("/me")
    public ResponseEntity<Response> updateUserById(@RequestHeader("Authorization") String authHeader, @RequestBody UserRequestDTO userDTO) {
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            userService.updateUserByUsername(username, userDTO);
            return ResponseEntity.ok().body(new ResponseValid(200, "user updated", userDTO));
        } catch (UserException e) {
            logger.error("errore " + e.getMessage());

            return ResponseEntity.status(400).body(new ResponseInvalid(400, "user id not found"));
        }
    }
}
