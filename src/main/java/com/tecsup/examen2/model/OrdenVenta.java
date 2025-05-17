package com.tecsup.examen2.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ordenventa")
public class OrdenVenta {

    @Id
    @Column(name = "nroordenvta")
    private Integer nroOrdenVta;

    @Column(name = "fechaemision")
    private LocalDate fechaEmision;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "situacion")
    private String situacion;

    // Relación uno a muchos con DetalleOrdenVta
    @OneToMany(mappedBy = "ordenVenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleOrdenVta> detalles;

    // Getters y Setters

    public Integer getNroOrdenVta() {
        return nroOrdenVta;
    }

    public void setNroOrdenVta(Integer nroOrdenVta) {
        this.nroOrdenVta = nroOrdenVta;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public List<DetalleOrdenVta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrdenVta> detalles) {
        this.detalles = detalles;
    }
}
