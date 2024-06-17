package com.develhope.spring.validators;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.Course;
import com.develhope.spring.models.DTO.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CourseValidator {

    @Autowired
    private UserDAO userDAO;
    public boolean isCourseValid(CourseDTO c) {
        return isCourseNotNull(c) && isDateValid(c) && isTutorIdPresent(c);
    }

    private boolean isCourseNotNull(CourseDTO c) {
        return (
                c.getName() != null &&
                        c.getStartDate() != null &&
                        c.getFinishDate() != null &&
                        c.getCourseLength() != null &&
                        c.getPrice() != null &&
                        c.getSubject() != null &&
                        c.getTutor_id() != null &&
                        c.getCourseType() != null
                );
    }

    private boolean isDateValid(CourseDTO c) {
        return Pattern.matches("\\d{1,2}+/+\\d{1,2}+/+\\d{2,4}", c.getStartDate() )&&
                Pattern.matches("\\d{1,2}+/+\\d{1,2}+/+\\d{2,4}", c.getFinishDate());
    }

    private boolean isTutorIdPresent(CourseDTO c){
        return userDAO.existsById(c.getTutor_id());
    }
}
