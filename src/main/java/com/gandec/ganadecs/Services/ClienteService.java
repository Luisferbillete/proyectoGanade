package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.Clientes.ClienteCmbDTO;
import com.gandec.ganadecs.DTO.Clientes.ClientesDTO;

import java.util.List;

public interface ClienteService {
    String save(ClientesDTO clientesDTO);
    List<ClientesDTO> findAll();
    ClientesDTO findById(long id);
    ClientesDTO update(ClientesDTO clientesDTO,long id);
    List<ClienteCmbDTO> findAllClientes();


}
