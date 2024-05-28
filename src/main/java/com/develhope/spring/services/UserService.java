package com.develhope.spring.services;

import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.User;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.UserDTO;
import com.develhope.spring.validators.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserValidator validator;

    public UserDTO addUser(UserDTO user) throws UserException {
        System.out.println(validator.isUserValid(user));
        if (validator.isUserValid(user)) {
            System.out.println(validator.isUserValid(user));
            User entity = modelMapper.map(user, User.class);
            User saved = userDAO.saveAndFlush(entity);
            modelMapper.map(saved, user);
            return user;
        } else {
            throw new UserException("User not added, a problem occurred with the data", 400);
        }
    }
    public List<User> getAllUsers(){
        return userDAO.findAll();
    }
    public Optional<User> getUserById(Long id){
        return userDAO.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
