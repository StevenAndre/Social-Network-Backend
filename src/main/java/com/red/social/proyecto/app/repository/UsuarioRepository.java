package com.red.social.proyecto.app.repository;

import com.red.social.proyecto.app.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Optional<Usuario> findByUsernameOrEmail(String username, String email);

    @Query(value = "SELECT u.* FROM usuarios u JOIN amistades a ON u.id = a.amigo_id WHERE a.usuario_id = :userId",nativeQuery = true)
    List<Usuario> getFriends(@Param("userId") Long userId);
    @Query(value = "SELECT u.* FROM usuarios u LEFT JOIN amistades a ON u.id = a.amigo_id AND a.usuario_id =:userId  WHERE a.amigo_id IS NULL AND u.id != :userId",nativeQuery = true)
    List<Usuario> getNotFriends(@Param("userId") Long userId);

    @Query(value = """
                SELECT u.* FROM usuarios u
                LEFT JOIN amistades a ON u.id = a.amigo_id AND a.usuario_id =:userId
                LEFT JOIN solicitudes s ON (u.id = s.solicitante_id AND s.solicitado_id =:userId) 
                OR (u.id = s.solicitado_id AND s.solicitante_id =:userId )
                WHERE a.amigo_id IS NULL
                AND u.id !=:userId
                AND s.solicitante_id IS NULL
                """ ,nativeQuery = true)
    List<Usuario> getSugerenciasOfFriends(@Param("userId") Long userId);

    Usuario findUsuarioByUsername(String username);

}
