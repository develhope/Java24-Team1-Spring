package com.develhope.spring.services;

import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.User;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.UserDTO;
import com.develhope.spring.validators.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<UserDTO> getAllUsers() {
        List <User> users = userDAO.findAll();
        List<UserDTO> usersDTOList = new ArrayList<>();
        for(User user : users){
            UserDTO userDTO = modelMapper.map(user,UserDTO.class);
            usersDTOList.add(userDTO);
        }
        return usersDTOList;
    }

    public Optional<User> getUserById(Long id) {
        return userDAO.findById(id);
    }


    public UserDTO updateUserById(Long id, UserDTO userDTO) throws UserException {
        User optionalUser = userDAO.findById(id).orElse(null);
            optionalUser.setName(userDTO.getName());
            optionalUser.setSurname(userDTO.getSurname());
            optionalUser.setUsername(userDTO.getUsername());
            optionalUser.setEmail(userDTO.getEmail());
            optionalUser.setCellNum(userDTO.getCellNum());
            optionalUser.setFiscCode(userDTO.getFiscCode());
            optionalUser.setRole(userDTO.getRole());
            optionalUser.setPassword(userDTO.getPassword());
            User userEdited = userDAO.saveAndFlush(optionalUser);
             modelMapper.map(userEdited, userDTO);
        return userDTO;
    }

    public void deleteUserById(Long id) throws UserException {
        if(userDAO.existsById(id)) {
            userDAO.deleteById(id);
        }else{
            throw  new UserException("user id not found", 404);
        }
    }

    public void deleteAllUsers() {
        userDAO.deleteAll();
      
    }

    public User getUserByUsername(String username){
        Optional<User> user = userDAO.findByUsername(username);

        if(user.isPresent()) {
            return user.get();
        }
        throw new BadCredentialsException("");
    }
}
