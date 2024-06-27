package com.develhope.spring.controllers;


import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.ReviewException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.ReviewDTO;
import com.develhope.spring.models.Response;
import com.develhope.spring.models.ResponseInvalid;
import com.develhope.spring.models.ResponseValid;
import com.develhope.spring.services.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.develhope.spring.utilities.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private JWTUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @PostMapping
    public ResponseEntity<Response> postReview(@RequestBody ReviewDTO review, @RequestHeader("Authorization") String authHeader) {
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            ReviewDTO newReview = reviewService.addReview(review, username);
            logger.info("Recensione inserita"+ newReview);
            return ResponseEntity.ok().body(
                    new ResponseValid(
                            200,
                            "student: " + newReview.getStudent_id() + " added a review to course: " + newReview.getCourse_id(),
                            newReview)
            );
        } catch (ReviewException | CourseException | UserException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping
    public ResponseEntity<Response> getAllReview() {
        try {
            List<ReviewDTO> reviews = reviewService.getAllReview();
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "List of reviews: ",
                            reviews)
            );
        } catch (Exception e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getReviewById(@PathVariable Long id) {
        try {
            ReviewDTO r = reviewService.getReviewById(id);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "Review found: ",
                            r)
            );
        } catch (ReviewException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            "Rievew not found, Id invalid"
                    )
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateReviewById(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO, @RequestHeader("Authorization") String authHeader) {
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            ReviewDTO reviewDTOsaved = reviewService.updateReviewById(id, reviewDTO, username);
            return ResponseEntity.ok().body(new ResponseValid(200, "review updated", reviewDTOsaved));
        } catch (ReviewException | UserException | CourseException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @DeleteMapping("/a/{id}")
    public ResponseEntity<Response> deleteReviewById(@PathVariable Long id) {
        try {
            reviewService.deleteReviewById(id);
            return ResponseEntity.ok().body(new Response(200, "review deleted"));
        } catch (ReviewException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "review id not found"));
        }
    }

    @DeleteMapping("/me/{id}")
    public ResponseEntity<Response> deleteYourReviewById(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        String token = jwtUtil.parseJwt(authHeader);
        String username = jwtUtil.extractUsername(token);
        try {
            reviewService.deleteYourReviewById(id, username);
            return ResponseEntity.ok().body(new Response(200, "review deleted"));
        } catch (ReviewException e) {
            return ResponseEntity.status(400).body(new Response(400, "review id not found"));
        }
    }

    @GetMapping("/tutor/{id}")
    public ResponseEntity<Response> getReviewByTutor(@PathVariable Long id) {
        try {
            List<ReviewDTO> reviews = reviewService.getReviewByTutor(id);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "review found: ",
                            reviews)
            );
        } catch (ReviewException e) {
            logger.error("errore " + e.getMessage());
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Response> getReviewCourse(@PathVariable Long id) {
            List<ReviewDTO> reviews = reviewService.getReviewCourse(id);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "List course review: ",
                            reviews)
            );
    }

}
