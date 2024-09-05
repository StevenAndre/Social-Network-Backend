package com.red.social.proyecto.app.controller;
import com.red.social.proyecto.app.dto.PublicacionDto;
import com.red.social.proyecto.app.service.PublicacionService;
import com.red.social.proyecto.app.service.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;


@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PublicacionController {
    @Autowired
    private PublicacionService publicacionService;
    @Autowired
    private StorageService storageService;

    @PostMapping("/create")
    public ResponseEntity<?> createPublicacion(@Valid @RequestPart("publicacion") PublicacionDto publicacionDto,
                                               BindingResult result,
                                               @RequestPart (value = "image",required = false) MultipartFile file){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body( result.getFieldErrors());
        }
        return new ResponseEntity<>(publicacionService.savePublicacion(publicacionDto,file), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updatePublicacion(@Valid @RequestPart("publicacion") PublicacionDto publicacionDto,
                                               BindingResult result,
                                               @RequestPart (value = "image",required = false) MultipartFile file){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body( result.getFieldErrors());
        }
        return ResponseEntity.ok(publicacionService.updatePublicacion(publicacionDto,file));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPublicacionesUser(@PathVariable Long userId){
        return ResponseEntity.ok(publicacionService.obtenerPublicacionesUsuario(userId));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{publicacionId}")
    public ResponseEntity<?> deletePublicacionAdmin(@PathVariable Long publicacionId){
        publicacionService.eliminarPublicacion(publicacionId);
        return ResponseEntity.ok("publicacion con id: "+publicacionId+" eliminado correctamente");
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPublicacionById(@PathVariable Long id){

        return ResponseEntity.ok(publicacionService.getPublivacionById(id));
    }


    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<Resource> getPhotoProfile(@PathVariable String filename) throws IOException {

        Resource file= storageService.loadResources(filename);
        String contentType= Files.probeContentType(file.getFile().toPath());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE,contentType)
                .body(file);
    }

    @GetMapping("/follows/{usuarioId}")
    public ResponseEntity<?> getPostOfSeguidos(
            @PathVariable Long usuarioId,
            @RequestParam(value = "numPag",defaultValue = "0",required = false) int numPag,
            @RequestParam(value = "pageSize",defaultValue = "3",required = false) int pageSize,
            @RequestParam(value = "sortDirec",defaultValue = "asc",required = false) String sortDirec
    ){
        return ResponseEntity.ok( publicacionService.getPublicacionesSeguidos(usuarioId,numPag,pageSize,sortDirec));
    }




}
