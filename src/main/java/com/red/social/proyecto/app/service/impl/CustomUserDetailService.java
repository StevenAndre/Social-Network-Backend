package com.red.social.proyecto.app.service.impl;

import com.red.social.proyecto.app.entity.Rol;
import com.red.social.proyecto.app.entity.Usuario;
import com.red.social.proyecto.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        Usuario usuario=usuarioRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(
                ()->new UsernameNotFoundException("user con username o email :"+usernameOrEmail.concat(" No encontrado"))
        );

        return new User(usuario.getUsername(),usuario.getPassword(),mapearRoles(usuario.getRoles()));
    }


    private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles){

        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
    }
}
