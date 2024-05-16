package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.entities.Course;
import com.develhope.spring.models.DTO.CourseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CourseDAO courseDAO;

    public CourseDTO addCourse(CourseDTO course) {
        Course entity = modelMapper.map(course, Course.class);
        Course saved = courseDAO.saveAndFlush(entity);
        modelMapper.map(saved, course);
        return course;
    }
}
