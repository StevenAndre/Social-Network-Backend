package com.red.social.proyecto.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class UserUpdateDto {


    public UserUpdateDto(){}

    public UserUpdateDto(Long id,String username, String email, String password, String nombre, String apellido, String photoProfile, Boolean estadoConexion) {
        this.id=id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.photoProfile = photoProfile;
        this.estadoConexion = estadoConexion;
    }

    private Long id;
    @NotEmpty(message = "el username es requerido")
    @NotBlank
    private String username;
    @Email
    @NotEmpty(message = "el email es requerido")
    private String email;
    private String password;
    @NotEmpty(message = "el username es requerido")
    @NotBlank
    private String nombre;
    @NotEmpty(message = "el apellido es requerido")
    @NotBlank
    private String apellido;
    private String photoProfile;
    private Boolean estadoConexion=true;

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

    public String getPassword() {
        return password;
    }

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
