package com.security.template.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.template.model.CourseRating;

public interface CourseRatingRepo extends JpaRepository<CourseRating, Long> {

}
