package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.CourseDTO;
import com.develhope.spring.models.DTO.UserDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

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

    @GetMapping("/{id}")
    public ResponseEntity<Response> findUserById(@PathVariable Long id) {
        try {
            UserDTO user = userService.getUserById(id);
            return ResponseEntity.ok().body(new Response(200, "user found", user));
        } catch (UserException e) {
            return ResponseEntity.status(400).body(new Response(400, "user not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUserById(@PathVariable Long id){
           try{
               userService.deleteUserById(id);
                return ResponseEntity.ok().body(new Response(200, "user deleted"));
            }catch (UserException e){
                return  ResponseEntity.status(400).body(new Response(400, "user id not found"));
            }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateUserById(@PathVariable Long id, @RequestBody UserDTO userDTO){
        try{
           userService.updateUserById(id, userDTO);
           return ResponseEntity.ok().body(new Response(200, "user updated",userDTO));
        } catch(UserException e){
            return ResponseEntity.status(400).body(new Response(400, "user id not found"));
        }
    }
}
