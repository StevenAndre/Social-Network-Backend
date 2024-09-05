package com.red.social.proyecto.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.red.social.proyecto.app.entity.Comentario;
import com.red.social.proyecto.app.entity.Like;
import com.red.social.proyecto.app.entity.Publicacion;
import com.red.social.proyecto.app.entity.Rol;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsuarioDto {



    private Long id;
    @NotEmpty(message = "el username es requerido")
    @NotBlank
    private String username;
    @Email
    @NotEmpty(message = "el email es requerido")
    private String email;
    @NotBlank
    @NotNull
    @NotEmpty
    private String password;
    @NotEmpty(message = "el username es requerido")
    @NotBlank
    private String nombre;
    @NotEmpty(message = "el apellido es requerido")
    @NotBlank
    private String apellido;
    private String photoProfile;
    private Boolean estadoConexion=false;

    private List<Publicacion> publicaciones= new ArrayList<>();



    private List<Comentario> comentarios= new ArrayList<>();

    private List<Like> likes= new ArrayList<>();


    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicacions) {
        this.publicaciones = publicacions;
    }

    @JsonIgnore
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    private Set<Rol> roles= new HashSet<>();

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPhotoProfile() {
        return photoProfile;
    }

    public void setPhotoProfile(String photoProfile) {
        this.photoProfile = photoProfile;
    }

    public Boolean getEstadoConexion() {
        return estadoConexion;
    }

    public void setEstadoConexion(Boolean estadoConexion) {
        this.estadoConexion = estadoConexion;
    }


}
