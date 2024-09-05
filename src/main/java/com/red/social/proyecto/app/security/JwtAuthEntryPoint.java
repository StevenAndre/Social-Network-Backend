package com.red.social.proyecto.app.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {



    @Override
    public void commence( HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Unauthorized: " + authException.getMessage());
        Map<String,Object> mensajeResponse=new HashMap<>();


        mensajeResponse.put("response",responseEntity.getBody());
        boolean tokenExpired= (boolean)request.getAttribute("tokenStatusExpired");
        mensajeResponse.put("tokenExpired",tokenExpired);


        System.out.println("Respuesta del token:=>"+request.getAttribute("tokenStatusExpired"));
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(mensajeResponse);
        // Establecer el encabezado de respuesta adecuado
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
        response.getWriter().close();

    }
}
