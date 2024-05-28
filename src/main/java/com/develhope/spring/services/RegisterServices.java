package com.develhope.spring.services;

import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.User;
import com.develhope.spring.models.DTO.UserDTO;
import com.develhope.spring.models.RegistrationDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.validators.RegistrationValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegisterServices {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RegistrationValidator registrationValidator;

    public ResponseEntity<Response> saveUser(RegistrationDTO registrationDTO) {
        if (registrationValidator.isRegistratioValid(registrationDTO)) {
            UserDTO user = mappingUserDTO(registrationDTO);
            User user1 = modelMapper.map(user, User.class);
            User userSaved = userDAO.saveAndFlush(user1);
            UserDTO userDTO = modelMapper.map(userSaved, UserDTO.class);
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "Registered successfully",
                            userDTO
                    )
            );
        }
        else {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "PROBLEM ACCURRED WITH DATA"
                    )
            );
        }
    }

    private UserDTO mappingUserDTO(RegistrationDTO registrationDTO) {
        return new UserDTO(
                registrationDTO.getName(),
                registrationDTO.getSurname(),
                registrationDTO.getUsername(),
                registrationDTO.getEmail(),
                registrationDTO.getCellNum(),
                registrationDTO.getFiscCode(),
                registrationDTO.getRole(),
                registrationDTO.getPassword()
        );
    }
}
