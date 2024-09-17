package com.security.template.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.template.model.User;
import com.security.template.repo.UserRepo;

@Service
public class UsersDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
                // .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new UsersDetails(user);
    }
}