package com.develhope.spring.DAO;

import com.develhope.spring.entities.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseScheduleDAO extends JpaRepository<CourseSchedule, Long> {
    
    @Query("SELECT cs FROM CourseSchedule cs WHERE cs.isDeleted = False")
    List<CourseSchedule> findActiveCourseSchedule();
}
