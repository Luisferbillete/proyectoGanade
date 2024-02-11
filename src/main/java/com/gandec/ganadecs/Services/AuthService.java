package com.gandec.ganadecs.Services;


import com.gandec.ganadecs.DTO.Auth.AuthResponse;
import com.gandec.ganadecs.DTO.Auth.CreateUserDTO;
import com.gandec.ganadecs.DTO.Auth.LoginRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(CreateUserDTO request);
    void deleteUser(Long id);
}
