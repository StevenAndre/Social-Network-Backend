package com.red.social.proyecto.app.repository;

import com.red.social.proyecto.app.entity.Amistad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AmistadRepository  extends JpaRepository<Amistad,Long> {


    @Modifying
    @Query(value = "DELETE FROM amistades WHERE (usuario_id = :seguidor AND amigo_id = :seguido) OR (usuario_id = :seguido AND amigo_id = :seguidor)",nativeQuery = true)
    void deleteAmistadOfUsers(@Param("seguidor") Long seguidor,@Param("seguido") Long seguido);





}
