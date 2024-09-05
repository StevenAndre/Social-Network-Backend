package com.red.social.proyecto.app.repository;

import com.red.social.proyecto.app.entity.Publicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {

    List<Publicacion> findPublicacionByAutorId(Long autorID);
    @Query(value = "select p.* from publicaciones as p join amistades as a on  p.usuario_id = a.amigo_id where a.usuario_id= :userId",nativeQuery = true)
    List<Publicacion> getPublicacionesOfSeguidos(@Param("userId") Long userId);

    @Query(value = "select p.* from publicaciones as p join amistades as a on  p.usuario_id = a.amigo_id where a.usuario_id= :userId",
            countQuery = "SELECT count(*) FROM publicaciones",
            nativeQuery = true)
    Page<Publicacion> getPublicacionesOfSeguidos(@Param("userId") Long userId, Pageable pageable);


}
