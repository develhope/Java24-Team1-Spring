package com.develhope.spring.DAO;

import com.develhope.spring.entities.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenDAO extends JpaRepository<UserToken, Long> {
}
