package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.Clientes.ClienteCmbDTO;
import com.gandec.ganadecs.DTO.Clientes.ClientesDTO;
import com.gandec.ganadecs.Services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

}
