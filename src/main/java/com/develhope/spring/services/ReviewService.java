package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.IscrizioneDAO;
import com.develhope.spring.DAO.ReviewDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.Iscrizione;
import com.develhope.spring.entities.Review;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.ReviewException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.mappers.ReviewMapper;
import com.develhope.spring.models.DTO.ReviewDTO;
import com.develhope.spring.validators.ReviewValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private IscrizioneDAO iscrizioneDAO;

    public ReviewDTO addReview(ReviewDTO review, String username) throws ReviewException, CourseException, UserException {
        if (validator.isReviewValid(review)) {

            Review entity = reviewMapper.dtoToEntity(review);
            entity.setStudent(userDAO.findByUsername(username).orElseThrow(() -> new UserException("User not found", 400)));
            List<Iscrizione> iscrizione = iscrizioneDAO.findCourseByCourse(entity.getCourse().getId());
            for(Iscrizione sub : iscrizione){
                if(sub.getUser().equals(entity.getStudent())){
                    Review saved = reviewDAO.saveAndFlush(entity);
                    return reviewMapper.entityToDto(saved);
                }
            }
            throw new ReviewException("You are not subbed to this course", 400);
        }
        throw new ReviewException("Review not added, a problem occurred with the data", 400);

    }

    public List<ReviewDTO> getAllReview() {
        List<Review> reviews = reviewDAO.findActiveReview();
        List<ReviewDTO> reviewsDTOList = new ArrayList<>();
        for (Review review : reviews) {
            ReviewDTO reviewDTO = reviewMapper.entityToDto(review);
            reviewsDTOList.add(reviewDTO);
        }
        return reviewsDTOList;
    }

    public ReviewDTO getReviewById(Long id) throws ReviewException {
        Review review = reviewDAO.findById(id).orElseThrow(() -> new ReviewException("Review not found!", 400));
        if (review != null) {
            return reviewMapper.entityToDto(review);
        } else {
            throw new ReviewException("Review not found!", 404);
        }
    }

    public ReviewDTO updateReviewById(Long id, ReviewDTO reviewDTO, String username) throws ReviewException, UserException, CourseException {
        Review optionalReview = reviewDAO.findById(id).orElseThrow(() -> new ReviewException("Review not found!", 400));
        if (optionalReview != null && optionalReview.getStudent().getUsername().equals(username)) {
            optionalReview.setReview(reviewDTO.getReview());
            Review reviewEdited = reviewDAO.saveAndFlush(optionalReview);
            return reviewMapper.entityToDto(reviewEdited);
        }
        throw new ReviewException("Review not found or you are not the owner of this review!", 400);

    }

    public void deleteReviewById(Long id) throws ReviewException {
        Review review = reviewDAO.findById(id).orElseThrow(() -> new ReviewException("Review not found!", 400));
        if (!review.getIsDeleted()) {
            review.setIsDeleted(true);
            reviewDAO.saveAndFlush(review);
        } else {
            throw new ReviewException("user id not found", 400);
        }
    }

    public void deleteYourReviewById(Long id, String username) throws ReviewException {
        Review review = reviewDAO.findById(id).orElseThrow(() -> new ReviewException("Review not found!", 400));
        if (!review.getIsDeleted() && review.getStudent().getUsername().equals(username)) {
            review.setIsDeleted(true);
            reviewDAO.saveAndFlush(review);
        }
        throw new ReviewException("user id not found or you are not the owner of this review", 400);

    }


    public List<ReviewDTO> getReviewByTutor(Long id) throws ReviewException {
        List<Review> reviewList = reviewDAO.findReviewTutor(id);
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for (Review review : reviewList) {
            reviewDTOList.add(reviewMapper.entityToDto(review));
        }
        return reviewDTOList;
    }

    public List<ReviewDTO> getReviewCourse(Long id) {
        List<Review> reviewList = reviewDAO.findReviewCourse(id);
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for (Review review : reviewList) {
            reviewDTOList.add(reviewMapper.entityToDto(review));
        }
        return reviewDTOList;
    }
}
