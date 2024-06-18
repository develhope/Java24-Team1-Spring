package com.develhope.spring.validators;

import com.develhope.spring.entities.User;
import com.develhope.spring.models.DTO.UserDTO;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserValidator {

    public boolean isUserValid(UserDTO u) {
        return (isUserNotNull(u) && isEmailValid(u) && isCfValid(u) && isCellNumValid(u));
    }

    private boolean isUserNotNull(UserDTO u) {
        return (
                u.getName() != null &&
                u.getSurname() != null &&
                u.getUsername() != null &&
                u.getEmail() != null &&
                u.getFiscCode() != null &&
                u.getRole() != null &&
                u.getPassword() != null
                );
    }

    private boolean isEmailValid(UserDTO u) {
        return (Pattern.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}", u.getEmail()));
    }

    private boolean isCfValid(UserDTO u) {
        return (Pattern.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$", u.getFiscCode()));
    }

    private boolean isCellNumValid(UserDTO u) {
        return (Pattern.matches("[0-9]{8,12}", u.getCellNum()));
    }

}
