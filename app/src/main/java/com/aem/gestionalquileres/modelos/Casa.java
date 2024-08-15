package com.aem.gestionalquileres.modelos;

import com.google.firebase.firestore.DocumentReference;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class Casa implements Serializable {
    private String id;
    private String direccion;
    private Long precio;
    private Boolean alquilable;
    private Boolean alquilado;
    private DocumentReference propietario;
    private String propietarioId;
    private List<Alquiler> alquileres;
    private String foto;
    private String alias;

    // Constructor vacío necesario para Firestore
    public Casa() {
    }

    public Casa(String id, String direccion, long precio, boolean alquilable, boolean alquilado, DocumentReference propietario, String foto, String alias) {
        this.id = id;
        this.direccion = direccion;
        this.precio = precio;
        this.alquilable = alquilable;
        this.alquilado = alquilado;
        this.propietario = propietario;
        this.alquileres = alquileres;
        this.foto = foto;
        this.alias = alias;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public Long getPrecio() { return precio != null ? precio : 0L; }
    public void setPrecio(Long precio) { this.precio = precio; }
    public Boolean isAlquilable(Boolean defaultValue) { return Optional.ofNullable(alquilable).orElse(defaultValue); }
    public void setAlquilable(Boolean alquilable) { this.alquilable = alquilable; }
    public Boolean isAlquilado(Boolean defaultValue) { return Optional.ofNullable(alquilado).orElse(defaultValue); }
    public void setAlquilado(Boolean alquilado) { this.alquilado = alquilado; }
    public DocumentReference getPropietario() { return propietario; }
    public void setPropietario(DocumentReference propietario) { this.propietario = propietario; }
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    // Métodos equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casa casa = (Casa) o;
        return id != null ? id.equals(casa.id) : casa.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Casa{" +
                "id='" + id + '\'' +
                ", precio=" + precio +
                ", direccion='" + direccion + '\'' +
                ", alquilable=" + alquilable +
                ", alquilado=" + alquilado +
                ", propietario=" + propietario +
                ", alquileres=" + alquileres +
                '}';
    }

    public void setPropietarioId(String propietarioId) {
        this.propietarioId = propietarioId;
    }

    public String getPropietarioId() {
        return propietarioId;
    }
}