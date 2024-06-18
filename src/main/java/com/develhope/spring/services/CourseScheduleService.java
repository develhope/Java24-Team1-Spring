package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.CourseScheduleDAO;
import com.develhope.spring.entities.CourseSchedule;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.CourseScheduleException;
import com.develhope.spring.mappers.CourseScheduleMapper;
import com.develhope.spring.models.DTO.requestDTO.CourseScheduleRequestDTO;
import com.develhope.spring.models.DTO.responseDTO.CourseScheduleResponseDTO;
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


    public CourseScheduleResponseDTO addCourseSchedule(CourseScheduleRequestDTO courseSchedule) throws CourseScheduleException, CourseException {
        CourseSchedule entity = courseScheduleMapper.dtoToEntity(courseSchedule);
        CourseSchedule saved = courseScheduleDAO.saveAndFlush(entity);
        return courseScheduleMapper.entityToDto(saved);
    }

    public CourseScheduleResponseDTO getCourseScheduleById(Long id) throws CourseScheduleException {
        CourseSchedule courseSchedule = courseScheduleDAO.findById(id).orElseThrow(() -> new CourseScheduleException("This Course Schedule doesn't exist!", 400));
        if(courseSchedule != null) {
            return courseScheduleMapper.entityToDto(courseSchedule);
        } else {
            throw new CourseScheduleException("This Course Schedule doesn't exist!", 400);
        }
    }

    public List<CourseScheduleResponseDTO> getAllCourseSchedule() {
        List<CourseSchedule> courseScheduleList = courseScheduleDAO.findActiveCourseSchedule();
        List<CourseScheduleResponseDTO> courseScheduleDTOList = new ArrayList<>();
        for(CourseSchedule courseSchedule: courseScheduleList){
            CourseScheduleResponseDTO courseScheduleDTO = courseScheduleMapper.entityToDto(courseSchedule);
            courseScheduleDTOList.add(courseScheduleDTO);
        }
        return courseScheduleDTOList;
    }


    public List<CourseSchedule> getAllCourseScheduleByCourse(Long id) {
        return courseScheduleDAO.findActiveCourseScheduleByCourse(id);
    }

    public CourseScheduleResponseDTO updateCourseScheduleById(Long id, CourseScheduleRequestDTO courseScheduleDTO) throws CourseScheduleException, CourseException {
        CourseSchedule optionalCourseSchedule = courseScheduleDAO.findById(id).orElseThrow(() -> new CourseScheduleException("This Course Schedule doesn't exist!", 400));
        if (optionalCourseSchedule != null) {
            optionalCourseSchedule.setStartDateTime(courseScheduleDTO.getStartDateTime());
            optionalCourseSchedule.setFinishDateTime(courseScheduleDTO.getFinishDateTime());
            optionalCourseSchedule.setLink(courseScheduleDTO.getLink());
            optionalCourseSchedule.setCourse(courseDAO.findById(courseScheduleDTO.getCourse_id()).orElseThrow(() -> new CourseException("This Course Schedule doesn't exist!", 400)));
            CourseSchedule courseScheduleEdited = courseScheduleDAO.saveAndFlush(optionalCourseSchedule);
            return courseScheduleMapper.entityToDto(courseScheduleEdited);
        } else {
            throw new CourseScheduleException("This Course Schedule doesn't exist!", 400);
        }
    }

    public void deleteCourseScheduleById(Long id) throws CourseScheduleException {
        CourseSchedule courseSchedule = courseScheduleDAO.findById(id).orElseThrow(() -> new CourseScheduleException("This Course Schedule doesn't exist!", 400));
        if(!courseSchedule.getIsDeleted()) {
            courseSchedule.setIsDeleted(true);
            courseScheduleDAO.saveAndFlush(courseSchedule);
        }else{
            throw  new CourseScheduleException("This Course Schedule doesn't exist!", 400);
        }
    }

}
