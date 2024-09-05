package com.red.social.proyecto.app.service;

import com.red.social.proyecto.app.dto.PublicacionDto;

import com.red.social.proyecto.app.entity.Publicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PublicacionService {

    PublicacionDto savePublicacion(PublicacionDto publicacionDto, MultipartFile file);
    PublicacionDto updatePublicacion(PublicacionDto publicacionDto,MultipartFile file);

    PublicacionDto getPublivacionById(Long id);
    List<PublicacionDto> obtenerPublicacionesUsuario(Long id);
    void eliminarPublicacion(Long id);

    Publicacion getPubEntity(Long id);

    List<PublicacionDto> getPublicacionesSeguidos(Long usuarioId);

    Page<PublicacionDto> getPublicacionesSeguidos(Long usuarioId, int numPagina, int totalPaginas,String sortDirec);


}
