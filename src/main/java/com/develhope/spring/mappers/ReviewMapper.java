package com.develhope.spring.mappers;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.Review;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CourseDAO courseDAO;


    public Review dtoToEntity(ReviewDTO reviewMapperDTO) throws UserException, CourseException {
        Review review = new Review();
        review.setId(reviewMapperDTO.getId());
        review.setStudent(userDAO.findById(reviewMapperDTO.getStudent_id()).orElseThrow(() -> new UserException("Student not found!", 404)));
        review.setCourse(courseDAO.findById(reviewMapperDTO.getCourse_id()).orElseThrow(() -> new CourseException("Course not found!", 404)));
        review.setReview(reviewMapperDTO.getReview());
        return review;
    }

    public ReviewDTO entityToDto(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setStudent_id(review.getStudent().getId());
        reviewDTO.setCourse_id(review.getCourse().getId());
        reviewDTO.setReview(review.getReview());
        return reviewDTO;
    }
}
