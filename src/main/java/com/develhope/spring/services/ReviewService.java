package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.ReviewDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.Review;
import com.develhope.spring.entities.User;
import com.develhope.spring.exceptions.ReviewException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.mappers.ReviewMapper;
import com.develhope.spring.models.DTO.ReviewDTO;
import com.develhope.spring.models.DTO.UserDTO;
import com.develhope.spring.validators.ReviewValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ReviewDAO reviewDAO;
    @Autowired
    private ReviewValidator validator;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CourseDAO courseDAO;

    public ReviewDTO addReview(ReviewDTO review) throws ReviewException {
        if (validator.isReviewValid(review)) {
            Review entity = reviewMapper.dtoToEntity(review);
            Review saved = reviewDAO.saveAndFlush(entity);
            return reviewMapper.entityToDto(saved);
        } else {
            throw new ReviewException("Review not added, a problem occurred with the data", 400);
        }
    }

    public List<ReviewDTO> getAllReview() {
        List<Review> reviews = reviewDAO.findAll();
        List<ReviewDTO> reviewsDTOList = new ArrayList<>();
        for (Review review : reviews) {
            ReviewDTO reviewDTO = reviewMapper.entityToDto(review);
            reviewsDTOList.add(reviewDTO);
        }
        return reviewsDTOList;
    }

    public ReviewDTO getReviewById(Long id) throws ReviewException {
        Review review = reviewDAO.findById(id).orElse(null);
        if (review != null) {
            return reviewMapper.entityToDto(review);
        } else {
            throw new ReviewException("Review not found!", 404);
        }
    }

    public ReviewDTO updateReviewById(Long id, ReviewDTO reviewDTO) throws ReviewException {
        Review optionalReview = reviewDAO.findById(id).orElse(null);
        if (optionalReview != null) {
            optionalReview.setReview(reviewDTO.getReview());
            optionalReview.setStudent(userDAO.findById(reviewDTO.getStudent_id()).orElse(null));
            optionalReview.setCourse(courseDAO.findById(reviewDTO.getCourse_id()).orElse(null));
            Review reviewEdited = reviewDAO.saveAndFlush(optionalReview);
            return reviewMapper.entityToDto(reviewEdited);
        } else {
            throw new ReviewException("Review not found!", 404);
        }
    }

    public void deleteReviewById(Long id) throws ReviewException {
        if(reviewDAO.existsById(id)) {
            reviewDAO.deleteById(id);
        }else{
            throw  new ReviewException("User not found", 404);
        }
    }

    public void deleteAllReviews() {
        reviewDAO.deleteAll();
    }

}
