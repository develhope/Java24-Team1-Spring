package com.develhope.spring.services;

import com.develhope.spring.DAO.GradeDAO;
import com.develhope.spring.entities.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    @Autowired
    private GradeDAO gradeDAO;

    public void addGrade(Grade grade) {
        gradeDAO.saveAndFlush(grade);
    }
}
