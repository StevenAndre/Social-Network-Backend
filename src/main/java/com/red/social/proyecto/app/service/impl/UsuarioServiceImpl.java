package com.red.social.proyecto.app.service.impl;

import com.red.social.proyecto.app.dto.UserUpdateDto;
import com.red.social.proyecto.app.dto.UsuarioDto;
import com.red.social.proyecto.app.entity.Rol;
import com.red.social.proyecto.app.entity.Usuario;
import com.red.social.proyecto.app.exception.ResourceNotFoundException;
import com.red.social.proyecto.app.repository.UsuarioRepository;
import com.red.social.proyecto.app.service.RolService;
import com.red.social.proyecto.app.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolService rolService;

    private final PasswordEncoder passwordEncoder;

    private final  ModelMapper mapper;


    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolService rolService, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.usuarioRepository = usuarioRepository;
        this.rolService=rolService;
        this.passwordEncoder=passwordEncoder;
        this.mapper=mapper;
    }

    @Override
    public UsuarioDto saveUsuario(UsuarioDto usuarioDto) {

        Usuario newUsuario=mapearToUsuario(usuarioDto);
        if(newUsuario.getPhotoProfile()==null || newUsuario.getPhotoProfile().equals(""))
            newUsuario.setPhotoProfile("default-profile.png");


        Set<Rol> roles = new HashSet<>();
        Rol rol=usuarioRepository.count()<1? rolService.getRolById(1L):rolService.getRolById(2L);
        roles.add(rol);
        newUsuario.setRoles(roles);
        newUsuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));

        return mapearADto(usuarioRepository.save(newUsuario));
    }

    @Override
    public UsuarioDto updateUsuario(UserUpdateDto usuarioDto) {

        Usuario userUpdate=getUsuarioById(usuarioDto.getId());
        userUpdate.setNombre(usuarioDto.getNombre());
        userUpdate.setApellido(usuarioDto.getApellido());
        userUpdate.setEmail(usuarioDto.getEmail());
        userUpdate.setUsername(usuarioDto.getUsername());
        if(usuarioDto.getPassword()!=null){
            userUpdate.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        }

        userUpdate.setPhotoProfile(usuarioDto.getPhotoProfile()==null? userUpdate.getPhotoProfile() : usuarioDto.getPhotoProfile());

        return mapearADto(usuarioRepository.save(userUpdate));
    }

    @Override
    public List<UsuarioDto> getAllUsuarios() {

       return usuarioRepository.findAll().stream().map(
               this::mapearADto
       ).toList();
    }

    @Override
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                ()->  new ResourceNotFoundException("User","el ID: "+id)
        );
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Boolean usernameUsed(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    @Override
    public Boolean emailUsed(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public Optional<Usuario> getUserActual(String username, String email) {
        return usuarioRepository.findByUsernameOrEmail(username,email);
    }

    @Override
    public Boolean updateUsernameCheck(String username, Long id) {
        return username.equals(getUsuarioById(id).getUsername()) || !usernameUsed(username);
    }

    @Override
    public Boolean updateEmailCheck(String email, Long id) {
        return email.equals(getUsuarioById(id).getEmail()) || !emailUsed(email);
    }

    @Override
    public List<UsuarioDto> getFriends(Long usuarioId) {


        return usuarioRepository.getFriends(usuarioId)
                .stream()
                .map(this::mapearADto).toList();
    }

    @Override
    public List<UsuarioDto> getNotFriends(Long usuarioId) {



        return usuarioRepository.getNotFriends(usuarioId)
                .stream()
                .map(this::mapearADto).toList();
    }

    @Override
    public List<UsuarioDto> getSugerenciasUser(Long usuarioId) {
        return usuarioRepository.getSugerenciasOfFriends(usuarioId)
                .stream()
                .map(this::mapearADto)
                .toList();
    }

    @Override
    public UsuarioDto loginAndLogoutUser(Usuario usuario, boolean estadoConextion) {

        usuario.setEstadoConexion(estadoConextion);
        Usuario usuarioSetConexion=usuarioRepository.save(usuario);

        return mapearADto(usuarioSetConexion);
    }

    @Override
    public UsuarioDto getUserByUsername(String username) {
        return mapearADto(usuarioRepository.findUsuarioByUsername(username));
    }

    @Override
    public Usuario loadUserByUsername(String username) {
        return usuarioRepository.findUsuarioByUsername(username);
    }


    public UsuarioDto mapearADto(Usuario usuario){

        return mapper.map(usuario, UsuarioDto.class);
    }

    public Usuario mapearToUsuario(UsuarioDto usuarioDto){
        return mapper.map(usuarioDto, Usuario.class);
    }


}
