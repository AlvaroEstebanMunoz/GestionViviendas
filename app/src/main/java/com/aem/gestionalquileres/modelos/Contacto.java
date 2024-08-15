package com.aem.gestionalquileres.modelos;

public class Contacto {
    private String id;
    private String telefono;
    private String direccion;

    // Constructor vacío necesario para Firestore
    public Contacto() {
    }

    public Contacto(String id, String telefono, String direccion) {
        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;

        return id != null ? id.equals(contacto.id) : contacto.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
