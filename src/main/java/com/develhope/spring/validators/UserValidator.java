package com.develhope.spring.validators;

import com.develhope.spring.models.DTO.requestDTO.UserRequestDTO;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserValidator {

    public boolean isUserValid(UserRequestDTO u) {
        return (isUserNotNull(u) && isEmailValid(u) && isCfValid(u) && isCellNumValid(u));
    }

    private boolean isUserNotNull(UserRequestDTO u) {
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

    private boolean isEmailValid(UserRequestDTO u) {
        return (Pattern.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}", u.getEmail()));

    }

    private boolean isCfValid(UserRequestDTO u) {
        return (Pattern.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$", u.getFiscCode()));
    }

    private boolean isCellNumValid(UserRequestDTO u) {
        return (Pattern.matches("[0-9]{8,12}", u.getCellNum()));
    }

}
