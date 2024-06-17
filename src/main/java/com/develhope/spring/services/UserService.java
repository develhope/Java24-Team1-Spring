package com.develhope.spring.services;

import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.Review;
import com.develhope.spring.entities.User;
import com.develhope.spring.exceptions.ReviewException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.mappers.UserMapper;
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
    private UserMapper userMapper;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserValidator validator;

    public UserDTO addUser(UserDTO user) throws UserException {
        System.out.println(validator.isUserValid(user));
        if (validator.isUserValid(user)) {
            System.out.println(validator.isUserValid(user));
            User entity = userMapper.dtoToEntity(user);
            User saved = userDAO.saveAndFlush(entity);
            return userMapper.entityToDto(saved);
        } else {
            throw new UserException("User not added, a problem occurred with the data", 400);
        }
    }

    public List<UserDTO> getAllUsers() {
        List <User> users = userDAO.findActiveUser();
        List<UserDTO> usersDTOList = new ArrayList<>();
        for(User user : users){
            UserDTO userDTO = userMapper.entityToDto(user);
            usersDTOList.add(userDTO);
        }
        return usersDTOList;
    }

    public UserDTO getUserById(Long id) throws UserException {
        User user = userDAO.findById(id).orElseThrow(() -> new UserException("This user does not exist!", 400));
        if (user != null) {
            return userMapper.entityToDto(user);
        } else {
            throw new UserException("This user does not exist!", 400);
        }
    }


    public UserDTO updateUserById(Long id, UserDTO userDTO) throws UserException {
        User optionalUser = userDAO.findById(id).orElseThrow(() -> new UserException("This user does not exist!", 400));
        if (optionalUser != null) {
            optionalUser.setName(userDTO.getName());
            optionalUser.setSurname(userDTO.getSurname());
            optionalUser.setUsername(userDTO.getUsername());
            optionalUser.setEmail(userDTO.getEmail());
            optionalUser.setCellNum(userDTO.getCellNum());
            optionalUser.setFiscCode(userDTO.getFiscCode());
            optionalUser.setRole(userDTO.getRole());
            optionalUser.setPassword(userDTO.getPassword());
            User userEdited = userDAO.saveAndFlush(optionalUser);
            return userMapper.entityToDto(userEdited);
        } else {
            throw new UserException("This user does not exist!", 400);
        }
    }

    public void deleteUserById(Long id) throws UserException {
        User user = userDAO.findById(id).orElseThrow(() -> new UserException("This review does not exist!", 400));
        if(!user.getIsDeleted()) {
            user.setIsDeleted(true);
            userDAO.saveAndFlush(user);
        }else{
            throw  new UserException("This user does not exist!", 400);
        }
    }


    public User getUserByUsername(String username){
        Optional<User> user = userDAO.findByUsername(username);

        if(user.isPresent()) {
            return user.get();
        }
        throw new BadCredentialsException("");
    }
  
}
