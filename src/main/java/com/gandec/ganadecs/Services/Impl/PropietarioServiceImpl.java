package com.gandec.ganadecs.Services.Impl;


import com.gandec.ganadecs.DTO.Auth.AuthResponse;
import com.gandec.ganadecs.DTO.Auth.CreatePropietary;
import com.gandec.ganadecs.DTO.Auth.LoginRequest;
import com.gandec.ganadecs.DTO.PropietaryComboDto;
import com.gandec.ganadecs.DTO.PropietaryDTO;
import com.gandec.ganadecs.Entity.ERole;
import com.gandec.ganadecs.Entity.Propietario;
import com.gandec.ganadecs.Entity.RoleEntity;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Jwt.JwtService;
import com.gandec.ganadecs.Mapeador.Mapper;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.PropietariosRepository;
import com.gandec.ganadecs.Services.PropietarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gandec.ganadecs.Mapeador.MapperList.mapList;

@Service
@RequiredArgsConstructor
public class PropietarioServiceImpl implements PropietarioService {
    private final PropietariosRepository propietariosRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;
    private final Mappers  mappers;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
        (request.getUsername(), request.getPassword()));
        Propietario propietario = propietariosRepository.findByUsername(request.getUsername())
                .orElseThrow(()->new AuthenticationException("Invalid username or password") {
                    @Override
                    public String getMessage() {
                        return super.getMessage();
                    }
                });
        String token=jwtService.getToken(propietario);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public CreatePropietary save(CreatePropietary createPropietary) {
        Set < RoleEntity > roleEntities = createPropietary.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());
        Propietario propietario = Propietario.builder()
                .nombres(createPropietary.getNombres())
                .apellidos(createPropietary.getApellidos())
                .direccion(createPropietary.getDireccion())
                .telefonos(createPropietary.getTelefonos())
                .email(createPropietary.getEmail())
                .username(createPropietary.getUsername())
                .password(passwordEncoder.encode(createPropietary.getPassword()))
                .roles(roleEntities)
                .build();
        propietariosRepository.save(propietario);
        return null;
    }

    @Override
    public List<PropietaryDTO> PropietaryGetAll() {
        List<Propietario>propietarioList=propietariosRepository.findAll();
        return mapList(propietarioList,PropietaryDTO.class);
    }





    @Override
    public void DeletePropietary(long id) {
       Propietario propietario=propietariosRepository.findById(id).orElseThrow(()
               ->new ResourceNotFoundExcepcion("Propietario","id",id));

       propietariosRepository.delete(propietario);

    }

    @Override
    public List<PropietaryComboDto> PROPIETARY_COMBO_DTO_LIST() {
        List<Propietario> propietarioList;
        propietarioList=propietariosRepository.findAll();
        return mapper.entityToDTO(propietarioList);

    }

    @Override
    public PropietaryDTO UpdatePropietary(PropietaryDTO propietaryDTO, long id) {
        Propietario propietario=propietariosRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundExcepcion("Propietario","id",id));

        propietario.setApellidos(propietaryDTO.getApellidos());
        propietario.setNombres(propietaryDTO.getNombres());
        propietario.setDireccion(propietaryDTO.getDireccion());
        propietario.setTelefonos(propietaryDTO.getTelefonos());

        Propietario proprietaryActualize=propietariosRepository.save(propietario);
        return (PropietaryDTO) mappers.convertToDto(proprietaryActualize,propietaryDTO);
    }

    @Override
    public PropietaryDTO GetPropietary(long id) {
        PropietaryDTO dto=new PropietaryDTO();
        Propietario propietario  =propietariosRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundExcepcion
                                ("Propietario","id",id));
        return (PropietaryDTO) mappers.convertToDto(propietario,dto);
    }

    @Override
    public PropietaryComboDto findPropietaryComboDtoByNombresAndApellidos(String nombres, String apellidos) {
        return propietariosRepository.findPropietaryComboDtoByNombresAndApellidos(nombres,apellidos);
    }


}
