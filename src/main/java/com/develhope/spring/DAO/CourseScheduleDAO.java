package com.develhope.spring.DAO;

import com.develhope.spring.entities.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseScheduleDAO extends JpaRepository<CourseSchedule, Long> {
}
