package com.red.social.proyecto.app.service;

import com.red.social.proyecto.app.dto.UserUpdateDto;
import com.red.social.proyecto.app.dto.UsuarioDto;
import com.red.social.proyecto.app.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    UsuarioDto saveUsuario(UsuarioDto usuarioDto);

    UsuarioDto updateUsuario(UserUpdateDto usuario);
    List<UsuarioDto> getAllUsuarios();
    Usuario getUsuarioById(Long id);

    void deleteUsuario(Long id);
    Boolean usernameUsed(String username);
    Boolean emailUsed(String email);

    Optional<Usuario> getUserActual(String username,String email);

    Boolean updateUsernameCheck(String username, Long id);

    Boolean updateEmailCheck(String email, Long id);

    List<UsuarioDto> getFriends(Long usuarioId);

    List<UsuarioDto> getNotFriends(Long usuarioId);
    List<UsuarioDto> getSugerenciasUser(Long usuarioId);

    UsuarioDto loginAndLogoutUser(Usuario usuario, boolean estadoConextion);

    UsuarioDto getUserByUsername(String username);

    Usuario loadUserByUsername(String username);



}
