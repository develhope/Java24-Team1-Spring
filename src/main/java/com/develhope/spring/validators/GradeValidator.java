package com.develhope.spring.validators;


import com.develhope.spring.DAO.GradeDAO;
import com.develhope.spring.models.DTO.CourseDTO;
import com.develhope.spring.models.DTO.GradeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GradeValidator {

        public boolean isGradeValid(GradeDTO g) {
            return (isGradeNotNull(g));
        }

        private boolean isGradeNotNull(GradeDTO g) {
            return (
                    g.getStudent_id() != null &&
                    g.getCourse_id() != null &&
                    g.getGrade() != null &&
                    g.getFinishedCourse() != null
            );
        }
}
