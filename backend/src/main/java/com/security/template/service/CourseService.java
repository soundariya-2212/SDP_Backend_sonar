package com.security.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.security.template.model.Course;
// import com.security.template.model.User;
import com.security.template.repo.CourseRepo;

import java.util.List;
// import java.util.Optional;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    // @Autowired
    // private UserService userService;

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + id));
    }

    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    public ResponseEntity<Course> updateCourse(Long id, Course course) {
        if (courseRepo.existsById(id)) {
            course.setId(id);
            return ResponseEntity.ok(courseRepo.save(course));
        }
        return ResponseEntity.notFound().build();
    }

    // public ResponseEntity<Void> deleteCourse(Long id) {
    //     if (courseRepo.existsById(id)) {
    //         courseRepo.deleteById(id);
    //         return ResponseEntity.ok().build();
    //     }
    //     return ResponseEntity.notFound().build();
    // }
    public boolean deleteCourse(Long id) {
        Optional<Course> courseOptional = courseRepo.findById(id);
        if (courseOptional.isPresent()) {
            courseRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    public List<Course> getCourses() {
        return courseRepo.findAll();
    }
}
