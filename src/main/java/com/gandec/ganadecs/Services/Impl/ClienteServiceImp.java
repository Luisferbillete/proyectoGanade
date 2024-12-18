package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.Clientes.ClienteCmbDTO;
import com.gandec.ganadecs.DTO.Clientes.ClientesDTO;
import com.gandec.ganadecs.Entity.Cliente;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.ClienteRepository;
import com.gandec.ganadecs.Services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gandec.ganadecs.Mapeador.MapperList.mapList;

@Service
@RequiredArgsConstructor
public class ClienteServiceImp implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final Mappers mappers;
    @Override
    public String save(ClientesDTO clientesDTO) {
        Cliente cliente=new Cliente();
        cliente= (Cliente) mappers.convertToEntity(clientesDTO,cliente);
        clienteRepository.save(cliente);
        return "Cliente Registrado";

    }

    @Override
    public Page<ClientesDTO> findAllClientes(Integer start, Integer limit) {
        Pageable pageable= PageRequest.of(start,limit);
        return clienteRepository.findAllClientesPage(pageable);
    }


    @Override
    public List<ClientesDTO> findAll() {
        List<Cliente> clienteList= clienteRepository.findAll();
        return mapList(clienteList,ClientesDTO.class);
    }

    @Override
    public ClientesDTO findById(long id) {
    Cliente cliente= clienteRepository.findById(id).orElseThrow(()->new RuntimeException("Cliente no encontrado"));
        ClientesDTO clientesDTO=new ClientesDTO();
        clientesDTO= (ClientesDTO) mappers.convertToDto(cliente,clientesDTO);
        return clientesDTO;}

    @Override
    public ClientesDTO update(ClientesDTO clientesDTO, long id) {
    Cliente cliente= clienteRepository.findById(id).orElseThrow(()->new RuntimeException("Cliente no encontrado"));
        cliente.setNombres(clientesDTO.getNombres());
        cliente.setApellidos(clientesDTO.getApellidos());
        cliente.setDireccion(clientesDTO.getDireccion());
        cliente.setTelefono(clientesDTO.getTelefono());
        Cliente clienteActualizado= clienteRepository.save(cliente);
        clientesDTO= (ClientesDTO) mappers.convertToDto(clienteActualizado,clientesDTO);
        return clientesDTO;}

    @Override
    public List<ClienteCmbDTO> findAllClientes() {
        return clienteRepository.findAllClientes();
    }

    @Override
    public void deleteCliente(long id) {
       Cliente cliente=clienteRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExcepcion("Cliente","id",id));
        clienteRepository.delete(cliente);

    }
}
