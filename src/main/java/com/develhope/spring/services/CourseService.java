package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.CourseSchedule;
import com.develhope.spring.entities.User;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.CourseScheduleException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.mappers.CourseMapper;
import com.develhope.spring.models.DTO.CourseDTO;
import com.develhope.spring.validators.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<Course> courseList = courseDAO.findActiveCourse();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course : courseList) {
            CourseDTO courseDTO = courseMapper.entityToDto(course);
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }

    public CourseDTO getCourseById(Long id) throws CourseException {
        Course course = courseDAO.findById(id).orElseThrow(() -> new CourseException("Course not found!", 404));
        if (course != null) {
            return courseMapper.entityToDto(course);
        } else {
            throw new CourseException("Course not found!", 404);
        }
    }

    public CourseDTO updateCourseById(Long id, CourseDTO courseDTO) throws CourseException, UserException {
        Course optionalCourse = courseDAO.findById(id).orElseThrow(() -> new CourseException("Course not found!", 404));

        if (optionalCourse != null) {
            optionalCourse.setName(courseDTO.getName());
            optionalCourse.setStartDate(courseDTO.getStartDate());
            optionalCourse.setFinishDate(courseDTO.getFinishDate());
            optionalCourse.setCourseLength(courseDTO.getCourseLength());
            optionalCourse.setPrice(courseDTO.getPrice());
            optionalCourse.setSubject(courseDTO.getSubject());
            optionalCourse.setDescription(courseDTO.getDescription());
            optionalCourse.setTutor(userDAO.findById(courseDTO.getTutor_id()).orElseThrow(() -> new UserException("Course not found!", 404)));
            optionalCourse.setCourseType(courseDTO.getCourseType());
            Course courseEdited = courseDAO.saveAndFlush(optionalCourse);
            return courseMapper.entityToDto(courseEdited);
        } else {
            throw new CourseException("Course not found!", 404);
        }
    }

    public void deleteCourseById(Long id) throws CourseException {
        Course course = courseDAO.findById(id).orElseThrow(() -> new CourseException("Course not found!", 404));
        if(course.getActiveCourse()) {
            course.setActiveCourse(false);
            courseDAO.saveAndFlush(course);
        } else {
            throw new CourseException("course id not found", 404);
        }
    }


    public List<CourseDTO> getActiveCoursesByTutor(Long id) throws CourseException {
        List<Course> courseList = courseDAO.findActiveCourse();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course : courseList) {
            if (Objects.equals(course.getTutor().getId(), id) && course.getActiveCourse()) {
                courseDTOList.add(courseMapper.entityToDto(course));
            }
        }
        if(!courseDTOList.isEmpty()) {
            return courseDTOList;
        }else{
            throw new CourseException("no courses found", 404);
        }
    }

    public List<CourseDTO> getActiveCourseBySubject(String s) {
        List<Course> courses = courseDAO.findActiveCourseBySubject(s);
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for(Course c : courses){
            courseDTOList.add(courseMapper.entityToDto(c));
        }
        return courseDTOList;

    }
}
