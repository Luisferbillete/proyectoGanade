package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.Entity.Propietario;
import com.gandec.ganadecs.Repository.PropietariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropietarioServiceImpl implements PropietarioService{
    @Autowired
    private PropietariosRepository propietariosRepository;
    @Override
    @Transactional
    public Propietario SavePropietario(Propietario propietario) {

        return propietariosRepository.save(propietario);
    }
}
