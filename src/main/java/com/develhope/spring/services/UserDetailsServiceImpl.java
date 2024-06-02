package com.develhope.spring.services;

import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.User;
import com.develhope.spring.models.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws BadCredentialsException {
        Optional<User> user = userDAO.findByUsername(username);

        if(user.isPresent()) {
            return UserDetailsImpl.build(user.get());
        }
        return null;
    }
}
