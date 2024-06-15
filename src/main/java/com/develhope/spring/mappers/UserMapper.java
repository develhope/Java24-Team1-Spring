package com.develhope.spring.mappers;

import com.develhope.spring.entities.User;
import com.develhope.spring.models.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserDTO entityToDto(User u){
        return new UserDTO(
                u.getId(),
                u.getName(),
                u.getSurname(),
                u.getUsername(),
                u.getEmail(),
                u.getCellNum(),
                u.getFiscCode(),
                u.getRole(),
                u.getPassword()
        );
    }
    public User dtoToEntity(UserDTO u){
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
