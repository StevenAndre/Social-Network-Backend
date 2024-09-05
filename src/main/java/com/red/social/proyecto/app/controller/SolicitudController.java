package com.red.social.proyecto.app.controller;

import com.red.social.proyecto.app.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {
    @Autowired
    private SolicitudService solicitudService;

    @PostMapping("{solicitanteId}/solicita/{solicitadoId}")
    public ResponseEntity<?> sendSolicitud(@PathVariable Long solicitanteId,@PathVariable Long solicitadoId){
        return new ResponseEntity<>(solicitudService.sendSolicitud(solicitanteId, solicitadoId), HttpStatus.CREATED);
    }


    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> getSolicitudesOfUser(@PathVariable Long usuarioId){
        return ResponseEntity.ok(solicitudService.getSolicitudesOfUser(usuarioId));
    }

    @DeleteMapping("/delete/{solicitudId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSolicitud(@PathVariable Long solicitudId){
        solicitudService.deleteSolicitud(solicitudId);
    }






}
