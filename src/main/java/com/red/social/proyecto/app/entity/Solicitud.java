package com.red.social.proyecto.app.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="solicitudes" )
public class Solicitud {

    public Solicitud(){}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 20)
    private String estado;

    @Column(name = "fecha_solicitud")
    private Date fechaSolicitud;

    @ManyToOne
    @JoinColumn(name="solicitante_id",referencedColumnName = "id")
    private Usuario solicitante;
    @ManyToOne
    @JoinColumn(name="solicitado_id",referencedColumnName = "id")
    private Usuario solicitado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public Usuario getSolicitado() {
        return solicitado;
    }

    public void setSolicitado(Usuario solicitado) {
        this.solicitado = solicitado;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
}
