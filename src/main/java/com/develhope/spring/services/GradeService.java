package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.GradeDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.Grade;
import com.develhope.spring.exceptions.GradeException;
import com.develhope.spring.mappers.GradeMapper;
import com.develhope.spring.models.DTO.GradeDTO;
import com.develhope.spring.validators.GradeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private GradeValidator validator;

    public GradeDTO addGrade(GradeDTO grade) throws GradeException {
        if (validator.isGradeValid(grade)) {
            Grade entity = gradeMapper.dtoToEntity(grade);
            Grade saved = gradeDAO.saveAndFlush(entity);
            return gradeMapper.entityToDto(saved);
        } else {
            throw new GradeException("Grade not added, a problem occurred with the data", 400);
        }
    }

    public List<GradeDTO> getAllGrade() {
        List<Grade> grades = gradeDAO.findAll();
        List<GradeDTO> gradesDTOList = new ArrayList<>();
        for (Grade grade : grades) {
            GradeDTO gradeDTO = gradeMapper.entityToDto(grade);
            gradesDTOList.add(gradeDTO);
        }
        return gradesDTOList;
    }

    public GradeDTO getGradeById(Long id) throws GradeException {
        Grade grade = gradeDAO.findById(id).orElse(null);
        if (grade != null) {
            return gradeMapper.entityToDto(grade);
        } else {
            throw new GradeException("Grade not found!", 404);
        }
    }

    public GradeDTO updateGradeById(Long id, GradeDTO gradeDTO) throws GradeException {
        Grade optionalGrade = gradeDAO.findById(id).orElse(null);
        if (optionalGrade != null) {
            optionalGrade.setStudent(userDAO.findById(gradeDTO.getStudent_id()).orElse(null));
            optionalGrade.setCourse(courseDAO.findById(gradeDTO.getCourse_id()).orElse(null));
            optionalGrade.setGrade(gradeDTO.getGrade());
            optionalGrade.setFinishedCourse(gradeDTO.getFinishedCourse());
            Grade gradeEdited = gradeDAO.saveAndFlush(optionalGrade);
            return gradeMapper.entityToDto(gradeEdited);
        } else {
            throw new GradeException("Grade not found!", 404);
        }
    }

    public void deleteGradeById(Long id) throws GradeException {
        if (gradeDAO.existsById(id)) {
            gradeDAO.deleteById(id);
        } else {
            throw new GradeException("Grade not found!", 404);
        }
    }

    public void deleteAllGrades() {
        gradeDAO.deleteAll();
    }

    public List<GradeDTO> getGradeByTutor(Long id) throws GradeException {
        List<Grade> gradeList = gradeDAO.findAll();
        List<GradeDTO> gradeDTOList = new ArrayList<>();
        for(Grade grade: gradeList){
            if(grade.getCourse().getTutor().getId() == id){  
              gradeDTOList.add(gradeMapper.entityToDto(grade));
            }
        }
        if(!gradeDTOList.isEmpty()){
            return gradeDTOList;
        }else{
            throw new GradeException("no grades found", 404);
        }
    }

}
