package com.develhope.spring.validators;

import com.develhope.spring.DAO.ReviewDAO;
import com.develhope.spring.models.DTO.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewValidator {

    @Autowired
    private ReviewDAO reviewDAO;

    public boolean isReviewValid(ReviewDTO r) {
        return isReviewNotNull(r); //&& studentExists(r) && courseExists(r) && isReviewNotEmpty(r));
    }

    private boolean isReviewNotNull(ReviewDTO r) {
        return (
                r.getStudent_id() != null &&
                r.getCourse_id() != null &&
                r.getReview() != null
                );
    }

    private boolean studentExists(ReviewDTO r) {
        return (reviewDAO.existsById(r.getStudent_id()));
    }

    private boolean courseExists(ReviewDTO r) {
        return (reviewDAO.existsById(r.getCourse_id()));
    }

    private boolean isReviewNotEmpty(ReviewDTO r) {
        return (r.getReview().length() > 1);
    }
}
