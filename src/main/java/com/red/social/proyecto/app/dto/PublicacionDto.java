package com.red.social.proyecto.app.dto;

import com.fasterxml.jackson.annotation.*;
import com.red.social.proyecto.app.entity.Comentario;
import com.red.social.proyecto.app.entity.Like;
import com.red.social.proyecto.app.entity.Usuario;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublicacionDto {

    private Long id;


    @NotNull
    @NotEmpty
    private String contenido;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSSSSS",timezone = "America/Lima")
    private Date fechaCreacion;
    @JsonFormat(pattern = "dd-MM-yyyy  HH:mm:ss.SSSSSS",timezone = "America/Lima")
    private Date fechaModificacion;
    private String imagenPost;

    @JsonIgnore
    private List<Comentario> comentarios= new ArrayList<>();



    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario autor;

    private List<Like> likesPublicacion=new ArrayList<>();

    public PublicacionDto(){}

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

    public String getImagenPost() {
        return imagenPost;
    }

    public void setImagenPost(String imagenPost) {
        this.imagenPost = imagenPost;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }


    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public List<Like> getLikesPublicacion() {
        return likesPublicacion;
    }

    public void setLikesPublicacion(List<Like> likesPublicacion) {
        this.likesPublicacion = likesPublicacion;
    }
}
