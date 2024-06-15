package com.develhope.spring.DAO;

import com.develhope.spring.entities.Iscrizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IscrizioneDAO extends JpaRepository<Iscrizione, Long> {

    @Query("SELECT i FROM Iscrizione i WHERE i.user.id = :id AND i.isDeleted = false")
    List<Iscrizione> findCourseByUser(@Param("id") Long id);

    @Query("SELECT i FROM Iscrizione i WHERE i.course.tutor.id = :id AND i.isDeleted = false")
    List<Iscrizione> findCourseByTutor(@Param("id") Long id);

    @Query("SELECT i FROM Iscrizione i WHERE i.course.id = :id AND i.isDeleted = false")
    List<Iscrizione> findCourseByCourse(@Param("id") Long id);

    @Query("SELECT i FROM Iscrizione i WHERE i.isDeleted = false")
    List<Iscrizione> findActiveIscrizione();

}
