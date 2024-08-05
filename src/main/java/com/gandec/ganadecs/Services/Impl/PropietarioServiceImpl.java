package com.gandec.ganadecs.Services.Impl;


import com.gandec.ganadecs.DTO.Auth.AuthResponse;
import com.gandec.ganadecs.DTO.Auth.CreatePropietary;
import com.gandec.ganadecs.DTO.Auth.LoginRequest;
import com.gandec.ganadecs.DTO.Propietary.PropietaryGetAll;
import com.gandec.ganadecs.DTO.PropietaryComboDto;
import com.gandec.ganadecs.DTO.PropietaryDTO;
import com.gandec.ganadecs.Entity.ERole;
import com.gandec.ganadecs.Entity.Propietario;
import com.gandec.ganadecs.Entity.RoleEntity;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Excepciones.UniqueConstraintException;
import com.gandec.ganadecs.Excepciones.UniqueEmailException;
import com.gandec.ganadecs.Excepciones.UniqueUsernameException;
import com.gandec.ganadecs.Jwt.JwtService;
import com.gandec.ganadecs.Mapeador.Mapper;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.PropietariosRepository;
import com.gandec.ganadecs.Services.PropietarioService;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
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
        Set<RoleEntity> roleEntities = createPropietary.getRoles().stream()
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
        Optional<Propietario> ExitsUsername = propietariosRepository.findByUsername(createPropietary.getUsername());
        if(ExitsUsername.isPresent()){
              throw new UniqueUsernameException("El nombre de usuario ya está en uso.");
        }
        Optional<Propietario> ExitsEmail = propietariosRepository.findByEmail(createPropietary.getEmail());
        if(ExitsEmail.isPresent()){
            throw new UniqueEmailException("El correo electrónico ya está en uso.");
        }
        propietariosRepository.save(propietario);

        /*try {
            propietariosRepository.save(propietario);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException || cause instanceof ConstraintViolationException) {
                String message = cause.getMessage();
                if (message.contains("propietario.UK_rtwwdpdecdoiq3xp5gyc019k4")) {
                    throw new UniqueUsernameException("El nombre de usuario ya está en uso.");
                } else if (message.contains("propietario.UK_418ssbxx45iu2j71fn7slf32h")) {
                    throw new UniqueEmailException("El correo electrónico ya está en uso.");
                }
            }
            throw new UniqueConstraintException("Violación de restricción única.");
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el propietario.", e);
        }*/






        return null;
    }

    @Override
    public List<PropietaryGetAll> PropietaryGetAll() {
        List<Propietario>propietarioList=propietariosRepository.findAll();
        return mapList(propietarioList,PropietaryGetAll.class);
    }

    @Override
    public Page<PropietaryGetAll> PropietaryGetAllPage(Integer start, Integer limit) {
        Pageable pageable = PageRequest.of(start, limit);
        Page<Propietario> propietarioPage = propietariosRepository.findAll(pageable);
        if (propietarioPage != null) {
            return propietarioPage.map(propietario -> (PropietaryGetAll) mappers.convertToDto(propietario, new PropietaryGetAll()));
        }
        return null;
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
