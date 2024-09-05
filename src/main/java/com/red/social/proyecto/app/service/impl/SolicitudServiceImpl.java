package com.red.social.proyecto.app.service.impl;

import com.red.social.proyecto.app.entity.Solicitud;
import com.red.social.proyecto.app.entity.Usuario;
import com.red.social.proyecto.app.repository.SolicitudRepository;
import com.red.social.proyecto.app.service.SolicitudService;
import com.red.social.proyecto.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Solicitud sendSolicitud(Long solicitanteId, Long solicitadoId) {
        Usuario solicitante=usuarioService.getUsuarioById(solicitanteId);
        Usuario solicitado=usuarioService.getUsuarioById(solicitadoId);
        Solicitud solicitud= new Solicitud();
        solicitud.setEstado("ENVIADA");
        solicitud.setSolicitado(solicitado);
        solicitud.setSolicitante(solicitante);
        solicitud.setFechaSolicitud(new Date());
        return solicitudRepository.save(solicitud);
    }

    @Override
    public void deleteSolicitud(Long solicitudId) {
        solicitudRepository.deleteById(solicitudId);
    }

    @Override
    public List<Solicitud> getSolicitudesOfUser(Long soliciadoId) {
        return solicitudRepository.findBySolicitadoId(soliciadoId);
    }

    @Override
    public Solicitud getSolicitudById(Long id) {
        return solicitudRepository.findById(id).get();
    }
}
