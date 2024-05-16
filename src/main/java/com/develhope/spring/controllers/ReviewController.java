package com.develhope.spring.controllers;

import com.develhope.spring.entities.Review;
import com.develhope.spring.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public void postReview(@RequestBody Review review) {
        reviewService.addReview(review);
    }
}
