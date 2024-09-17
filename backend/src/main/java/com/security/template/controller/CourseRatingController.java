package com.security.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.security.template.model.CourseRating;
import com.security.template.service.CourseRatingService;

import java.util.List;

@RestController
@RequestMapping("/courserating")
public class CourseRatingController {

    @Autowired
    private CourseRatingService courseRatingService;

    @GetMapping
    public List<CourseRating> getAllRatings() {
        return courseRatingService.getAllRatings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseRating> getRatingById(@PathVariable Long id) {
        return courseRatingService.getRatingById(id);
    }

    // @PostMapping
    // public CourseRating createRating(
    //         @RequestBody CourseRating rating,
    //         @RequestParam Long courseID,
    //         @RequestParam Long userID) {
    //     return courseRatingService.createRating(rating, courseID, userID);
    // }

    @PutMapping("/{id}")
    public ResponseEntity<CourseRating> updateRating(@PathVariable Long id, @RequestBody CourseRating rating) {
        return courseRatingService.updateRating(id, rating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        return courseRatingService.deleteRating(id);
    }

}
