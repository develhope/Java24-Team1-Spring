package com.develhope.spring.services;

import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.User;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserValidator validator;

    public User addUser(User user) throws UserException {
        if (validator.isUserValid(user)) {
            return userDAO.saveAndFlush(user);
        } else {
            throw new UserException("User not added, a problem occurred with the data", 400);
        }

    }
}
