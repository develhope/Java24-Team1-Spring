package com.develhope.spring.mappers;

import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.Course;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.models.DTO.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    @Autowired
    private UserDAO userDAO;

    public Course dtoToEntity(CourseDTO courseDTO) throws CourseException {
        return new Course(
                courseDTO.getId(),
                courseDTO.getName(),
                courseDTO.getStartDate(),
                courseDTO.getFinishDate(),
                courseDTO.getCourseLength(),
                courseDTO.getPrice(),
                courseDTO.getSubject(),
                courseDTO.getDescription(),
                userDAO.findById(courseDTO.getTutor_id()).orElseThrow(() -> new CourseException("Tutor not found!", 404)),
                courseDTO.getCourseType()
        );
    }

    public CourseDTO entityToDto(Course course){
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getStartDate(),
                course.getFinishDate(),
                course.getCourseLength(),
                course.getPrice(),
                course.getSubject(),
                course.getDescription(),
                course.getTutor().getId(),
                course.getCourseType()
        );
    }
}
