package com.develhope.spring.validators;

import com.develhope.spring.DAO.CourseScheduleDAO;
import com.develhope.spring.models.DTO.requestDTO.CourseScheduleRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class CourseScheduleValidator {

    @Autowired
    private CourseScheduleDAO courseScheduleDAO;

    public boolean isCourseScheduleValid(CourseScheduleRequestDTO courseSchedule) {
        return isCourseScheduleNotNull(courseSchedule);
    }

    private boolean isCourseScheduleNotNull(CourseScheduleRequestDTO courseSchedule) {
        return (
                courseSchedule.getStartDateTime() != null &&
                courseSchedule.getFinishDateTime() != null &&
                courseSchedule.getLink() != null
                );
    }

}
