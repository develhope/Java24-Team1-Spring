package com.develhope.spring.services;

import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.User;
import com.develhope.spring.exceptions.RegisterException;
import com.develhope.spring.mappers.UserMapper;
import com.develhope.spring.models.DTO.requestDTO.UserRequestDTO;
import com.develhope.spring.models.DTO.responseDTO.UserResponseDTO;
import com.develhope.spring.models.RegistrationDTO;
import com.develhope.spring.validators.RegistrationValidator;
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

    public UserResponseDTO saveUser(RegistrationDTO registrationDTO) throws RegisterException {
        if (registrationValidator.isRegistratioValid(registrationDTO)) {
            UserRequestDTO user = mappingUserDTO(registrationDTO);
            User user1 = userMapper.dtoToEntity(user);
            User userSaved = userDAO.saveAndFlush(user1);
            return userMapper.entityToDto(userSaved);
        }
       else {
           throw new RegisterException("An errore  accurred with data", 400);
        }
    }

    private UserRequestDTO mappingUserDTO(RegistrationDTO registrationDTO) {
        return new UserRequestDTO(
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
