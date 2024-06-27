package com.develhope.spring.services;

import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.User;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.mappers.UserMapper;
import com.develhope.spring.models.DTO.requestDTO.UserRequestDTO;
import com.develhope.spring.models.DTO.responseDTO.UserResponseDTO;
import com.develhope.spring.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserResponseDTO addUser(UserRequestDTO user) throws UserException {
        if (validator.isUserValid(user)) {
            System.out.println(validator.isUserValid(user));
            User entity = userMapper.dtoToEntity(user);
            User saved = userDAO.saveAndFlush(entity);
            return userMapper.entityToDto(saved);
        } else {
            throw new UserException("User not added, a problem occurred with the data", 400);
        }
    }

    public List<UserResponseDTO> getAllUsers() {
        List <User> users = userDAO.findActiveUser();
        List<UserResponseDTO> usersDTOList = new ArrayList<>();
        for(User user : users){
            UserResponseDTO userDTO = userMapper.entityToDto(user);
            usersDTOList.add(userDTO);
        }
        return usersDTOList;
    }

    public UserResponseDTO getUserById(Long id) throws UserException {
        User user = userDAO.findById(id).orElseThrow(() -> new UserException("This user does not exist!", 400));
        if (user != null) {
            return userMapper.entityToDto(user);
        } else {
            throw new UserException("This user does not exist!", 400);
        }
    }


    public UserResponseDTO updateUserById(Long id, UserRequestDTO userDTO) throws UserException {
        User optionalUser = userDAO.findById(id).orElseThrow(() -> new UserException("This user does not exist!", 400));
        if (optionalUser != null) {
            optionalUser.setName(userDTO.getName());
            optionalUser.setSurname(userDTO.getSurname());
            optionalUser.setUsername(userDTO.getUsername());
            optionalUser.setEmail(userDTO.getEmail());
            optionalUser.setCellNum(userDTO.getCellNum());
            optionalUser.setFiscCode(userDTO.getFiscCode());
            optionalUser.setRole(userDTO.getRole());
            String encPass = passwordEncoder.encode(userDTO.getPassword());
            optionalUser.setPassword(encPass);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + encPass);

            User userEdited = userDAO.saveAndFlush(optionalUser);
            return userMapper.entityToDto(userEdited);
        } else {
            throw new UserException("This user does not exist!", 400);
        }
    }

    public void deleteUserById(Long id) throws UserException {
        User user = userDAO.findById(id).orElseThrow(() -> new UserException("This user does not exist!", 400));
        if(!user.getIsDeleted()) {
            user.setIsDeleted(true);
            userDAO.saveAndFlush(user);
        }else{
            throw  new UserException("This user does not exist!", 400);
        }
    }

    public void deleteUserByUsername(String username) throws UserException {
        User user = userDAO.findByUsername(username).orElseThrow(() -> new UserException("This user does not exist!", 400));
        if(!user.getIsDeleted()) {
            user.setIsDeleted(true);
            userDAO.saveAndFlush(user);
        }else{
            throw  new UserException("This user does not exist!", 400);
        }
    }


    public UserResponseDTO getUserByUsername(String username) throws UserException {
        User user = userDAO.findByUsername(username).orElseThrow(() -> new UserException("User not found", 400));
        return userMapper.entityToDto(user);
    }

    public User getUserByUsernameLogin(String username) throws UserException {
        return userDAO.findByUsername(username).orElseThrow(() -> new UserException("User not found", 400));
    }

    public UserResponseDTO updateUserByUsername(String username, UserRequestDTO userDTO) throws UserException {
        User optionalUser = userDAO.findByUsername(username).orElseThrow(() -> new UserException("This user does not exist!", 400));
        if (optionalUser != null) {
            optionalUser.setName(userDTO.getName());
            optionalUser.setSurname(userDTO.getSurname());
            optionalUser.setUsername(userDTO.getUsername());
            optionalUser.setEmail(userDTO.getEmail());
            optionalUser.setCellNum(userDTO.getCellNum());
            optionalUser.setFiscCode(userDTO.getFiscCode());
            optionalUser.setRole(userDTO.getRole());
            optionalUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            User userEdited = userDAO.saveAndFlush(optionalUser);
            return userMapper.entityToDto(userEdited);
        } else {
            throw new UserException("This user does not exist!", 400);
        }
    }
}
