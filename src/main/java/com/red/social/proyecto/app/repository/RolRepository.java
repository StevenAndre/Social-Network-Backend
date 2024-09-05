package com.red.social.proyecto.app.repository;

import com.red.social.proyecto.app.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {

}
