package com.security.template.service;

import com.security.template.auth.AuthenticationResponse;
import com.security.template.auth.ChangePasswordRequest;
import com.security.template.auth.RegisterRequest;
import com.security.template.auth.UserUpdateRequest;
import com.security.template.model.User;
import com.security.template.auth.AuthenticationRequest;

public interface AuthService {
    String register(RegisterRequest registerRequest);

    AuthenticationRequest login(AuthenticationResponse loginRequest);

    String createAdmin();

    User findUserByEmail(String email);

    void updateUser(Long id, UserUpdateRequest userUpdateRequest) throws Exception;

    void changePassword(ChangePasswordRequest changePasswordRequest) throws Exception;

    
}
