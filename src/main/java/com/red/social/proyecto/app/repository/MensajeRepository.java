package com.red.social.proyecto.app.repository;

import com.red.social.proyecto.app.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje,Long> {

    @Query(value = "select * from mensajes where  emisor_id in (:idSender,:idReciver) and receptor_id in(:idSender,:idReciver) order by fecha_envio asc",nativeQuery = true)
    List<Mensaje> getMensajesByUsers(@Param("idSender")Long idUserSender,@Param("idReciver") Long idUserReciver);

}
