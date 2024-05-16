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
        return (isCourseNotNull(c) && isDateValid(c));
    }

    private boolean isCourseNotNull(CourseDTO c) {
        return (
                c.getName() != null &&
                        c.getStartDate() != null &&
                        c.getFinishDate() != null &&
                        c.getCourseLength() != null &&
                        c.getPrice() != null &&
                        c.getSubject() != null &&
                        c.getTutor() != null &&
                        c.getActiveCourse() != null &&
                        c.getCourseType() != null
                );
    }

    private boolean isDateValid(CourseDTO c) {
        return (
                Pattern.matches("(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)dd", c.getStartDate().toString()) &&
                Pattern.matches("(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)dd", c.getFinishDate().toString())
                );
    }

    private boolean isCourseLenghtNumber(CourseDTO c) {
        return (Pattern.matches("\\d", c.getCourseLength().toString()));
    }

    private boolean isTutorIdPresent(CourseDTO c){
        return userDAO.existsById(c.getTutor().getId());
    }
}
