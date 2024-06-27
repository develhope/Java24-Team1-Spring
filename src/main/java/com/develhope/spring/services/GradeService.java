package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.GradeDAO;
import com.develhope.spring.DAO.IscrizioneDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.CourseSchedule;
import com.develhope.spring.entities.Grade;
import com.develhope.spring.entities.Iscrizione;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.CourseScheduleException;
import com.develhope.spring.exceptions.GradeException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.mappers.GradeMapper;
import com.develhope.spring.models.DTO.GradeDTO;
import com.develhope.spring.validators.GradeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GradeService {
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private GradeDAO gradeDAO;
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private IscrizioneDAO iscrizioneDAO;
    @Autowired
    private GradeValidator validator;


    public GradeDTO addGrade(GradeDTO grade, String username) throws GradeException, CourseException, UserException {
        if (validator.isGradeValid(grade)) {
            Grade entity = gradeMapper.dtoToEntity(grade);
            List<Iscrizione> iscrizione = iscrizioneDAO.findCourseByCourse(entity.getCourse().getId());
            for(Iscrizione sub : iscrizione) {
                if (sub.getUser().equals(entity.getStudent()) && entity.getCourse().getTutor().getUsername().equals(username)) {
                    Grade saved = gradeDAO.saveAndFlush(entity);
                    return gradeMapper.entityToDto(saved);
                }
            }
            throw new GradeException("Grade not added, you are not the owner of the course or the student is not subbed to the course.", 400);
        }
        throw new GradeException("Grade not added, a problem occurred with the data", 400);
    }

    public List<GradeDTO> getAllGrade() {
        List<Grade> grades = gradeDAO.findActiveGrade();
        List<GradeDTO> gradesDTOList = new ArrayList<>();
        for (Grade grade : grades) {
            GradeDTO gradeDTO = gradeMapper.entityToDto(grade);
            gradesDTOList.add(gradeDTO);
        }
        return gradesDTOList;
    }

    public GradeDTO getGradeById(Long id, String username) throws GradeException {
        Grade grade = gradeDAO.findById(id).orElseThrow(() -> new GradeException("This grade does not exist!", 400));
        if(grade.getStudent().getUsername().equals(username) || grade.getCourse().getTutor().getUsername().equals(username)) {
            return gradeMapper.entityToDto(grade);
        }
        throw new GradeException("YOU SHALL NOT PASS!!!", 400);
    }

    public GradeDTO updateGradeById(Long id, GradeDTO gradeDTO, String username) throws GradeException {
        Grade optionalGrade = gradeDAO.findById(id).orElseThrow(() -> new GradeException("This grade does not exist!", 400));
        if (optionalGrade != null && optionalGrade.getCourse().getTutor().getUsername().equals(username)) {
            optionalGrade.setGrade(gradeDTO.getGrade());
            optionalGrade.setFinishedCourse(gradeDTO.getFinishedCourse());
            Grade gradeEdited = gradeDAO.saveAndFlush(optionalGrade);
            return gradeMapper.entityToDto(gradeEdited);
        }
            throw new GradeException("This grade does not exist or you are not the owner of the course!", 400);
    }

    public void deleteGradeById(Long id) throws GradeException {
        Grade grade = gradeDAO.findById(id).orElseThrow(() -> new GradeException("This Course Schedule does not exist!", 400));
        if(!grade.getIsDeleted()) {
            grade.setIsDeleted(true);
            gradeDAO.saveAndFlush(grade);
        } else {
            throw new GradeException("This grade does not exist!", 400);
        }
    }

    public void deleteYourGradeById(Long id, String username) throws GradeException {
        Grade grade = gradeDAO.findById(id).orElseThrow(() -> new GradeException("This Course Schedule does not exist!", 400));
        if(!grade.getIsDeleted() && grade.getCourse().getTutor().getUsername().equals(username)) {
            grade.setIsDeleted(true);
            gradeDAO.saveAndFlush(grade);
        } else {
            throw new GradeException("This grade does not exist or you are not the owner of the course!", 400);
        }
    }


    public List<GradeDTO> getGradeByTutor(String username) throws GradeException {
        List<Grade> gradeList = gradeDAO.findAllTutorGrades(username);
        List<GradeDTO> gradeDTOList = new ArrayList<>();
        for(Grade grade: gradeList){
              gradeDTOList.add(gradeMapper.entityToDto(grade));
        }
        return gradeDTOList;
    }

    public List<GradeDTO> getAllStudentsGrades(String username){
        List<Grade> gradeList = gradeDAO.findAllStudentGrades(username);
        List<GradeDTO> gradeDTOList = new ArrayList<>();
        for(Grade grade : gradeList){
            gradeDTOList.add(gradeMapper.entityToDto(grade));
        }
        return gradeDTOList;
    }

}
