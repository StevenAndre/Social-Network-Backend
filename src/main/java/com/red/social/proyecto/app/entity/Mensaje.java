package com.red.social.proyecto.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;
    @Column(name = "fecha_envio")
    private Date fechaEnvio;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name="emisor_id",referencedColumnName = "id")
    private Usuario emisor;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name="receptor_id",referencedColumnName = "id")
    private Usuario receptor;


    @Transient
    private String userEmiter;
    @Transient
    private String userRecetor;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
    }

    public String getUserEmiter() {
        return userEmiter;
    }

    public void setUserEmiter(String userEmiter) {
        this.userEmiter = userEmiter;
    }

    public String getUserRecetor() {
        return userRecetor;
    }

    public void setUserRecetor(String userRecetor) {
        this.userRecetor = userRecetor;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", userEmiter='" + userEmiter + '\'' +
                ", userRecetor='" + userRecetor + '\'' +
                '}';
    }
}
