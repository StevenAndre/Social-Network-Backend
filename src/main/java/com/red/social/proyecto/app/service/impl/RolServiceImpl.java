package com.red.social.proyecto.app.service.impl;

import com.red.social.proyecto.app.entity.Rol;
import com.red.social.proyecto.app.repository.RolRepository;
import com.red.social.proyecto.app.service.RolService;

import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl  implements RolService {


    private final RolRepository rolRepository;


    public RolServiceImpl(RolRepository rolRepository){
        this.rolRepository=rolRepository;
    }

    @Override
    public Rol saveRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Rol updateRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Rol getRolById(Long id) {
        return rolRepository.findById(id).get();
    }

    @Override
    public void deleteRol(Long id) {

        rolRepository.deleteById(id);
    }
}
