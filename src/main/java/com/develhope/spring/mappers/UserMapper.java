package com.develhope.spring.mappers;

import com.develhope.spring.entities.User;
import com.develhope.spring.models.DTO.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO entityToDTO(User u){
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
                u.getPassword()
        );
    }
}