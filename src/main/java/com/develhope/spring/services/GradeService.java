package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.GradeDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.Grade;
import com.develhope.spring.entities.User;
import com.develhope.spring.exceptions.GradeException;
import com.develhope.spring.models.DTO.GradeDTO;
import com.develhope.spring.validators.GradeValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GradeDAO gradeDAO;
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private GradeValidator validator;

    public GradeDTO addGrade(GradeDTO grade) throws GradeException {
        if (validator.isGradeValid(grade)){
            Grade entity = modelMapper.map(grade, Grade.class);

            Optional<User> student = userDAO.findById(grade.getStudent_id());
            entity.setStudent(student.get());
            Optional<Course> course = courseDAO.findById(grade.getCourse_id());
            entity.setCourse(course.get());

            Grade saved = gradeDAO.saveAndFlush(entity);
            modelMapper.map(saved, grade);
            return grade;
        }else{
            throw new GradeException("Grade not added, a problem occurred with the data", 400);
        }
    }

}
