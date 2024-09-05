package com.red.social.proyecto.app.repository;

import com.red.social.proyecto.app.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud,Long> {

    List<Solicitud> findBySolicitadoId(Long solicitadoId);


}
