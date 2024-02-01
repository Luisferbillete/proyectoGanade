package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.User.CreateUserDTO;
import com.gandec.ganadecs.Entity.ERole;
import com.gandec.ganadecs.Entity.RoleEntity;
import com.gandec.ganadecs.Entity.UserEntity;
import com.gandec.ganadecs.Repository.UserRepository;
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
@RequestMapping("Ganadec/user")
public class UserController {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
   @PostMapping("/save")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
       Set<RoleEntity> roleEntities = createUserDTO.getRoles().stream()
               .map(role -> RoleEntity.builder()
                       .name(ERole.valueOf(role))
                       .build())
               .collect(Collectors.toSet());
       UserEntity userEntity = UserEntity.builder()
               .username(createUserDTO.getUsername())
               .password(passwordEncoder.encode(createUserDTO.getPassword()))
               .email(createUserDTO.getEmail())
               .roles(roleEntities)
               .build();

       userRepository.save(userEntity);

       return ResponseEntity.ok(userEntity);

   }
    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROPIETARIO') or hasRole('ENCARGADO')")
    public ResponseEntity<String >deleteUser(@PathVariable(name = "id")String id){
        System.out.println("id = " + id);
        userRepository.deleteById(Long.parseLong(id));
        return ResponseEntity.ok("Se ha borrado el user con id ".concat(id));
    }


}
