package com.red.social.proyecto.app.service;

import com.red.social.proyecto.app.dto.ComentarioDto;
import com.red.social.proyecto.app.entity.Comentario;

import java.util.List;

public interface ComentarioService {

    ComentarioDto saveComentario(Long publicacionId, ComentarioDto comentarioDto);
    ComentarioDto updateComentario(ComentarioDto comentarioDto);

    ComentarioDto getComentarioById(Long id);

    List<ComentarioDto> getCometariosByPublicacion(Long publicacionId);

    void deleteComentario(Long id);

}
