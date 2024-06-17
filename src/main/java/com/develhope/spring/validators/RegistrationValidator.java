package com.develhope.spring.validators;

import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.User;
import com.develhope.spring.models.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class RegistrationValidator {

    @Autowired
    private UserDAO userDAO;

    public boolean isRegistratioValid(RegistrationDTO registrationDTO) {
        return isRegistrationNotNull(registrationDTO) &&
                isPasswordsEquals(registrationDTO) &&
                isUsernameGreaterThanFive(registrationDTO) &&
                isPasswordGreaterThenEigth(registrationDTO) &&
                isEmailValid(registrationDTO) &&
                isCellNumValid(registrationDTO) &&
                isCfValid(registrationDTO) &&
                isAlredyPresent(registrationDTO);
    }

    private boolean isRegistrationNotNull(RegistrationDTO registrationDTO) {
        return registrationDTO.getUsername() != null &&
                registrationDTO.getPassword() != null &&
                registrationDTO.getRepeatPassword() != null;
    }

    private boolean isPasswordsEquals(RegistrationDTO registrationDTO) {
        return registrationDTO.getPassword().equals(registrationDTO.getRepeatPassword());
    }

    private boolean isUsernameGreaterThanFive(RegistrationDTO registrationDTO) {
        return registrationDTO.getUsername().length() >= 5;
    }

    private boolean isPasswordGreaterThenEigth(RegistrationDTO registrationDTO) {
        return registrationDTO.getPassword().length() >= 8;
    }

    private boolean isEmailValid(RegistrationDTO registrationDTO) {
        return (Pattern.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}", registrationDTO.getEmail()));
    }

    private boolean isCfValid(RegistrationDTO registrationDTO) {
        return (Pattern.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$", registrationDTO.getFiscCode()));
    }

    private boolean isCellNumValid(RegistrationDTO registrationDTO) {
        return (Pattern.matches("[0-9]{8,12}", registrationDTO.getCellNum()));
    }

    private boolean isAlredyPresent(RegistrationDTO registrationDTO) {
        Optional<User> user = userDAO.findByUsername(registrationDTO.getUsername());
        Optional<User> user1 = userDAO.findByEmail(registrationDTO.getEmail());
        return user.isEmpty() && user1.isEmpty();
    }
}
