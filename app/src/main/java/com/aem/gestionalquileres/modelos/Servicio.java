package com.aem.gestionalquileres.modelos;

public class Servicio {
    private String id;
    private String descripcion;
    private String empresaId; // Puede ser una referencia a un ID de Empresa

    // Constructor vacío necesario para Firestore
    public Servicio() {
    }

    public Servicio(String id, String descripcion, String empresaId) {
        this.id = id;
        this.descripcion = descripcion;
        this.empresaId = empresaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Servicio servicio = (Servicio) o;

        return id != null ? id.equals(servicio.id) : servicio.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
