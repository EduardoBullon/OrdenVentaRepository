package com.tecsup.examen2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalleordenvta")
public class DetalleOrdenVta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descripcionmed")
    private String descripcionMed;

    @Column(name = "cantidadrequerida")
    private Integer cantidadRequerida;

    @ManyToOne
    @JoinColumn(name = "nroordenvta") // FK hacia OrdenVenta
    private OrdenVenta ordenVenta;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcionMed() {
        return descripcionMed;
    }

    public void setDescripcionMed(String descripcionMed) {
        this.descripcionMed = descripcionMed;
    }

    public Integer getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(Integer cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public OrdenVenta getOrdenVenta() {
        return ordenVenta;
    }

    public void setOrdenVenta(OrdenVenta ordenVenta) {
        this.ordenVenta = ordenVenta;
    }
}
