package com.aem.gestionalquileres.modelos;

public class Empresa {
    private String id;
    private String nombre;
    private String contactoId; // Puede ser una referencia a un ID de Contacto

    // Constructor vacío necesario para Firestore
    public Empresa() {
    }

    public Empresa(String id, String nombre, String contactoId) {
        this.id = id;
        this.nombre = nombre;
        this.contactoId = contactoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContactoId() {
        return contactoId;
    }

    public void setContactoId(String contactoId) {
        this.contactoId = contactoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empresa empresa = (Empresa) o;

        return id != null ? id.equals(empresa.id) : empresa.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
