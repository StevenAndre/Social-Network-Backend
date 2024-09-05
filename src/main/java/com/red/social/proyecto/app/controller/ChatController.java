package com.red.social.proyecto.app.controller;

import com.red.social.proyecto.app.dto.NotificationDto;
import com.red.social.proyecto.app.entity.Mensaje;
import com.red.social.proyecto.app.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.SendTo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.awt.*;
import java.util.Date;

@RestController
public class ChatController {






    @Autowired
    private MensajeService mensajeService;




    @GetMapping("/chat/messages/{userSender}/{userReciver}")
    public ResponseEntity<?> getMessagesOfChat(@PathVariable String userSender, @PathVariable String userReciver){
        return ResponseEntity.ok(mensajeService.getMesagesByChat(userSender,userReciver));
    }
    
    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibirMensaje(Mensaje mensaje){
        // aqui se puede hacer algo como guarar en bd
        mensaje.setFechaEnvio(new Date());

        mensaje.setTexto("Recibido por el broker: "+mensaje.getTexto());



        return  mensaje;
    }


    @MessageMapping("/mensaje-private/{userSend}/{userReceiver}")
    @SendTo({"/chat/message/{userReceiver}/{userSend}"})
    public Mensaje privateMensajes(@DestinationVariable  String userSend,@DestinationVariable String userReceiver , Mensaje mensaje){


        mensaje.setFechaEnvio(new Date());

        Mensaje message=mensajeService.saveMensaje(mensaje,userSend,userReceiver);
        String username=mensaje.getUserEmiter();
        System.out.println("Message==>"+mensaje.getTexto());
        System.out.println("username send="+userSend);
        System.out.println("username reciver="+userReceiver);
        System.out.println(message);

        return mensaje;
    }


    @MessageMapping("/escribiendo/{userSend}/{userReceiver}")
    @SendTo({"/chat/escribiendo/{userReceiver}/{userSend}"})
    public String writingEvent(@DestinationVariable String userSend,@DestinationVariable String userReceiver ){

        return "Escribiendo...";
    }



    @MessageMapping("/notify/{userSend}/{userReceiver}")
    @SendTo({"/chat/notify/{userReceiver}"})
    public NotificationDto notificationMessage(@DestinationVariable String userSend, @DestinationVariable String userReceiver ){

        NotificationDto notificacion=new NotificationDto();
        notificacion.setUsername(userSend);
        notificacion.setMessage("Tienes un nuevo mensaje");

        return notificacion;
    }

    @MessageMapping("/chat/connect/{username}")
    @SendTo({"/chat/connect/{username}"})
    public String userConnected(@DestinationVariable String username) {
       String usernameConec=username;
        return "200 USER CONECT: "+usernameConec;
    }

    @MessageMapping("/inchat/{userSend}/{userReceiver}")
    @SendTo({"/chat/inchat/{userReceiver}/{userSend}"})
    public Boolean conectInChat(@DestinationVariable String userSend,
                                @DestinationVariable String userReceiver,
                                Boolean conect) {

        System.out.println(String.format("Conectado al chat: %b userSender: %s  userReciver: %s",conect,userSend,userReceiver));

        return conect;
    }



}
