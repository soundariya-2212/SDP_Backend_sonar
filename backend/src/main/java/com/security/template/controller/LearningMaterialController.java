package com.security.template.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.security.template.model.Course;
import com.security.template.model.LearningMaterial;
import com.security.template.service.CourseService;
import com.security.template.service.LearningMaterialService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/learning-materials")
public class LearningMaterialController {

    @Autowired
    private LearningMaterialService learningMaterialService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/materials")
    public ResponseEntity<String> addMaterial(@RequestBody LearningMaterial learningMaterial) {
        try {
            learningMaterialService.saveMaterial(learningMaterial);
            return ResponseEntity.ok("Material added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding material");
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }


    @GetMapping("/materials/{id}")
    public ResponseEntity<LearningMaterial> getMaterialById(@PathVariable Long id) {
        Optional<LearningMaterial> material = learningMaterialService.getMaterialById(id);
        return material.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/materials/{id}")
    public ResponseEntity<LearningMaterial> updateMaterial(
            @PathVariable Long id, @RequestBody LearningMaterial updatedMaterial) {
        try {
            LearningMaterial material = learningMaterialService.updateMaterial(id, updatedMaterial);
            return ResponseEntity.ok(material);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/materials")
    public List<LearningMaterial> getAllMaterials() {
        return learningMaterialService.getAllMaterials();
    }
    
    @DeleteMapping("/materials/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        learningMaterialService.deleteMaterial(id);
        return ResponseEntity.noContent().build();
    }
}
