package com.security.template.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;

// import org.springframework.beans.factory.annotation.Autowired;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.security.template.model.Enrollment;
// import com.security.template.service.EnrollmentService;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/enroll")
// public class EnrollmentController {

//     @Autowired
//     private EnrollmentService enrollmentService;

//     @GetMapping
//     public List<Enrollment> getAllEnrollments() {
//         return enrollmentService.getAllEnrollments();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
//         Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(id);
//         return enrollment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     @PostMapping
//     public ResponseEntity<Enrollment> enrollCourse(@RequestParam Long courseID,
//                                                     @RequestParam Long userID) {
//         Enrollment enrollment = enrollmentService.enrollCourse(courseID, userID);
//         return ResponseEntity.ok(enrollment);
//     }

//     @PutMapping("/status")
//     public ResponseEntity<Enrollment> updateEnrollmentStatus(@RequestParam Long courseID,
//                                                               @RequestParam Long userID,
//                                                               @RequestParam Boolean isEnrolled) {
//         Enrollment updatedEnrollment = enrollmentService.updateEnrollmentStatus(courseID, userID, isEnrolled);
//         return updatedEnrollment != null ? ResponseEntity.ok(updatedEnrollment) : ResponseEntity.notFound().build();
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
//         boolean isDeleted = enrollmentService.deleteEnrollment(id);
//         return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
//     }
// }


import org.springframework.web.bind.annotation.*;

// import com.security.template.model.Enrollment;
import com.security.template.service.EnrollmentService;

@RestController
@RequestMapping("/enroll")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<String> enrollCourse(
            @RequestParam Long courseId,
            @RequestParam Long userId,
            @RequestParam String userEmail) {

        boolean success = enrollmentService.enrollUser(courseId, userId, userEmail);
        if (success) {
            return ResponseEntity.ok("Enrollment successful");
        } else {
            return ResponseEntity.badRequest().body("Failed to enroll in course");
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalEnrollmentsByUserId(@RequestParam("userId") Long userId) {
        long count = enrollmentService.getTotalEnrollmentsByUserId(userId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/by-course")
    public ResponseEntity<Map<String, Long>> getEnrollmentsByCourse() {
        Map<String, Long> enrollmentsByCourse = enrollmentService.getEnrollmentsByCourse();
        return ResponseEntity.ok(enrollmentsByCourse);
    }
}
