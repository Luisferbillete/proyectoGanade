package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.Clientes.ClienteCmbDTO;
import com.gandec.ganadecs.DTO.Clientes.ClientesDTO;
import com.gandec.ganadecs.DTO.Parto.PartosPropietariosDTO;
import com.gandec.ganadecs.Services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")   // Solo los usuarios con el rol de ADMIN pueden acceder a los metodos
@RequestMapping("/Ganadec/Cliente")
public class ClientController {
    private final ClienteService clientService;

    @PostMapping("/save")
    public String SaveClient(@Valid @RequestBody ClientesDTO clientesDTO){
        return clientService.save(clientesDTO);
    }
    @GetMapping("/all")
    public Page<ClientesDTO> GetPartosAll(@RequestParam(name = "start") Integer start,
                                                    @RequestParam(name = "limit") Integer limit) {
        return clientService.findAllClientes(start, limit);
    }
    @GetMapping("/findAll")
    public List<ClientesDTO> findAll(){
        return clientService.findAll();
    }
    @GetMapping("/findById/{id}")
    public ClientesDTO findById(@PathVariable long id){
        return clientService.findById(id);
    }
    @PutMapping("/update/{id}")
    public ClientesDTO update(@Valid @RequestBody ClientesDTO clientesDTO,@PathVariable long id){
        return clientService.update(clientesDTO,id);
    }
    @GetMapping("/findAllClients")
    public List<ClienteCmbDTO> findAllClients(){
        return clientService.findAllClientes();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeletePotter(@PathVariable(name = "id")long id){
        try{
            clientService.deleteCliente(id);
            return ResponseEntity.ok("Cliente eliminado con exito");
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el Cliente porque tiene Registros asociados.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado.");
        }
    }

}
