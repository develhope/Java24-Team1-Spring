package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseScheduleDAO;
import com.develhope.spring.entities.CourseSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseScheduleService {

    @Autowired
    private CourseScheduleDAO courseScheduleDAO;

    public void addCourseSchedule(CourseSchedule courseSchedule) {
        courseScheduleDAO.saveAndFlush(courseSchedule);
    }
}
