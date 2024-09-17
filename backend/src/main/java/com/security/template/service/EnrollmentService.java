package com.security.template.service;

// import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// // import com.security.template.model.Course;
// import com.security.template.model.Enrollment;
// // import com.security.template.model.User;
// import com.security.template.repo.EnrollmentRepo;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class EnrollmentService {

//     @Autowired
//     private EnrollmentRepo enrollmentRepository;

//     // @Autowired
//     // private UserService userService;

//     // @Autowired
//     // private CourseService courseService;

//     public List<Enrollment> getAllEnrollments() {
//         return enrollmentRepository.findAll();
//     }

//     public Optional<Enrollment> getEnrollmentById(Long id) {
//         return enrollmentRepository.findById(id);
//     }

//     // public Enrollment createEnrollment(Enrollment enrollment, Long courseID, Long userID) {
//     //     // User user = userService.getUserById(userID);
//     //     // enrollment.setUser(user);
//     //     Course course = courseService.getCourseById(courseID);
//     //     enrollment.setCourse(course);
//     //     return enrollmentRepository.save(enrollment);
//     // }

//     public Enrollment enrollCourse(Long courseID, Long userID) {
//         // Find if an enrollment record exists
//         Enrollment enrollment = enrollmentRepository.findByCourseIdAndUserId(courseID, userID);

//         if (enrollment == null) {
//             // Create a new enrollment record
//             enrollment = new Enrollment();
//             enrollment.setId(courseID);
//             enrollment.setId(userID);
//             enrollment.setIsEnrolled(true); // Set isEnrolled to true
//         } else {
//             // Update existing enrollment record
//             enrollment.setIsEnrolled(true); // Ensure isEnrolled is true
//         }

//         return enrollmentRepository.save(enrollment);
//     }

//     // public Enrollment updateEnrollment(Long id, Enrollment enrollment) {
//     //     if (enrollmentRepository.existsById(id)) {
//     //         enrollment.setId(id);
//     //         return enrollmentRepository.save(enrollment);
//     //     } else {
//     //         return null;
//     //     }
//     // }

//     public Enrollment updateEnrollmentStatus(Long courseID, Long userID, Boolean isEnrolled) {
//         // Fetch existing enrollment record
//         Enrollment enrollment = enrollmentRepository.findByCourseIdAndUserId(courseID, userID);
//         if (enrollment != null) {
//             enrollment.setIsEnrolled(isEnrolled);
//             return enrollmentRepository.save(enrollment);
//         }
//         return null;
//     }

//     public boolean deleteEnrollment(Long id) {
//         if (enrollmentRepository.existsById(id)) {
//             enrollmentRepository.deleteById(id);
//             return true;
//         } else {
//             return false;
//         }
//     }
// }


import org.springframework.stereotype.Service;

import com.security.template.model.Course;
import com.security.template.model.Enrollment;
import com.security.template.model.User;
import com.security.template.repo.CourseRepo;
import com.security.template.repo.EnrollmentRepo;
import com.security.template.repo.UserRepo;

@Service
public class EnrollmentService {

    private final EnrollmentRepo enrollmentRepo;
    private final UserRepo userRepo;
    private final CourseRepo courseRepo;

    public EnrollmentService(EnrollmentRepo enrollmentRepo, UserRepo userRepo, CourseRepo courseRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
    }

    public boolean enrollUser(Long courseId, Long userId, String userEmail) {
        // Check if the course exists
        Course course = courseRepo.findById(courseId).orElse(null);
        if (course == null) {
            return false;
        }

        // Check if the user exists
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return false;
        }

        // Create and save the enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setUser(user);
        enrollment.setStatus("Enrolled");

        enrollmentRepo.save(enrollment);
        return true;
    }


    public long getTotalEnrollmentsByUserId(Long userId) {
        return enrollmentRepo.countEnrollmentsByUserId(userId);
    }

    public Map<String, Long> getEnrollmentsByCourse() {
        // Fetch all courses
        return courseRepo.findAll().stream()
            .collect(Collectors.toMap(
                Course::getTitle, // Use the title of the course
                course -> enrollmentRepo.countByCourseId(course.getId()) // Count enrollments by course ID
            ));
    }
}
