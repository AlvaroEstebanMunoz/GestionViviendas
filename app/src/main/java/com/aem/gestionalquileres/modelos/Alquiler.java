package com.aem.gestionalquileres.modelos;

public class Alquiler {
    private String id;
    private String casaId; // Puede ser una referencia a un ID de Casa
    private String personaId; // Puede ser una referencia a un ID de Persona
    private String fechaInicio;
    private String fechaFin;

    // Constructor vacío necesario para Firestore
    public Alquiler() {
    }

    public Alquiler(String id, String casaId, String personaId, String fechaInicio, String fechaFin) {
        this.id = id;
        this.casaId = casaId;
        this.personaId = personaId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCasaId() {
        return casaId;
    }

    public void setCasaId(String casaId) {
        this.casaId = casaId;
    }

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alquiler alquiler = (Alquiler) o;

        return id != null ? id.equals(alquiler.id) : alquiler.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
