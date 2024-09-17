package com.security.template.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// import com.security.template.model.Course;
import com.security.template.model.Enrollment;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {

    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.course.id = :courseId")
    long countByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.user.id = :userId")
    long countEnrollmentsByUserId(@Param("userId") Long userId);
}
