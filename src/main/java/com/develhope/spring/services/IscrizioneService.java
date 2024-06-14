package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.IscrizioneDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.*;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.CourseScheduleException;
import com.develhope.spring.exceptions.IscrizioneException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.mappers.IscrizioneMapper;
import com.develhope.spring.mappers.UserMapper;
import com.develhope.spring.models.DTO.CourseScheduleDTO;
import com.develhope.spring.models.DTO.IscrizioneDTO;
import com.develhope.spring.models.DTO.UserDTO;
import com.develhope.spring.validators.IscrizioneValidator;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class IscrizioneService {
    @Autowired
    private IscrizioneDAO iscrizioneDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private CourseScheduleService courseScheduleService;
    @Autowired
    private GoogleCalendarService googleCalendarService;
    @Autowired
    private IscrizioneValidator iscrizioneValidator;
    @Autowired
    private IscrizioneMapper iscrizioneMapper;
    @Autowired
    private UserMapper userMapper;

    public IscrizioneDTO subscribeToCourse(Long idUser, Long idCourse) throws Exception {

        User user = userDAO.findById(idUser).orElseThrow(() -> new UserException("User not found!", 400));
        Course course = courseDAO.findById(idCourse).orElseThrow(() -> new CourseException("Course not found!", 400));

        if(iscrizioneValidator.isIscrizioneValid(user,course)){
            Iscrizione iscrizione = new Iscrizione();
            iscrizione.setCourse(course);
            iscrizione.setUser(user);
            Date dataIscrizione = new Date();
            iscrizione.setDataIscrizione(dataIscrizione.toString());
            iscrizione.setPayed(false);
            Iscrizione saved = iscrizioneDAO.saveAndFlush(iscrizione);

            //richiama metodo di UserTokenService; DA USCIRE DA QUI O FARE UN CONTROLLO SE GIà ESISTE TOKEN!////////////////////////////////////////////////////////////////////////////////////////////////////
            UserToken userToken;
            if (userTokenService.findByUserId(user)!= null){
                userToken = userTokenService.findByUserId(user);
            }else {
                userToken = userTokenService.createUserToken(user);
            }


            //Crea List di EVENT da CourseSchedule utilizzando un servizio che torna una lista di eventi di un corso;
            List<CourseSchedule> cs = courseScheduleService.getAllCourseScheduleByCourse(course.getId());

            System.out.println(cs.toString());

            //richiama metodo AddEvent;
            googleCalendarService.addEvent(userToken, cs);

            return iscrizioneMapper.entityToDTO(saved);
        } else {
            throw new IscrizioneException("Subscription not added, a problem occurred with the data", 400);
        }
    }

      
    public IscrizioneDTO getById(Long id) throws IscrizioneException {
        Iscrizione iscrizione = iscrizioneDAO.findById(id).orElseThrow(() -> new IscrizioneException("Subscription not found!", 400));
        if (iscrizione != null) {
            return iscrizioneMapper.entityToDTO(iscrizione);
        } else {
            throw new IscrizioneException("Subscription not found", 404);
        }
    }

    public List<IscrizioneDTO> getAll() {
        List<Iscrizione> iscrizioneList = iscrizioneDAO.findActiveIscrizione();
        List<IscrizioneDTO> iscrizioneDTOList = new ArrayList<>();
        for (Iscrizione iscrizione : iscrizioneList) {
            iscrizioneDTOList.add(iscrizioneMapper.entityToDTO(iscrizione));
        }
        return iscrizioneDTOList;
    }

    public IscrizioneDTO payedSwitch(Long id) throws IscrizioneException {
        Iscrizione iscrizione = iscrizioneDAO.findById(id).orElseThrow(() -> new IscrizioneException("Subscription not found!", 404));
        if (iscrizione != null) {
            iscrizione.setPayed(!iscrizione.getPayed());
            Iscrizione saved = iscrizioneDAO.saveAndFlush(iscrizione);
            return iscrizioneMapper.entityToDTO(saved);
        } else {
            throw new IscrizioneException("Subscription not found", 404);
        }
    }

    public void deleteSubscription(Long id) throws IscrizioneException {
        Iscrizione iscrizione = iscrizioneDAO.findById(id).orElseThrow(() -> new IscrizioneException("Subscription not found!", 404));
        if(!iscrizione.getIsDeleted()) {
            iscrizione.setIsDeleted(true);
            iscrizioneDAO.saveAndFlush(iscrizione);
        } else {
            throw new IscrizioneException("Subscription not found", 404);
        }
    }

    public List<IscrizioneDTO> getAllByTutor(Long id) throws IscrizioneException {
        List<Iscrizione> iscrizioneList = iscrizioneDAO.findActiveIscrizione();
        List<IscrizioneDTO> iscrizioneDTOList = new ArrayList<>();
        for (Iscrizione iscrizione : iscrizioneList) {
            if (Objects.equals(iscrizione.getCourse().getTutor().getId(), id)) {
                iscrizioneDTOList.add(iscrizioneMapper.entityToDTO(iscrizione));
            }
        }
        if (!iscrizioneList.isEmpty()) {
            return iscrizioneDTOList;
        } else {
            throw new IscrizioneException("Subscription not found", 400);
        }
    }

    public List<UserDTO> getUserByCourse(Long id) throws IscrizioneException {
        List<Iscrizione> iscrizioneList = iscrizioneDAO.findActiveIscrizione();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (Iscrizione iscrizione : iscrizioneList) {
            if (Objects.equals(iscrizione.getCourse().getId(), id)) {
                userDTOList.add(userMapper.entityToDto(iscrizione.getUser()));
            }
        }
        return userDTOList;
    }


    public List<IscrizioneDTO> getSubscribedCourse(Long id) {
        List<Iscrizione> iscrizioneList = iscrizioneDAO.findCourseByUser(id);
        List<IscrizioneDTO> iscrizioneDTOList = new ArrayList<>();
        for (Iscrizione iscrizione : iscrizioneList) {
            iscrizioneDTOList.add(iscrizioneMapper.entityToDTO(iscrizione));
        }
        return iscrizioneDTOList;
    }
}