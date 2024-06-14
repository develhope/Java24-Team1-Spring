package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.GradeDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.CourseSchedule;
import com.develhope.spring.entities.Grade;
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
    private GradeValidator validator;

    public GradeDTO addGrade(GradeDTO grade) throws GradeException, CourseException, UserException {
        if (validator.isGradeValid(grade)) {
            Grade entity = gradeMapper.dtoToEntity(grade);
            Grade saved = gradeDAO.saveAndFlush(entity);
            return gradeMapper.entityToDto(saved);
        } else {
            throw new GradeException("Grade not added, a problem occurred with the data", 400);
        }
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

    public GradeDTO getGradeById(Long id) throws GradeException {
        Grade grade = gradeDAO.findById(id).orElseThrow(() -> new GradeException("Grade not found!", 404));
        return gradeMapper.entityToDto(grade);
    }

    public GradeDTO updateGradeById(Long id, GradeDTO gradeDTO) throws GradeException, UserException, CourseException {
        Grade optionalGrade = gradeDAO.findById(id).orElseThrow(() -> new GradeException("Grade not found!", 404));
        if (optionalGrade != null) {
            optionalGrade.setStudent(userDAO.findById(gradeDTO.getStudent_id()).orElseThrow(() -> new UserException("Student not found!", 404)));
            optionalGrade.setCourse(courseDAO.findById(gradeDTO.getCourse_id()).orElseThrow(() -> new CourseException("Course not found!", 404)));
            optionalGrade.setGrade(gradeDTO.getGrade());
            optionalGrade.setFinishedCourse(gradeDTO.getFinishedCourse());
            Grade gradeEdited = gradeDAO.saveAndFlush(optionalGrade);
            return gradeMapper.entityToDto(gradeEdited);
        } else {
            throw new GradeException("Grade not found!", 404);
        }
    }

    public void deleteGradeById(Long id) throws GradeException {
        Grade grade = gradeDAO.findById(id).orElseThrow(() -> new GradeException("Course Schedule not found!", 404));
        if(!grade.getIsDeleted()) {
            grade.setIsDeleted(true);
            gradeDAO.saveAndFlush(grade);
        } else {
            throw new GradeException("Grade not found!", 404);
        }
    }


    public List<GradeDTO> getGradeByTutor(Long id) throws GradeException {
        List<Grade> gradeList = gradeDAO.findActiveGrade();
        List<GradeDTO> gradeDTOList = new ArrayList<>();
        for(Grade grade: gradeList){
            if(Objects.equals(grade.getCourse().getTutor().getId(), id)){
              gradeDTOList.add(gradeMapper.entityToDto(grade));
            }
        }
        if(!gradeDTOList.isEmpty()){
            return gradeDTOList;
        }else{
            throw new GradeException("no grades found", 404);
        }
    }
    public List<GradeDTO> getAllStudentsGrades(Long id){
        List<Grade> gradeList = gradeDAO.findAllStudentGrades(id);
        List<GradeDTO> gradeDTOList = new ArrayList<>();
        for(Grade grade : gradeList){
            gradeDTOList.add(gradeMapper.entityToDto(grade));
        }
        return gradeDTOList;
    }

}
