package com.red.social.proyecto.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario likeador;
    @ManyToOne
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    private Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "comentario_id", referencedColumnName = "id")
    private Comentario comentario;

    public Like() {
    }

    public Like(Usuario likeador, Publicacion publicacion, Comentario comentario) {
        this.likeador = likeador;
        this.publicacion = publicacion;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getLikeador() {
        return likeador;
    }

    public void setLikeador(Usuario likeador) {
        this.likeador = likeador;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
}
