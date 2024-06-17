package com.develhope.spring.mappers;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.Grade;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.GradeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CourseDAO courseDAO;

    public Grade dtoToEntity(GradeDTO gradeDTO) throws UserException, CourseException {
        return new Grade(
                gradeDTO.getId(),
                userDAO.findById(gradeDTO.getStudent_id()).orElseThrow(() -> new UserException("Student not found!", 404)),
                courseDAO.findById(gradeDTO.getCourse_id()).orElseThrow(() -> new CourseException("Course not found!", 404)),
                gradeDTO.getGrade(),
                gradeDTO.getFinishedCourse()
        );
    }

    public GradeDTO entityToDto(Grade grade){
        return new GradeDTO(
                grade.getId(),
                grade.getStudent().getId(),
                grade.getCourse().getId(),
                grade.getGrade(),
                grade.getFinishedCourse()
        );
    }
}
