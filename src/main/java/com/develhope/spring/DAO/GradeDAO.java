package com.develhope.spring.DAO;

import com.develhope.spring.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeDAO extends JpaRepository<Grade, Long> {
}
