package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.Auth.AuthResponse;
import com.gandec.ganadecs.DTO.Auth.CreateUserDTO;
import com.gandec.ganadecs.DTO.Auth.LoginRequest;
import com.gandec.ganadecs.Entity.ERole;
import com.gandec.ganadecs.Entity.RoleEntity;
import com.gandec.ganadecs.Entity.UserEntity;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Jwt.JwtService;
import com.gandec.ganadecs.Repository.UserRepository;
import com.gandec.ganadecs.Services.AuthService;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserEntity user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        

        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponse register(CreateUserDTO request) {
       Set<RoleEntity> roleEntities = request.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());
        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .roles(roleEntities)
                .build();

        userRepository.save(userEntity);
        
        return AuthResponse.builder()
                .token(jwtService.getToken(userEntity))
                .build();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExcepcion("User","id",id));
    }
}
