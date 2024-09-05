package com.red.social.proyecto.app.repository;

import com.red.social.proyecto.app.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Long> {

    List<Comentario> findComentarioByPublicacionId(Long id);
}
