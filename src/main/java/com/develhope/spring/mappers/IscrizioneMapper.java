package com.develhope.spring.mappers;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.Iscrizione;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.IscrizioneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IscrizioneMapper {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CourseDAO courseDAO;

    public Iscrizione dtoToEntity(IscrizioneDTO iscrizioneDTO) throws CourseException, UserException {
        return new Iscrizione(
                iscrizioneDTO.getId(),
                userDAO.findById(iscrizioneDTO.getUser()).orElseThrow(() -> new UserException("Student not found!", 404)),
                courseDAO.findById(iscrizioneDTO.getCourse()).orElseThrow(() -> new CourseException("Course not found!", 404)),
                iscrizioneDTO.getDataIscrizione(),
                iscrizioneDTO.getPayed()
        );
    }
    public IscrizioneDTO entityToDTO(Iscrizione iscrizione){
        return new IscrizioneDTO(
                iscrizione.getId(),
                iscrizione.getUser().getId(),
                iscrizione.getCourse().getId(),
                iscrizione.getDataIscrizione(),
                iscrizione.getPayed()
        );
    }
}
