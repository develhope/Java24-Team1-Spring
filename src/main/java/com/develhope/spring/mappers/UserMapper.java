package com.develhope.spring.mappers;

import com.develhope.spring.entities.User;
import com.develhope.spring.models.DTO.requestDTO.UserRequestDTO;
import com.develhope.spring.models.DTO.responseDTO.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserResponseDTO entityToDto(User u){
        return new UserResponseDTO(
                u.getId(),
                u.getName(),
                u.getSurname(),
                u.getUsername(),
                u.getEmail(),
                u.getCellNum(),
                u.getFiscCode(),
                u.getRole()
        );
    }
    public User dtoToEntity(UserRequestDTO u){
        return new User(
                u.getId(),
                u.getName(),
                u.getSurname(),
                u.getUsername(),
                u.getEmail(),
                u.getCellNum(),
                u.getFiscCode(),
                u.getRole(),
                passwordEncoder.encode(u.getPassword())
        );
    }
}
