package com.develhope.spring.services;

import com.develhope.spring.DAO.ReviewDAO;
import com.develhope.spring.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    public void addReview(Review review) {

        reviewDAO.saveAndFlush(review);
    }
}
