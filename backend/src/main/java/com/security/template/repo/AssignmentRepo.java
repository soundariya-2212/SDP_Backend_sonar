package com.security.template.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.template.model.Assignment;

public interface AssignmentRepo extends JpaRepository<Assignment, Long> {

}
