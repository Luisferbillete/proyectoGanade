package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.Clientes.ClienteCmbDTO;
import com.gandec.ganadecs.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteReository extends JpaRepository<Cliente,Long> {
    @Query("SELECT new com.gandec.ganadecs.DTO.Clientes.ClienteCmbDTO(c.id,CONCAT(c.nombres,' ',c.apellidos))  FROM Cliente c ")
    List<ClienteCmbDTO> findAllClientes();
}
