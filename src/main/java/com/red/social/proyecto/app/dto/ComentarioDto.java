package com.red.social.proyecto.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.red.social.proyecto.app.entity.Like;
import com.red.social.proyecto.app.entity.Publicacion;
import com.red.social.proyecto.app.entity.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComentarioDto {

    public ComentarioDto(){}

    private Long id;
    @NotEmpty(message = "El contenido no puede estar vacio")
    @NotNull(message = "El contenido no puede estar nulo")
    private String contenido;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSSSSS",timezone = "America/Lima")
    private Date fechaCreacion;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSSSSS",timezone = "America/Lima")
    private Date fechaModificacion;
    @JsonIgnore
    private Publicacion publicacion;
    private Usuario autor;
    private List<Like> likesComentario= new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public List<Like> getLikesComentario() {
        return likesComentario;
    }

    public void setLikesComentario(List<Like> likesComentario) {
        this.likesComentario = likesComentario;
    }
}
