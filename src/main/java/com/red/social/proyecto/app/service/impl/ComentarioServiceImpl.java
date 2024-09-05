package com.red.social.proyecto.app.service.impl;

import com.red.social.proyecto.app.dto.ComentarioDto;
import com.red.social.proyecto.app.entity.Comentario;
import com.red.social.proyecto.app.entity.Publicacion;
import com.red.social.proyecto.app.entity.Usuario;
import com.red.social.proyecto.app.repository.ComentarioRepository;
import com.red.social.proyecto.app.service.ComentarioService;
import com.red.social.proyecto.app.service.PublicacionService;
import com.red.social.proyecto.app.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private PublicacionService publicacionService;
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ComentarioDto saveComentario(Long publicacionId, ComentarioDto comentarioDto) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Usuario autor=usuarioService.getUserActual(authentication.getName(),authentication.getName()).get();
        Publicacion publicacion= publicacionService.getPubEntity(publicacionId);
        Comentario comentario= mapDtoToComen(comentarioDto);
        comentario.setPublicacion(publicacion);
        comentario.setAutor(autor);
        comentario.setFechaCreacion(new Date());
        comentario.setFechaModificacion(new Date());
        comentario.setComentario(comentario);

        return mapComenToDto(comentarioRepository.save(comentario));
    }

    @Override
    public ComentarioDto updateComentario(ComentarioDto comentarioDto) {

        Comentario comentario=comentarioRepository.findById(comentarioDto.getId()).get();
        comentario.setContenido(comentarioDto.getContenido());
        comentario.setFechaModificacion(new Date());
        return mapComenToDto(comentarioRepository.save(comentario));
    }

    @Override
    public ComentarioDto getComentarioById(Long id) {
        return  mapComenToDto(comentarioRepository.findById(id).get());
    }

    @Override
    public List<ComentarioDto> getCometariosByPublicacion(Long publicacionId) {
        return comentarioRepository.findComentarioByPublicacionId(publicacionId)
                .stream()
                .map(this::mapComenToDto)
                .toList();
    }

    @Override
    public void deleteComentario(Long id) {
        comentarioRepository.deleteById(id);
    }

    public ComentarioDto mapComenToDto(Comentario comentario){
        return mapper.map(comentario, ComentarioDto.class);
    }

    public Comentario mapDtoToComen(ComentarioDto comentarioDto){
        return mapper.map(comentarioDto, Comentario.class);
    }

}
