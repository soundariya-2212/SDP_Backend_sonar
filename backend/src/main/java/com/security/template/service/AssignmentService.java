package com.security.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.security.template.model.Assignment;
import com.security.template.model.Course;
import com.security.template.repo.AssignmentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepo assignmentRepository;

    @Autowired
    private CourseService courseService;

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public ResponseEntity<Assignment> getAssignmentById(Long id) {
        Optional<Assignment> assignment = assignmentRepository.findById(id);
        return assignment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Assignment createAssignment(Assignment assignment, Long courseID) {
        Course courseObj = courseService.getCourseById(courseID); // This should now return a Course, not
                                                                  // ResponseEntity<Course>
        assignment.setCourse(courseObj);
        return assignmentRepository.save(assignment);
    }

    public ResponseEntity<Assignment> updateAssignment(Long id, Assignment assignment) {
        if (assignmentRepository.existsById(id)) {
            assignment.setId(id);
            return ResponseEntity.ok(assignmentRepository.save(assignment));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deleteAssignment(Long id) {
        if (assignmentRepository.existsById(id)) {
            assignmentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
