package com.red.social.proyecto.app.service.impl;

import com.red.social.proyecto.app.dto.PublicacionDto;
import com.red.social.proyecto.app.entity.Publicacion;
import com.red.social.proyecto.app.entity.Usuario;
import com.red.social.proyecto.app.exception.ResourceNotFoundException;
import com.red.social.proyecto.app.repository.PublicacionRepository;
import com.red.social.proyecto.app.service.PublicacionService;
import com.red.social.proyecto.app.service.StorageService;
import com.red.social.proyecto.app.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import java.util.List;


@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private  PublicacionRepository publicacionRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ModelMapper mapper;

    @Override
    public PublicacionDto savePublicacion(PublicacionDto publicacionDto, MultipartFile file) {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Usuario autor=usuarioService.getUserActual(authentication.getName(),authentication.getName()).get();
        Publicacion publicacion=mapearToPub(publicacionDto);
        publicacion.setAutor(autor);
        publicacion.setFechaCreacion(new Date());
        publicacion.setFechaModificacion(new Date());
        if(file!=null && !file.isEmpty()){
            publicacion.setImagenPost(storageService.store(file));
        }

        return mapeearToPubDto(publicacionRepository.save(publicacion));
    }

    @Override
    public PublicacionDto updatePublicacion(PublicacionDto publicacionDto, MultipartFile file) {

        Publicacion publicacion= mapearToPub(getPublivacionById(publicacionDto.getId()));
        publicacion.setContenido(publicacionDto.getContenido());
        if(file!=null && !file.isEmpty()){
            storageService.delete(publicacion.getImagenPost());
            publicacion.setImagenPost(storageService.store(file));
        }
        publicacion.setFechaModificacion(new Date());

        return mapeearToPubDto(publicacionRepository.save(publicacion));
    }
    @Override
    public PublicacionDto getPublivacionById(Long id) {


        return mapeearToPubDto(publicacionRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Publicacion"," el ID: "+id)
        ));
    }

    @Override
    public List<PublicacionDto> obtenerPublicacionesUsuario(Long id) {
        return publicacionRepository.findPublicacionByAutorId(id)
                .stream()
                .map(this::mapeearToPubDto).toList();
    }






    @Override
    public void eliminarPublicacion(Long id) {
        publicacionRepository.deleteById(id);
    }

    @Override
    public Publicacion getPubEntity(Long id) {
        return publicacionRepository.findById(id).orElseThrow(
                ()->  new ResourceNotFoundException("Publicacion ","el ID: "+id)
        );
    }

    @Override
    public List<PublicacionDto> getPublicacionesSeguidos(Long usuarioId) {
        return publicacionRepository.getPublicacionesOfSeguidos(usuarioId).stream().map(
                this::mapeearToPubDto
        ).toList();
    }

    @Override
    public Page<PublicacionDto> getPublicacionesSeguidos(Long usuarioId, int numPagina, int totalPaginas,String sortDirec) {

        Sort sort= sortDirec.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by("fecha_creacion").ascending():Sort.by("fecha_creacion").descending();
        Pageable pageable = PageRequest.of(numPagina,totalPaginas, sort);
        Page<Publicacion> publicaciones= publicacionRepository.getPublicacionesOfSeguidos(usuarioId,pageable);

        Page<PublicacionDto> publicacionDtos=publicaciones.map(this::mapeearToPubDto);


        return publicacionDtos;
    }

    public PublicacionDto mapeearToPubDto(Publicacion publicacion){
        return mapper.map(publicacion, PublicacionDto.class);
    }

    public Publicacion mapearToPub(PublicacionDto publicacionDto){
        return  mapper.map(publicacionDto, Publicacion.class);
    }

}
