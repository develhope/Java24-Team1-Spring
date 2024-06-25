package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.CourseScheduleDAO;
import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.CourseSchedule;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.CourseScheduleException;
import com.develhope.spring.mappers.CourseScheduleMapper;
import com.develhope.spring.models.DTO.CourseScheduleDTO;
import com.develhope.spring.validators.CourseScheduleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CourseScheduleService {

    @Autowired
    private CourseScheduleDAO courseScheduleDAO;

    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private CourseScheduleMapper courseScheduleMapper;

    @Autowired
    private CourseScheduleValidator courseScheduleValidator;


    public CourseScheduleDTO addCourseSchedule(CourseScheduleDTO courseSchedule, String username) throws CourseScheduleException, CourseException {
        CourseSchedule entity = courseScheduleMapper.dtoToEntity(courseSchedule);
        if(entity.getCourse().getTutor().getUsername().equals(username)) {
            CourseSchedule saved = courseScheduleDAO.saveAndFlush(entity);
            return courseScheduleMapper.entityToDto(saved);
        }

        throw new CourseScheduleException("You are not the owner of this course!", 400);
    }

    public CourseScheduleDTO getCourseScheduleById(Long id) throws CourseScheduleException {
        CourseSchedule courseSchedule = courseScheduleDAO.findById(id).orElseThrow(() -> new CourseScheduleException("This Course Schedule doesn't exist!", 400));
        if (courseSchedule != null) {
            return courseScheduleMapper.entityToDto(courseSchedule);
        } else {
            throw new CourseScheduleException("This Course Schedule doesn't exist!", 400);
        }
    }


    public List<CourseScheduleDTO> getAllCourseSchedule() {
        List<CourseSchedule> courseScheduleList = courseScheduleDAO.findActiveCourseSchedule();
        List<CourseScheduleDTO> courseScheduleDTOList = new ArrayList<>();
        for (CourseSchedule courseSchedule : courseScheduleList) {
            CourseScheduleDTO courseScheduleDTO = courseScheduleMapper.entityToDto(courseSchedule);
            courseScheduleDTOList.add(courseScheduleDTO);
        }
        return courseScheduleDTOList;
    }


    public List<CourseSchedule> getAllCourseScheduleByCourse(Long id) throws CourseException {
        courseDAO.findById(id).orElseThrow(() -> new CourseException("Course not found", 400));
        return courseScheduleDAO.findActiveCourseScheduleByCourse(id);
    }

    public CourseScheduleDTO updateCourseScheduleById(Long id, CourseScheduleDTO courseScheduleDTO, String username) throws CourseScheduleException, CourseException {
        CourseSchedule optionalCourseSchedule = courseScheduleDAO.findById(id).orElseThrow(() -> new CourseScheduleException("This Course Schedule doesn't exist!", 400));
        if (optionalCourseSchedule != null && optionalCourseSchedule.getCourse().getTutor().getUsername().equals(username)) {
            optionalCourseSchedule.setStartDateTime(courseScheduleDTO.getStartDateTime());
            optionalCourseSchedule.setFinishDateTime(courseScheduleDTO.getFinishDateTime());
            optionalCourseSchedule.setLink(courseScheduleDTO.getLink());
            optionalCourseSchedule.setCourse(courseDAO.findById(courseScheduleDTO.getCourse_id()).orElseThrow(() -> new CourseException("This Course Schedule doesn't exist!", 400)));
            CourseSchedule courseScheduleEdited = courseScheduleDAO.saveAndFlush(optionalCourseSchedule);
            return courseScheduleMapper.entityToDto(courseScheduleEdited);
        } else {
            throw new CourseScheduleException("This Course Schedule doesn't exist or you are not the owner!", 400);
        }
    }

    public void deleteCourseScheduleById(Long id) throws CourseScheduleException {
        CourseSchedule courseSchedule = courseScheduleDAO.findById(id).orElseThrow(() -> new CourseScheduleException("This Course Schedule doesn't exist!", 400));
        if (!courseSchedule.getIsDeleted()) {
            courseSchedule.setIsDeleted(true);
            courseScheduleDAO.saveAndFlush(courseSchedule);
        } else {
            throw new CourseScheduleException("This Course Schedule doesn't exist!", 400);
        }
    }

    public void deleteYourCourseScheduleById(Long id, String username) throws CourseScheduleException {
        CourseSchedule courseSchedule = courseScheduleDAO.findById(id).orElseThrow(() -> new CourseScheduleException("This Course Schedule doesn't exist!", 400));
        if (!courseSchedule.getIsDeleted() && courseSchedule.getCourse().getTutor().getUsername().equals(username)) {
            courseSchedule.setIsDeleted(true);
            courseScheduleDAO.saveAndFlush(courseSchedule);
        } else {
            throw new CourseScheduleException("This Course Schedule doesn't exist or you are not the owner!", 400);
        }
    }

}
