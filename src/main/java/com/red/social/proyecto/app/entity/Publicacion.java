package com.red.social.proyecto.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "publicaciones")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3000)
    private String contenido;

    @JsonFormat(pattern = "dd-MM-yyyy  HH:mm:ss.SSSSSS",timezone = "America/Lima")
    private Date fechaCreacion;
    @JsonFormat(pattern = "dd-MM-yyyy  HH:mm:ss.SSSSSS",timezone = "America/Lima")
    private Date fechaModificacion;
    private String imagenPost;
    @JsonManagedReference(value = "publicacion-comentario")
    @JsonIgnore
    @OneToMany(mappedBy = "publicacion",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comentario> comentarios= new ArrayList<>();
    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario_id",referencedColumnName = "id")
    private Usuario autor;
    @OneToMany(mappedBy = "publicacion",cascade = CascadeType.ALL)
    private List<Like> likesPublicacion=new ArrayList<>();


    public Publicacion() {
    }

    public Publicacion(String contenido, Date fechaCreacion, Date fechaModificacion, String imagenPost, List<Comentario> comentarios, Usuario autor, List<Like> likesPublicacion) {
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.imagenPost = imagenPost;
        this.comentarios = comentarios;
        this.autor = autor;
        this.likesPublicacion = likesPublicacion;
    }

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


    @Override
    public String toString() {
        return "Publicacion{" +
                "id=" + id +
                ", contenido='" + contenido + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaModificacion=" + fechaModificacion +
                ", imagenPost='" + imagenPost + '\'' +
                ", comentarios=" + comentarios +
                ", autor=" + autor +
                ", likesPublicacion=" + likesPublicacion +
                '}';
    }
}
