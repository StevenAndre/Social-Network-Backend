package com.red.social.proyecto.app.controller;

import com.red.social.proyecto.app.dto.ComentarioDto;
import com.red.social.proyecto.app.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@CrossOrigin("*")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;


    @PostMapping("/create/{publicacionId}")
    public ResponseEntity<?> createNewComentario(@PathVariable Long publicacionId,
                                                 @RequestBody ComentarioDto comentarioDto,
                                                 BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body( result.getFieldErrors());
        }
        return new ResponseEntity<>(comentarioService.saveComentario(publicacionId,comentarioDto), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateComentario(@RequestBody ComentarioDto comentarioDto,BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body( result.getFieldErrors());
        }
        return ResponseEntity.ok(comentarioService.updateComentario(comentarioDto));
    }
    @GetMapping("/post/{publicacionId}")
    public ResponseEntity<?> getComentariosOfPublicacion(@PathVariable Long publicacionId){
        return ResponseEntity.ok(comentarioService.getCometariosByPublicacion(publicacionId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable Long id){
        return ResponseEntity.ok(comentarioService.getComentarioById(id));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommentAdmin(@PathVariable Long  id){
        comentarioService.deleteComentario(id);
        return ResponseEntity.ok("Comentario con id: "+id+" Eliminado correctamente");
    }



}
