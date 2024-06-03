package com.develhope.spring.controllers;

import com.develhope.spring.entities.Review;
import com.develhope.spring.exceptions.CourseException;
import com.develhope.spring.exceptions.ReviewException;
import com.develhope.spring.exceptions.UserException;
import com.develhope.spring.models.DTO.CourseDTO;
import com.develhope.spring.models.DTO.ReviewDTO;
import com.develhope.spring.models.DTO.UserDTO;
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

    @PostMapping("/")
    public ResponseEntity<Response> postReview(@RequestBody ReviewDTO review) {
        try {
            ReviewDTO newReview = reviewService.addReview(review);
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "student: " + newReview.getStudent_id() + " added a review to course: " + newReview.getCourse_id(),
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

    @GetMapping("/list")
    public List<ReviewDTO> getAllReview() {
        return reviewService.getAllReview();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getReviewById(@PathVariable Long id) {
        Optional<Review> r = reviewService.getReviewById(id);
        if(r.isPresent()){
            return ResponseEntity.ok().body(
                    new Response(200,
                            "Review found: ",
                            r)
            );
        }else{
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            "Rievew not found, Id invalid"
                    )
            );
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateReviewById(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO){
        try{
            reviewService.updateReviewById(id, reviewDTO);
            return ResponseEntity.ok().body(new Response(200, "review updated",reviewDTO));
        }catch(ReviewException e){
            return ResponseEntity.status(404).body(new Response(404, "review id not found"));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteReviewById(@PathVariable Long id){
        try{
           reviewService.deleteReviewById(id);
            return ResponseEntity.ok().body(new Response(200, "review deleted"));
        }catch (ReviewException e){
            return  ResponseEntity.status(404).body(new Response(404, "review id not found"));
        }
    }
    @DeleteMapping("/list")
    public void deleteAllReviews(){
       reviewService.deleteAllReviews();
    }
    @GetMapping("/tutor{id}")
    public ResponseEntity<Response> getReviewByTutor(@PathVariable Long id){
        try {
            List<ReviewDTO> reviews = reviewService.getReviewByTutor(id);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "review found: ",
                            reviews)
            );
        } catch (ReviewException e) {
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            e.getMessage()
                    )
            );
        }
    }
}
