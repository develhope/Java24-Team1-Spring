package com.develhope.spring.DAO;

import com.develhope.spring.entities.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseScheduleDAO extends JpaRepository<CourseSchedule, Long> {
    
    @Query("SELECT cs FROM CourseSchedule cs WHERE cs.isDeleted = false")
    List<CourseSchedule> findActiveCourseSchedule();

    @Query("SELECT cs FROM CourseSchedule cs WHERE cs.isDeleted = false AND cs.course.id = :id")
    List<CourseSchedule> findActiveCourseScheduleByCourse(@Param("id") Long id);

}
