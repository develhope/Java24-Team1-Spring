package com.develhope.spring.DAO;

import com.develhope.spring.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeDAO extends JpaRepository<Grade, Long> {

    @Query("SELECT g FROM Grade g WHERE g.student.username = :username")
    List<Grade> findAllStudentGrades(@Param("username") String username);

    @Query("SELECT g FROM Grade g WHERE g.course.tutor.username = :username")
    List<Grade> findAllTutorGrades(@Param("username") String username);

    @Query("SELECT g FROM Grade g WHERE g.isDeleted = false")
    List<Grade> findActiveGrade();
}
