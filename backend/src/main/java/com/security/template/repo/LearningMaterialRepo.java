package com.security.template.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.template.model.LearningMaterial;

public interface LearningMaterialRepo extends JpaRepository<LearningMaterial, Long> {
    
    List<LearningMaterial> findByCourseId(Long courseId);
}
