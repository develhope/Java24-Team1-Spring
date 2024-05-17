package com.develhope.spring.controllers;

import com.develhope.spring.DAO.ReviewDAO;
import com.develhope.spring.entities.Review;
import com.develhope.spring.exceptions.ReviewException;
import com.develhope.spring.models.DTO.ReviewDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewDAO reviewDAO;

    @PostMapping("/add")
    public ResponseEntity<Response> postReview(@RequestBody ReviewDTO review) throws ReviewException {
        try {
            ReviewDTO newReview = reviewService.addReview(review);
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "student: " + newReview.getStudent() + " added a review to course: " + newReview.getCourse(),
                            newReview)
            );
        } catch (ReviewException e) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/all")
    public List<Review> getAllReview() {
        return reviewDAO.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Review> getReviewById(@PathVariable Long id) {
        return reviewDAO.findById(id);
    }
}
