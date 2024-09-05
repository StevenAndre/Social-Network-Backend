package com.red.social.proyecto.app.service.impl;

import com.red.social.proyecto.app.entity.Mensaje;
import com.red.social.proyecto.app.entity.Usuario;
import com.red.social.proyecto.app.repository.MensajeRepository;
import com.red.social.proyecto.app.service.MensajeService;
import com.red.social.proyecto.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class MensajeServiceImpl implements MensajeService {


    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MensajeRepository mensajeRepository;

    @Override
    public Mensaje saveMensaje(Mensaje mensaje, String usernameSender, String usernameReciver) {

        Mensaje mensajeSaved= new Mensaje();
        Usuario userSender = usuarioService.loadUserByUsername(usernameSender);
        Usuario userReciver = usuarioService.loadUserByUsername(usernameReciver);
        mensajeSaved.setTexto(mensaje.getTexto());
        mensajeSaved.setFechaEnvio(new Date());
        mensajeSaved.setEmisor(userSender);
        mensajeSaved.setReceptor(userReciver);


        return mensajeRepository.save(mensajeSaved);
    }

    @Override
    public List<Mensaje> getMesagesByChat(String user1, String user2) {
        Usuario userSender = usuarioService.loadUserByUsername(user1);
        Usuario userReciver = usuarioService.loadUserByUsername(user2);


        return mensajeRepository
                .getMensajesByUsers(userSender.getId(), userReciver.getId())
                .stream()
                .map(
                        m->{

                            if(m.getEmisor().getUsername().equals(user1)){
                                m.setUserEmiter(userSender.getUsername());
                                m.setUserRecetor(userReciver.getUsername());
                            }else{
                                m.setUserEmiter(userReciver.getUsername());
                                m.setUserRecetor(userSender.getUsername());

                            }



                            return m;
                        }
                ).toList();
    }
}
