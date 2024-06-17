package com.develhope.spring.DAO;

import com.develhope.spring.entities.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTokenDAO extends JpaRepository<UserToken, Long> {

    @Query("SELECT ut FROM UserToken ut WHERE ut.user_id.id = :id")
    Optional<UserToken> findByUserId(@Param("id") Long id);
}
