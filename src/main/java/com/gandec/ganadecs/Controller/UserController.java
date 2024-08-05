package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.Auth.AuthResponse;
import com.gandec.ganadecs.DTO.Auth.CreatePropietary;
import com.gandec.ganadecs.DTO.Auth.LoginRequest;
import com.gandec.ganadecs.DTO.Propietary.PropietaryGetAll;
import com.gandec.ganadecs.Excepciones.UniqueConstraintException;
import com.gandec.ganadecs.Excepciones.UniqueEmailException;
import com.gandec.ganadecs.Excepciones.UniqueUsernameException;
import com.gandec.ganadecs.Services.PropietarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
/*    @GetMapping("/GetAll")
    public ResponseEntity<List<PropietaryGetAll>> GetAllPropietary(){
        return new ResponseEntity<>(propietarioService.PropietaryGetAll(), HttpStatus.OK);
    }*/

 @PostMapping("/save")

   public ResponseEntity<String> createPropietary(@Valid @RequestBody CreatePropietary createPropietary) throws UniqueConstraintException {
   //try{
   propietarioService.save(createPropietary);
   return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente");
 }
       /*} catch (UniqueUsernameException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       } catch (UniqueEmailException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       } catch (UniqueConstraintException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       } catch (RuntimeException e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud.");
       }*/









}
