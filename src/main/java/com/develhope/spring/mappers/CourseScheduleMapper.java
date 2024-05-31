package com.develhope.spring.mappers;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.entities.CourseSchedule;
import com.develhope.spring.models.DTO.CourseScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseScheduleMapper {

    @Autowired
    private CourseDAO courseDAO;

    public CourseSchedule dtoToEntity(CourseScheduleDTO courseScheduleDTO) {
        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setId(courseScheduleDTO.getId());
        courseSchedule.setCourse(courseDAO.findById(courseScheduleDTO.getCourse_id()).orElse(null));
        courseSchedule.setLink(courseScheduleDTO.getLink());
        courseSchedule.setStartDateTime(courseScheduleDTO.getStartDateTime());
        courseSchedule.setFinishDateTime(courseScheduleDTO.getFinishDateTime());
        return courseSchedule;
    }

    public CourseScheduleDTO entityToDto(CourseSchedule courseSchedule) {
        CourseScheduleDTO courseScheduleDTO = new CourseScheduleDTO();
        courseScheduleDTO.setId(courseSchedule.getId());
        courseScheduleDTO.setCourse_id(courseSchedule.getCourse().getId());
        courseScheduleDTO.setLink(courseSchedule.getLink());
        courseScheduleDTO.setStartDateTime(courseSchedule.getStartDateTime());
        courseScheduleDTO.setFinishDateTime(courseSchedule.getFinishDateTime());
        return courseScheduleDTO;
    }

}
