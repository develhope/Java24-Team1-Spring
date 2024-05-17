package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.entities.Course;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.models.DTO.CourseDTO;
import com.develhope.spring.validators.CourseValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private CourseValidator validator;

    public CourseDTO addCourse(CourseDTO course) throws CourseException {
        if (validator.isCourseValid(course)) {
            Course entity = modelMapper.map(course, Course.class);
            Course saved = courseDAO.saveAndFlush(entity);
            modelMapper.map(saved, course);
            return course;
        }else{
            throw new CourseException("Course not added, a problem occurred with the data", 400);
        }

    }
    public List<Course> getAllCourse(){
        return courseDAO.findAll();
    }
    public Optional<Course> getCourseById (Long id){
        return courseDAO.findById(id);
    }

}
