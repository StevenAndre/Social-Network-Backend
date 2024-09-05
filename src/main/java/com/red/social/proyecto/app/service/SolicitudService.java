package com.red.social.proyecto.app.service;

import com.red.social.proyecto.app.entity.Solicitud;

import java.util.List;

public interface SolicitudService {

    Solicitud sendSolicitud(Long solicitante, Long solicitado);

    void deleteSolicitud(Long solicitudId);

    List<Solicitud> getSolicitudesOfUser(Long soliciadoId);

    Solicitud getSolicitudById(Long id);





}
