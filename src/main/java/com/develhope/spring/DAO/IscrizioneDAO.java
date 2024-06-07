package com.develhope.spring.DAO;

import com.develhope.spring.entities.Iscrizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IscrizioneDAO extends JpaRepository<Iscrizione, Long> {

    @Query("SELECT i FROM Iscrizione i WHERE i.user.id = :id")
    List<Iscrizione> findCourseByUser(@Param("id") Long id);

}
