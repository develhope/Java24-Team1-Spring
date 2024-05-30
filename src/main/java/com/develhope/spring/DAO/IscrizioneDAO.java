package com.develhope.spring.DAO;

import com.develhope.spring.entities.Iscrizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IscrizioneDAO extends JpaRepository<Iscrizione, Long> {
}
