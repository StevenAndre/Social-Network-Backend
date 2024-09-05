package com.red.social.proyecto.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 2000,nullable = false)
    private String contenido;

    private Date fechaCreacion;
    private Date fechaModificacion;

    @JsonBackReference(value = "publicacion-comentario")
    @ManyToOne
    @JoinColumn(name="publicacion_id",referencedColumnName = "id")
    private Publicacion publicacion;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario_id",referencedColumnName = "id")
    private Usuario autor;
    @OneToMany(mappedBy = "comentario",cascade = CascadeType.ALL)
    private List<Like> likesComentario= new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comentario_id",referencedColumnName = "id")
    private Comentario comentarioPadre;

    @OneToMany(mappedBy ="comentarioPadre",cascade = CascadeType.ALL)
    private List<Comentario> comentariosHijos=new ArrayList<>();


    public Comentario() {
    }

    public Comentario(String contenido, Date fechaCreacion, Date fechaModificacion, Publicacion publicacion, Usuario autor, List<Like> likesComentario) {
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.publicacion = publicacion;
        this.autor = autor;
        this.likesComentario = likesComentario;
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

    public Comentario getComentario() {
        return comentarioPadre;
    }

    public void setComentario(Comentario comentarioPadre) {
        this.comentarioPadre = comentarioPadre;
    }

    public List<Comentario> getComentarios() {
        return comentariosHijos;
    }

    public void setComentarios(List<Comentario> comentariosHijos) {
        this.comentariosHijos = comentariosHijos;
    }
}
