package com.aem.gestionalquileres.modelos;

import com.google.firebase.firestore.DocumentReference;

public class Alquiler {
    private String id; // ID único del alquiler
    private DocumentReference casaRef; // Referencia a la entidad Casa
    private DocumentReference personaRef; // Referencia a la entidad Persona
    private String fechaInicio;
    private String fechaFin;
    private Double renta; // Precio del alquiler
    private Boolean renovable; // Indica si es renovable
    private Double incrementoAnual; // Incremento anual en porcentaje

    // Constructor vacío necesario para Firestore
    public Alquiler() {
    }

    public Alquiler(String id, DocumentReference casaRef, DocumentReference personaRef,
                    String fechaInicio, String fechaFin, Double renta,
                    Boolean renovable, Double incrementoAnual) {
        this.id = id;
        this.casaRef = casaRef;
        this.personaRef = personaRef;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.renta = renta;
        this.renovable = renovable;
        this.incrementoAnual = incrementoAnual;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DocumentReference getCasaRef() {
        return casaRef;
    }

    public void setCasaRef(DocumentReference casaRef) {
        this.casaRef = casaRef;
    }

    public DocumentReference getPersonaRef() {
        return personaRef;
    }

    public void setPersonaRef(DocumentReference personaRef) {
        this.personaRef = personaRef;
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

    public Double getRenta() {
        return renta;
    }

    public void setRenta(Double renta) {
        this.renta = renta;
    }

    public Boolean getRenovable() {
        return renovable;
    }

    public void setRenovable(Boolean renovable) {
        this.renovable = renovable;
    }

    public Double getIncrementoAnual() {
        return incrementoAnual;
    }

    public void setIncrementoAnual(Double incrementoAnual) {
        this.incrementoAnual = incrementoAnual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alquiler alquiler = (Alquiler) o;

        // Comparar los campos relevantes
        if (!id.equals(alquiler.id)) return false;
        if (!casaRef.equals(alquiler.casaRef)) return false;
        if (!personaRef.equals(alquiler.personaRef)) return false;
        if (!fechaInicio.equals(alquiler.fechaInicio)) return false;
        if (!fechaFin.equals(alquiler.fechaFin)) return false;
        if (!renta.equals(alquiler.renta)) return false;
        if (!renovable.equals(alquiler.renovable)) return false;
        return incrementoAnual.equals(alquiler.incrementoAnual);
    }
}
