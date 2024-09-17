package com.security.template.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// import com.security.template.auth.ChangePasswordRequest;
// import com.security.template.auth.UserUpdateRequest;
import com.security.template.model.User;
import com.security.template.repo.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

   

    // Register a new user
    public ResponseEntity<User> registerUser(User user) {
        try {
            User createdUser = userRepo.save(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing user
    public ResponseEntity<User> updateUser(Long id, User user) {
        Optional<User> existingUser = userRepo.findById(id);
        if (existingUser.isPresent()) {
            user.setId(id);
            User updatedUser = userRepo.save(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a user
    public ResponseEntity<HttpStatus> deleteUser(Long id) {
        try {
            userRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all users
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepo.findAll();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a user by ID
    // public ResponseEntity<User> getUserById(Long id) {
    //     Optional<User> user = userRepo.findById(id);
    //     if (user.isPresent()) {
    //         return new ResponseEntity<>(user.get(), HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
    public ResponseEntity<User> getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
