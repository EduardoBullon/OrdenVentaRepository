package com.tecsup.examen2.service;

import com.tecsup.examen2.model.DetalleOrdenVta;
import com.tecsup.examen2.model.OrdenVenta;
import com.tecsup.examen2.repository.OrdenVentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenVentaService {

    private final OrdenVentaRepository ordenVentaRepository;

    @Autowired
    public OrdenVentaService(OrdenVentaRepository ordenVentaRepository) {
        this.ordenVentaRepository = ordenVentaRepository;
    }

    public List<OrdenVenta> listarTodo() {
        return ordenVentaRepository.findAll();
    }

    public Optional<OrdenVenta> buscarPorId(Integer id) {
        return ordenVentaRepository.findById(id);
    }

    public OrdenVenta guardar(OrdenVenta ordenVenta) {
        return ordenVentaRepository.save(ordenVenta);
    }

    public void eliminar(Integer id) {
        ordenVentaRepository.deleteById(id);
    }

    @Transactional
    public OrdenVenta actualizar(OrdenVenta ordenVentaNueva) {
        OrdenVenta ordenExistente = ordenVentaRepository.findById(ordenVentaNueva.getNroOrdenVta())
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        // Actualizar campos básicos
        ordenExistente.setFechaEmision(ordenVentaNueva.getFechaEmision());
        ordenExistente.setMotivo(ordenVentaNueva.getMotivo());
        ordenExistente.setSituacion(ordenVentaNueva.getSituacion());

        // Eliminar detalles que ya no están en la nueva lista
        ordenExistente.getDetalles().removeIf(detalleExistente ->
                ordenVentaNueva.getDetalles().stream()
                        .noneMatch(detalleNuevo -> detalleNuevo.getId().equals(detalleExistente.getId()))
        );

        // Agregar o actualizar detalles
        for (DetalleOrdenVta detalleNuevo : ordenVentaNueva.getDetalles()) {
            boolean existe = false;
            for (DetalleOrdenVta detalleExistente : ordenExistente.getDetalles()) {
                if (detalleExistente.getId().equals(detalleNuevo.getId())) {
                    // Actualizar campos del detalle existente
                    detalleExistente.setDescripcionMed(detalleNuevo.getDescripcionMed());
                    detalleExistente.setCantidadRequerida(detalleNuevo.getCantidadRequerida());
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                // Añadir nuevo detalle y establecer relación
                detalleNuevo.setOrdenVenta(ordenExistente);
                ordenExistente.getDetalles().add(detalleNuevo);
            }
        }

        return ordenVentaRepository.save(ordenExistente);
    }
}
