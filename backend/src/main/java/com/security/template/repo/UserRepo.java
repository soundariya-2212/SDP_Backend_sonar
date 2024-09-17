package com.security.template.repo;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.template.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

    // Optional<User> findByEmail(String email);

    boolean existsByName(String name);

    User findByEmail(String email);

    void deleteByName(String name);

}
