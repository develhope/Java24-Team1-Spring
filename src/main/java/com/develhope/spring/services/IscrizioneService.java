package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.IscrizioneDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.*;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.IscrizioneException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.mappers.IscrizioneMapper;
import com.develhope.spring.mappers.UserMapper;
import com.develhope.spring.models.DTO.IscrizioneDTO;
import com.develhope.spring.models.DTO.requestDTO.UserRequestDTO;
import com.develhope.spring.models.DTO.responseDTO.UserResponseDTO;
import com.develhope.spring.validators.IscrizioneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public IscrizioneDTO subscribeToCourse(Long idCourse, String username) throws Exception {

        User user = userDAO.findByUsername(username).orElseThrow(() -> new UserException("This user does not exist!", 400));
        Course course = courseDAO.findById(idCourse).orElseThrow(() -> new CourseException("This course does not exist!", 400));

        if (iscrizioneValidator.isIscrizioneValid(user, course)) {
            Iscrizione iscrizione = new Iscrizione();
            iscrizione.setCourse(course);
            iscrizione.setUser(user);
            Date dataIscrizione = new Date();
            iscrizione.setDataIscrizione(dataIscrizione.toString());
            iscrizione.setPayed(false);
            Iscrizione saved = iscrizioneDAO.saveAndFlush(iscrizione);

            //richiama metodo di UserTokenService; DA USCIRE DA QUI O FARE UN CONTROLLO SE GIà ESISTE TOKEN!////////////////////////////////////////////////////////////////////////////////////////////////////
            UserToken userToken;
            if (userTokenService.findByUserId(user) != null) {
                userToken = userTokenService.findByUserId(user);
            } else {
                userToken = userTokenService.createUserToken(user);
            }


            //Crea List di EVENT da CourseSchedule utilizzando un servizio che torna una lista di eventi di un corso;
            List<CourseSchedule> cs = courseScheduleService.getAllCourseScheduleforEvent(course.getId());

            //richiama metodo AddEvent;
            googleCalendarService.addEvent(userToken, cs);

            return iscrizioneMapper.entityToDTO(saved);
        } else {
            throw new IscrizioneException("Subscription not added, a problem occurred with the data", 400);
        }
    }


    public IscrizioneDTO getById(Long id) throws IscrizioneException {
        Iscrizione iscrizione = iscrizioneDAO.findById(id).orElseThrow(() -> new IscrizioneException("This subscription does not exist!", 400));
        if (iscrizione != null) {
            return iscrizioneMapper.entityToDTO(iscrizione);
        } else {
            throw new IscrizioneException("This subscription does not exist!", 400);
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

    public IscrizioneDTO payedSwitch(Long id, String username) throws IscrizioneException {
        Iscrizione iscrizione = iscrizioneDAO.findById(id).orElseThrow(() -> new IscrizioneException("This subscription does not exist!", 400));
        if (iscrizione.getCourse().getTutor().getUsername().equals(username)) {
            iscrizione.setPayed(!iscrizione.getPayed());
            Iscrizione saved = iscrizioneDAO.saveAndFlush(iscrizione);
            return iscrizioneMapper.entityToDTO(saved);
        } else {
            throw new IscrizioneException("You are not the owner of this course", 400);
        }
    }

    public void deleteSubscription(Long id) throws IscrizioneException {
        Iscrizione iscrizione = iscrizioneDAO.findById(id).orElseThrow(() -> new IscrizioneException("This subscription does not exist!", 400));
        if (!iscrizione.getIsDeleted()) {
            iscrizione.setIsDeleted(true);
            iscrizioneDAO.saveAndFlush(iscrizione);
        } else {
            throw new IscrizioneException("This subscription does not exist!", 400);
        }
    }

    public void deleteYourSubscription(Long id, String username) throws IscrizioneException {
        Iscrizione iscrizione = iscrizioneDAO.findById(id).orElseThrow(() -> new IscrizioneException("This subscription does not exist!", 400));
        if (!iscrizione.getIsDeleted() && iscrizione.getUser().getUsername().equals(username)) {
            iscrizione.setIsDeleted(true);
            iscrizioneDAO.saveAndFlush(iscrizione);
        }else {
            throw new IscrizioneException("This subscription does not exist or you are not the owner!", 400);
        }

    }

    public List<IscrizioneDTO> getAllByTutor(Long id) throws IscrizioneException {
        List<Iscrizione> iscrizioneList = iscrizioneDAO.findCourseByTutor(id);
        List<IscrizioneDTO> iscrizioneDTOList = new ArrayList<>();
        for (Iscrizione iscrizione : iscrizioneList) {
            iscrizioneDTOList.add(iscrizioneMapper.entityToDTO(iscrizione));
        }
        return iscrizioneDTOList;
    }

    public List<UserResponseDTO> getUserByCourse(Long id) throws IscrizioneException {
        List<Iscrizione> iscrizioneList = iscrizioneDAO.findCourseByCourse(id);
        List<UserResponseDTO> userDTOList = new ArrayList<>();
        for (Iscrizione iscrizione : iscrizioneList) {
            userDTOList.add(userMapper.entityToDto(iscrizione.getUser()));
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