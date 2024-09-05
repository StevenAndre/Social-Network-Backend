package com.red.social.proyecto.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "amistades")
public class Amistad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="usuario_id",referencedColumnName = "id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="amigo_id",referencedColumnName = "id")
    private Usuario amigo;



    public Amistad(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getAmigo() {
        return amigo;
    }

    public void setAmigo(Usuario amigo) {
        this.amigo = amigo;
    }


}
