package com.red.social.proyecto.app.service.impl;

import com.red.social.proyecto.app.entity.Amistad;
import com.red.social.proyecto.app.entity.Solicitud;
import com.red.social.proyecto.app.entity.Usuario;
import com.red.social.proyecto.app.repository.AmistadRepository;
import com.red.social.proyecto.app.service.AmistadService;
import com.red.social.proyecto.app.service.SolicitudService;
import com.red.social.proyecto.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AmistadServiceImpl implements AmistadService {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AmistadRepository amistadRepository;

    @Autowired
    private SolicitudService solicitudService;


    @Override
    public Map<String, Amistad> aceptarAmistad(Long solicitudId) {
        Map<String, Amistad> amistadMap = new HashMap<>();
        Solicitud solicitud = solicitudService.getSolicitudById(solicitudId);
        Usuario usuarioSolicitante = solicitud.getSolicitante();
        Usuario usuarioSolicitado = solicitud.getSolicitado();

        Amistad amistad1 = crearAmistad(usuarioSolicitante, usuarioSolicitado);
        Amistad amistad2 = crearAmistad(usuarioSolicitado, usuarioSolicitante);

        amistadMap.put("amistad1", amistadRepository.save(amistad1));
        amistadMap.put("amistad2", amistadRepository.save(amistad2));

        solicitudService.deleteSolicitud(solicitudId);
        return amistadMap;
    }

    @Override
    public void deleteAmistad(Long usuarioId, Long amigoId) {
        amistadRepository.deleteAmistadOfUsers(usuarioId,amigoId);
    }

    private Amistad crearAmistad(Usuario usuario, Usuario amigo) {
        Amistad amistad = new Amistad();
        amistad.setUsuario(usuario);
        amistad.setAmigo(amigo);
        return amistad;
    }


}
