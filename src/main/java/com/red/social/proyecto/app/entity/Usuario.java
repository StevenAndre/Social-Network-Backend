package com.red.social.proyecto.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario   {

    public Usuario(){}

    public Usuario(String nombre, String apellido, String username, String password, String email, String photoProfile, Boolean estadoConexion, List<Publicacion> publicacions, List<Comentario> comentarios, List<Like> likes, Set<Rol> roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.email = email;
        this.photoProfile = photoProfile;
        this.estadoConexion = estadoConexion;
        this.publicacions = publicacions;
        this.comentarios = comentarios;
        this.likes = likes;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotEmpty(message = "el nombre es un campo obligatorio")
    @Column(nullable = false,length = 60)
    private String nombre;
    @NotBlank
    @NotEmpty(message = "el apellido es un campo obligatorio")
    @Column(nullable = false,length = 70)
    private String apellido;
    @Column(unique = true,length = 60)
    @NotEmpty(message = "el username es requerido")
    private String username;
    @NotBlank
    @NotEmpty(message = "La contraseña es requerida")
    private String password;
    @Column(unique = true,length = 60)
    @Email
    @NotEmpty(message = "el email es requerido")
    private String email;
    private String photoProfile;
    private Boolean estadoConexion=false;
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy ="autor",cascade = CascadeType.ALL)
    private List<Publicacion> publicacions= new ArrayList<>();
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy ="autor",cascade = CascadeType.ALL)
    private List<Comentario> comentarios= new ArrayList<>();

    @OneToMany(mappedBy ="likeador",cascade = CascadeType.ALL)
    private List<Like> likes= new ArrayList<>();




    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles",joinColumns =@JoinColumn(name = "user_id",referencedColumnName = "id")
            ,inverseJoinColumns =@JoinColumn(name = "role_id",referencedColumnName = "id") )
    private Set<Rol> roles= new HashSet<>();

    @OneToMany(mappedBy = "amigo",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Amistad> amigos=new HashSet<>();

    @OneToMany(mappedBy = "solicitante",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Solicitud> solicitudes= new ArrayList<>();
    @OneToMany(mappedBy = "emisor",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Mensaje> mensajes= new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public List<Publicacion> getPublicacions() {
        return publicacions;
    }

    public void setPublicacions(List<Publicacion> publicacions) {
        this.publicacions = publicacions;
    }

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

    public Set<Amistad> getAmigos() {
        return amigos;
    }

    public void setAmigos(Set<Amistad> amigos) {
        this.amigos = amigos;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", photoProfile='" + photoProfile + '\'' +
                ", estadoConexion=" + estadoConexion +
                ", roles=" + roles +
                '}';
    }
}
