package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.Clientes.ClienteCmbDTO;
import com.gandec.ganadecs.DTO.Clientes.ClientesDTO;
import com.gandec.ganadecs.Entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    @Query("SELECT new com.gandec.ganadecs.DTO.Clientes.ClienteCmbDTO(c.Id,CONCAT(c.nombres,' ',c.apellidos))  FROM Cliente c ")
    List<ClienteCmbDTO> findAllClientes();
    @Query("SELECT new com.gandec.ganadecs.DTO.Clientes.ClientesDTO(c.Id,c.nombres,c.apellidos,c.direccion,c.telefono)  FROM Cliente c ")
    Page<ClientesDTO> findAllClientesPage(Pageable pageable);


}
