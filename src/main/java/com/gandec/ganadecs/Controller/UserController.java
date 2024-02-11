package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.Auth.AuthResponse;
import com.gandec.ganadecs.DTO.Auth.CreateUserDTO;
import com.gandec.ganadecs.DTO.Auth.LoginRequest;
import com.gandec.ganadecs.Entity.ERole;
import com.gandec.ganadecs.Entity.RoleEntity;
import com.gandec.ganadecs.Entity.UserEntity;
import com.gandec.ganadecs.Repository.UserRepository;
import com.gandec.ganadecs.Services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/auth")
public class UserController {
  
  private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }



  @PostMapping("/save")
    public ResponseEntity<AuthResponse> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        return ResponseEntity.ok(authService.register(createUserDTO));

    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String >deleteUser(@PathVariable(name = "id")Long id){
        authService.deleteUser(id);
        return ResponseEntity.ok("User Deleted");
    }

}
