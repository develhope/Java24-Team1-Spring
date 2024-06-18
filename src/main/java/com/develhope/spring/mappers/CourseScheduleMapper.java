package com.develhope.spring.mappers;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.entities.CourseSchedule;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.models.DTO.requestDTO.CourseScheduleRequestDTO;
import com.develhope.spring.models.DTO.responseDTO.CourseScheduleResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseScheduleMapper {

    @Autowired
    private CourseDAO courseDAO;

    public CourseSchedule dtoToEntity(CourseScheduleRequestDTO courseScheduleDTO) throws CourseException {
        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setId(courseScheduleDTO.getId());
        courseSchedule.setCourse(courseDAO.findById(courseScheduleDTO.getCourse_id()).orElseThrow(() -> new CourseException("Course not found!", 404)));
        courseSchedule.setLink(courseScheduleDTO.getLink());
        courseSchedule.setStartDateTime(courseScheduleDTO.getStartDateTime());
        courseSchedule.setFinishDateTime(courseScheduleDTO.getFinishDateTime());
        return courseSchedule;
    }

    public CourseScheduleResponseDTO entityToDto(CourseSchedule courseSchedule) {
        CourseScheduleResponseDTO courseScheduleDTO = new CourseScheduleResponseDTO();
        courseScheduleDTO.setId(courseSchedule.getId());
        courseScheduleDTO.setCourse_id(courseSchedule.getCourse().getId());
        courseScheduleDTO.setLink(courseSchedule.getLink());
        courseScheduleDTO.setStartDateTime(courseSchedule.getStartDateTime().toString());
        courseScheduleDTO.setFinishDateTime(courseSchedule.getFinishDateTime().toString());
        return courseScheduleDTO;
    }

}
