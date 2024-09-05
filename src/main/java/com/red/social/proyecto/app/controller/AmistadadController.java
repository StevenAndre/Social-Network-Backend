package com.red.social.proyecto.app.controller;

import com.red.social.proyecto.app.service.AmistadService;
import com.red.social.proyecto.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/amistad")
public class AmistadadController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AmistadService amistadService;

    @GetMapping("/amigos/{userId}")
    public ResponseEntity<?> getSeguidos(@PathVariable Long userId){

        return ResponseEntity.ok(usuarioService.getFriends(userId));
    }

    @GetMapping("/sugerencias/{userId}")
    public ResponseEntity<?> getUsersNotFollow(@PathVariable Long userId){
        return ResponseEntity.ok(usuarioService.getSugerenciasUser(userId));
    }

    @PostMapping("/solicitud/{solicitudId}")
    public ResponseEntity<?> followUser(@PathVariable Long solicitudId){
        return ResponseEntity.ok(amistadService.aceptarAmistad(solicitudId));
    }

    @DeleteMapping("/{seguidorId}/unfollowing/{seguidoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unfollowingUser(@PathVariable Long seguidorId, @PathVariable Long seguidoId){
        amistadService.deleteAmistad(seguidorId,seguidoId);
    }



}
