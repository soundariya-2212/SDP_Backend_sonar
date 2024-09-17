package com.security.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.template.model.LearningMaterial;
import com.security.template.repo.LearningMaterialRepo;

import java.util.List;
import java.util.Optional;

@Service
public class LearningMaterialService {

    @Autowired
    private LearningMaterialRepo learningMaterialRepo;

    public void saveMaterial(LearningMaterial learningMaterial) {
        learningMaterialRepo.save(learningMaterial);
    }

    public List<LearningMaterial> findByCourseId(Long courseId) {
        return learningMaterialRepo.findByCourseId(courseId);
    }


    public List<LearningMaterial> getAllMaterials() {
        return learningMaterialRepo.findAll();
    }

   
    public Optional<LearningMaterial> getMaterialById(Long id) {
        return learningMaterialRepo.findById(id);
    }

   
    public LearningMaterial createMaterial(LearningMaterial material) {
        return learningMaterialRepo.save(material);
    }

    public LearningMaterial updateMaterial(Long id, LearningMaterial updatedMaterial) {
        return learningMaterialRepo.findById(id)
            .map(material -> {
                material.setTitle(updatedMaterial.getTitle());
                material.setUrl(updatedMaterial.getUrl());
                material.setCourse(updatedMaterial.getCourse());
                return learningMaterialRepo.save(material);
            })
            .orElseThrow(() -> new RuntimeException("Material not found"));
    }

    
    public void deleteMaterial(Long id) {
        learningMaterialRepo.deleteById(id);
    }

    // public List<LearningMaterial> getAllMaterials() {
    //     return learningMaterialRepo.findAll();
    // }
}
