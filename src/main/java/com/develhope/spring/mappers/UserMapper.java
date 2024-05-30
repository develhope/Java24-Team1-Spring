package com.develhope.spring.mappers;

import com.develhope.spring.entities.User;
import com.develhope.spring.models.DTO.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User dtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setCellNum(userDTO.getCellNum());
        user.setFiscCode(userDTO.getFiscCode());
        user.setRole(userDTO.getRole());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public UserDTO entityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCellNum(user.getCellNum());
        userDTO.setFiscCode(user.getFiscCode());
        userDTO.setRole(user.getRole());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
