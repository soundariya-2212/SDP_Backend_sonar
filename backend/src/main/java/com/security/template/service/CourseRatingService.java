package com.security.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// import com.security.template.model.Course;
import com.security.template.model.CourseRating;
// import com.security.template.model.User;
import com.security.template.repo.CourseRatingRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CourseRatingService {

    @Autowired
    private CourseRatingRepo courseRatingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    public List<CourseRating> getAllRatings() {
        return courseRatingRepository.findAll();
    }

    public ResponseEntity<CourseRating> getRatingById(Long id) {
        Optional<CourseRating> rating = courseRatingRepository.findById(id);
        return rating.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // public CourseRating createRating(CourseRating rating, Long courseID, Long userID) {
    //     Course userobj = courseService.getCourseById(courseID);
    //     User userobj1 = userService.getUserById(userID);
    //     rating.setCourse(userobj);
    //     rating.setUser(userobj1);
    //     return courseRatingRepository.save(rating);
    // }

    public ResponseEntity<CourseRating> updateRating(Long id, CourseRating rating) {
        if (courseRatingRepository.existsById(id)) {
            rating.setId(id);
            return ResponseEntity.ok(courseRatingRepository.save(rating));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deleteRating(Long id) {
        if (courseRatingRepository.existsById(id)) {
            courseRatingRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
