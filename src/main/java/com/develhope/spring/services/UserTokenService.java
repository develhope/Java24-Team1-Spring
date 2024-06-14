package com.develhope.spring.services;

import com.develhope.spring.DAO.UserTokenDAO;
import com.develhope.spring.entities.User;
import com.develhope.spring.entities.UserToken;
import com.develhope.spring.exceptions.UserTokenException;
import com.google.api.client.auth.oauth2.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserTokenService {
    @Autowired
    private UserTokenDAO userTokenDAO;

    public UserToken findById(Long id) throws Exception {
        return userTokenDAO.findById(id).orElseThrow(() -> new UserTokenException("User token not found!", 404));
    }

    public UserToken createUserToken(User user){

        UserToken userToken = new UserToken();
        userToken.setUser_id(user);
        return userTokenDAO.save(userToken);
    }

    public void setToken(Credential credential, UserToken user){
        user.setAccessToken(credential.getAccessToken());
        user.setRefreshToken(credential.getRefreshToken());
        user.setTokenExpiry(credential.getExpirationTimeMilliseconds() == null ? null : Instant.ofEpochMilli(credential.getExpirationTimeMilliseconds()));
        userTokenDAO.save(user);
    }

    public UserToken findByUserId(User user) throws UserTokenException {
        return userTokenDAO.findByUserId(user.getId()).orElse(null);
    }
}