package com.security.template.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.template.model.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {
   
}
