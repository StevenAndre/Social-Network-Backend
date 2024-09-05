package com.red.social.proyecto.app.service;

import com.red.social.proyecto.app.entity.Mensaje;

import java.util.List;

public interface MensajeService {

    Mensaje saveMensaje(Mensaje mensaje,String usernameSender, String usernameReciver);

    List<Mensaje> getMesagesByChat(String user1,String user2);

}
