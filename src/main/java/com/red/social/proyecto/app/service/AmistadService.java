package com.red.social.proyecto.app.service;

import com.red.social.proyecto.app.entity.Amistad;
import com.red.social.proyecto.app.entity.Solicitud;

import java.util.Map;

public interface AmistadService {

    Map<String,Amistad> aceptarAmistad(Long solicitudId);


    void deleteAmistad(Long usuarioId, Long amigoId);

}
