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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public CourseDTO addCourse(String username, CourseDTO course) throws CourseException, UserException {
        if (validator.isCourseValid(course)) {
            User user = userDAO.findByUsername(username).orElseThrow(() -> new UserException("User not found", 400));
            Course entity = courseMapper.dtoToEntity(course);
            entity.setTutor(user);
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
        Course course = courseDAO.findById(id).orElseThrow(() -> new CourseException("This course doesn't exist!", 400));
        if (course != null) {
            return courseMapper.entityToDto(course);
        } else {
            throw new CourseException("This course doesn't exist!", 400);
        }
    }

    public CourseDTO updateCourseById(Long id, CourseDTO courseDTO, String username) throws CourseException, UserException {
        Course optionalCourse = courseDAO.findById(id).orElseThrow(() -> new CourseException("This course doesn't exist!", 400));
        if (optionalCourse.getTutor().getUsername().equals(username)) {
            optionalCourse.setName(courseDTO.getName());
            optionalCourse.setStartDate(courseDTO.getStartDate());
            optionalCourse.setFinishDate(courseDTO.getFinishDate());
            optionalCourse.setCourseLength(courseDTO.getCourseLength());
            optionalCourse.setPrice(courseDTO.getPrice());
            optionalCourse.setSubject(courseDTO.getSubject());
            optionalCourse.setDescription(courseDTO.getDescription());
            optionalCourse.setCourseType(courseDTO.getCourseType());
            Course courseEdited = courseDAO.saveAndFlush(optionalCourse);
            return courseMapper.entityToDto(courseEdited);
        } else {
            throw new CourseException("This course doesn't exist or you are not the owner!", 400);
        }
    }


    public void deleteCourseById(Long id) throws CourseException {
        Course course = courseDAO.findById(id).orElseThrow(() -> new CourseException("This course doesn't exist!", 400));
        if (course.getActiveCourse()) {
            course.setActiveCourse(false);
            courseDAO.saveAndFlush(course);
        } else {
            throw new CourseException("This course doesn't exist!", 400);
        }
    }

    public void deleteYourCourse(Long id, String username) throws CourseException {
        Course course = courseDAO.findById(id).orElseThrow(() -> new CourseException("This course doesn't exist!", 400));

        if (course.getActiveCourse() && course.getTutor().getUsername().equals(username)) {
            course.setActiveCourse(false);
            courseDAO.saveAndFlush(course);
        } else {
            throw new CourseException("This course doesn't exist or you are not the owner!", 400);
        }
    }


    public List<CourseDTO> getActiveCoursesByTutor(Long id) throws CourseException {
        List<Course> courseList = courseDAO.findActiveCourseByTutor(id);
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course : courseList) {
            courseDTOList.add(courseMapper.entityToDto(course));
        }
        return courseDTOList;
    }

    public List<CourseDTO> getYourActiveCourse(String username) throws CourseException {
        List<Course> courseList = courseDAO.findYourCourse(username);
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course : courseList) {
            courseDTOList.add(courseMapper.entityToDto(course));
        }
        return courseDTOList;
    }

    public List<CourseDTO> getActiveCourseBySubject(String s) {
        List<Course> courses = courseDAO.findActiveCourseBySubject(s);
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course c : courses) {
            courseDTOList.add(courseMapper.entityToDto(c));
        }
        return courseDTOList;
    }
}
