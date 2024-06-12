package com.develhope.spring.DAO;

import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.Iscrizione;
import com.develhope.spring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CourseDAO extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.activeCourse = True AND c.subject = :materia")
    List<Course> findActiveCourseBySubject(@Param("materia") String materia);




}
