package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.Course;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.mappers.CourseMapper;
import com.develhope.spring.models.DTO.CourseDTO;
import com.develhope.spring.validators.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private CourseValidator validator;
    @Autowired
    private UserDAO userDAO;

    public CourseDTO addCourse(CourseDTO course) throws CourseException {
        if (validator.isCourseValid(course)) {
            Course entity = courseMapper.dtoToEntity(course);
            Course saved = courseDAO.saveAndFlush(entity);
            return courseMapper.entityToDto(saved);
        } else {
            throw new CourseException("Course not added, a problem occurred with the data", 400);
        }

    }

    public List<CourseDTO> getAllCourse() {
        List<Course> courseList = courseDAO.findAll();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course : courseList) {
            CourseDTO courseDTO = courseMapper.entityToDto(course);
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }

    public CourseDTO getCourseById(Long id) throws CourseException {
        Course course = courseDAO.findById(id).orElse(null);
        if (course != null) {
            return courseMapper.entityToDto(course);
        } else {
            throw new CourseException("Course not found!", 404);
        }
    }

    public CourseDTO updateCourseById(Long id, CourseDTO courseDTO) throws CourseException {
        Course optionalCourse = courseDAO.findById(id).orElse(null);
        if (optionalCourse != null) {
            optionalCourse.setName(courseDTO.getName());
            optionalCourse.setStartDate(courseDTO.getStartDate());
            optionalCourse.setFinishDate(courseDTO.getFinishDate());
            optionalCourse.setCourseLength(courseDTO.getCourseLength());
            optionalCourse.setPrice(courseDTO.getPrice());
            optionalCourse.setSubject(courseDTO.getSubject());
            optionalCourse.setDescription(courseDTO.getDescription());
            optionalCourse.setTutor(userDAO.findById(courseDTO.getTutor_id()).orElse(null));
            optionalCourse.setActiveCourse(courseDTO.getActiveCourse());
            optionalCourse.setCourseType(courseDTO.getCourseType());
            Course courseEdited = courseDAO.saveAndFlush(optionalCourse);
            return courseMapper.entityToDto(courseEdited);
        } else {
            throw new CourseException("Course not found!", 404);
        }
    }

    public void deleteCourseById(Long id) throws CourseException {
        if (courseDAO.existsById(id)) {
            courseDAO.deleteById(id);
        } else {
            throw new CourseException("course id not found", 404);
        }
    }
    public void deleteAllCourses(){
        courseDAO.deleteAll();
    }
}
