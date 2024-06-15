package com.develhope.spring.DAO;

import com.develhope.spring.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDAO extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.course.id = :id AND r.isDeleted = false")
    List<Review> findReviewCourse(@Param("id")Long id);

    @Query("SELECT r FROM Review r WHERE r.course.tutor.id = :id AND r.isDeleted = false")
    List<Review> findReviewTutor(@Param("id")Long id);

    @Query("SELECT r FROM Review r WHERE r.isDeleted = false")
    List<Review> findActiveReview();
}
