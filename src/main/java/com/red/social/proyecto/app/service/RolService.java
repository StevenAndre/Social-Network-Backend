package com.red.social.proyecto.app.service;

import com.red.social.proyecto.app.entity.Rol;

public interface RolService {

    Rol saveRol(Rol rol);
    Rol updateRol(Rol rol);
    Rol getRolById(Long id);
    void deleteRol(Long id);
}
