package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.Auth.AuthResponse;
import com.gandec.ganadecs.DTO.Auth.CreatePropietary;
import com.gandec.ganadecs.DTO.Auth.LoginRequest;
import com.gandec.ganadecs.Services.PropietarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/auth")
public class UserController {
  
  private final PropietarioService propietarioService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(propietarioService.login(request));
    }



  @PostMapping("/save")
    public ResponseEntity<CreatePropietary> createPropietary(@Valid @RequestBody CreatePropietary createPropietary){
        return ResponseEntity.ok(propietarioService.save(createPropietary));

    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String >deleteUser(@PathVariable(name = "id")Long id){
        //authService.deleteUser(id);
        return ResponseEntity.ok("User Deleted");
    }

}
