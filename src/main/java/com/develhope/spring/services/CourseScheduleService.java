package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.CourseScheduleDAO;
import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.CourseSchedule;
import com.develhope.spring.exceptions.CourseScheduleException;
import com.develhope.spring.models.DTO.CourseScheduleDTO;
import com.develhope.spring.validators.CourseScheduleValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseScheduleService {

    @Autowired
    private CourseScheduleDAO courseScheduleDAO;

    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CourseScheduleValidator courseScheduleValidator;


    public CourseScheduleDTO addCourseSchedule(CourseScheduleDTO courseSchedule) throws CourseScheduleException{
        CourseSchedule entity = modelMapper.map(courseSchedule, CourseSchedule.class);

        Optional<Course> course = courseDAO.findById(courseSchedule.getCourse_id());
        entity.setCourse(course.get());

        CourseSchedule saved = courseScheduleDAO.saveAndFlush(entity);
        modelMapper.map(saved, courseSchedule);
        return courseSchedule;
    }

    public Optional<CourseSchedule> getCourseScheduleById(Long id) {
        return courseScheduleDAO.findById(id);
    }

    public List<CourseSchedule> getAllCourseSchedule() {
        return courseScheduleDAO.findAll();
    }
}
