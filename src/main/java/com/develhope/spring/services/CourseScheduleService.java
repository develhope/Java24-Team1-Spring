package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.CourseScheduleDAO;
import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.CourseSchedule;
import com.develhope.spring.exceptions.CourseScheduleException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.mappers.CourseScheduleMapper;
import com.develhope.spring.models.DTO.CourseScheduleDTO;
import com.develhope.spring.validators.CourseScheduleValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private CourseScheduleMapper courseScheduleMapper;

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

    public List<CourseScheduleDTO> getAllCourseSchedule() {
        List<CourseSchedule> courseScheduleList = courseScheduleDAO.findAll();
        List<CourseScheduleDTO> courseScheduleDTOList = new ArrayList<>();
        for(CourseSchedule courseSchedule: courseScheduleList){
            CourseScheduleDTO courseScheduleDTO = modelMapper.map(courseSchedule, CourseScheduleDTO.class);
            courseScheduleDTOList.add(courseScheduleDTO);
        }
        return courseScheduleDTOList;
    }

    public List<CourseSchedule> getAllCourseScheduleByCourse(Long id) {
        List<CourseSchedule> courseScheduleList = courseScheduleDAO.findAll();
        List<CourseSchedule> newCourseScheduleList = new ArrayList<>();
        for(CourseSchedule courseSchedule: courseScheduleList){
            if(courseSchedule.getCourse().getId() == id) {
                newCourseScheduleList.add(courseSchedule);
            }
        }
        return newCourseScheduleList;
    }

    public CourseScheduleDTO updateCourseScheduleById(Long id, CourseScheduleDTO courseScheduleDTO) throws CourseScheduleException {
        CourseSchedule optionalCourseSchedule = courseScheduleDAO.findById(id).orElse(null);
            optionalCourseSchedule.setStartDateTime(courseScheduleDTO.getStartDateTime());
            optionalCourseSchedule.setFinishDateTime(courseScheduleDTO.getFinishDateTime());
            optionalCourseSchedule.setLink(courseScheduleDTO.getLink());
            optionalCourseSchedule.setCourse(courseDAO.findById(courseScheduleDTO.getCourse_id()).orElse(null));
            CourseSchedule courseScheduleEdited = courseScheduleDAO.saveAndFlush(optionalCourseSchedule);
            modelMapper.map(courseScheduleEdited, courseScheduleDTO);

        return courseScheduleDTO;
    }
    public void deleteCourseScheduleById(Long id) throws CourseScheduleException {
        if(courseScheduleDAO.existsById(id)) {
            courseScheduleDAO.deleteById(id);
        }else{
            throw  new CourseScheduleException("course schedule id not found", 404);
        }
    }

    public void deleteAllCourseSchedules() {
        courseScheduleDAO.deleteAll();
    }
}
