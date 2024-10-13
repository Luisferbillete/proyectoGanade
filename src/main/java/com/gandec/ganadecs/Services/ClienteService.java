package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.Clientes.ClienteCmbDTO;
import com.gandec.ganadecs.DTO.Clientes.ClientesDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClienteService {
    String save(ClientesDTO clientesDTO);
    Page<ClientesDTO> findAllClientes(Integer start, Integer limit);
    List<ClientesDTO> findAll();
    ClientesDTO findById(long id);
    ClientesDTO update(ClientesDTO clientesDTO,long id);
    List<ClienteCmbDTO> findAllClientes();
    void deleteCliente(long id);


}
