package com.aem.gestionalquileres.modelos;


public class Recibo {
    private String id;
    private String concepto;
    private double monto;
    private String personaId; // Puede ser una referencia a un ID de Persona

    // Constructor vacío necesario para Firestore
    public Recibo() {
    }

    public Recibo(String id, String concepto, double monto, String personaId) {
        this.id = id;
        this.concepto = concepto;
        this.monto = monto;
        this.personaId = personaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recibo recibo = (Recibo) o;

        return id != null ? id.equals(recibo.id) : recibo.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
