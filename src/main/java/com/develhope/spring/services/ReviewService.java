package com.develhope.spring.services;

import com.develhope.spring.DAO.ReviewDAO;
import com.develhope.spring.entities.Review;
import com.develhope.spring.exceptions.ReviewException;
import com.develhope.spring.models.DTO.ReviewDTO;
import com.develhope.spring.validators.ReviewValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReviewDAO reviewDAO;
    @Autowired
    private ReviewValidator validator;

    public ReviewDTO addReview(ReviewDTO review) throws ReviewException {
        if(validator.isReviewValid(review)){
            Review entity = modelMapper.map(review, Review.class);
            Review saved = reviewDAO.saveAndFlush(entity);
            modelMapper.map(saved, review);
            return review;
        } else {
            throw new ReviewException("Review not added, a problem occurred with the data", 400);
        }
    }
}
