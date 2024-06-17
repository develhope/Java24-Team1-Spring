package com.develhope.spring.models;

import com.develhope.spring.entities.User;
import com.develhope.spring.enums.RoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private final String username;
    private String password;
    private final Collection<? extends GrantedAuthority> authorities;




    public UserDetailsImpl(String username, String password, RoleEnum role) {
        this.username = username;
        this.password = password;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities != null ? authorities : Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return null;
    }



    @Override
    public String getUsername() {
        return this.username;
    }

    public static UserDetailsImpl build(User user) {

        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                user.getRole());
    }
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
