package com.develhope.spring.services;

import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.User;
import com.develhope.spring.exceptions.RegisterException;
import com.develhope.spring.mappers.UserMapper;
import com.develhope.spring.models.DTO.UserDTO;
import com.develhope.spring.models.RegistrationDTO;
import com.develhope.spring.validators.RegistrationValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServices {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RegistrationValidator registrationValidator;

    public UserDTO saveUser(RegistrationDTO registrationDTO) throws RegisterException {
        if (registrationValidator.isRegistratioValid(registrationDTO)) {
            UserDTO user = mappingUserDTO(registrationDTO);
            User user1 = userMapper.dtoToEntity(user);
            User userSaved = userDAO.saveAndFlush(user1);
            return userMapper.entityToDto(userSaved);
        }
       else {
           throw new RegisterException("An errore  accurred with data", 400);
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
