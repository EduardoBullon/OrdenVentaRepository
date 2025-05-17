package com.tecsup.examen2.controller;

import com.tecsup.examen2.model.OrdenVenta;
import com.tecsup.examen2.service.OrdenVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenventa")
public class OrdenVentaController {

    private final OrdenVentaService ordenVentaService;

    // Inyección por constructor (recomendado)
    @Autowired
    public OrdenVentaController(OrdenVentaService ordenVentaService) {
        this.ordenVentaService = ordenVentaService;
    }

    // Obtener todas las órdenes de venta
    @GetMapping
    public List<OrdenVenta> listarTodas() {
        return ordenVentaService.listarTodo();
    }

    // Obtener orden por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenVenta> obtenerPorId(@PathVariable Integer id) {
        return ordenVentaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nueva orden
    @PostMapping
    public OrdenVenta crearOrden(@RequestBody OrdenVenta ordenVenta) {
        return ordenVentaService.guardar(ordenVenta);
    }

    // Actualizar orden existente
    @PutMapping("/{id}")
    public ResponseEntity<OrdenVenta> actualizarOrden(@PathVariable Integer id, @RequestBody OrdenVenta ordenActualizada) {
        return ordenVentaService.buscarPorId(id).map(orden -> {
            orden.setFechaEmision(ordenActualizada.getFechaEmision());
            orden.setMotivo(ordenActualizada.getMotivo());
            orden.setSituacion(ordenActualizada.getSituacion());
            orden.setDetalles(ordenActualizada.getDetalles());
            OrdenVenta ordenGuardada = ordenVentaService.guardar(orden);
            return ResponseEntity.ok(ordenGuardada);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar orden por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Integer id) {
        return ordenVentaService.buscarPorId(id).map(orden -> {
            ordenVentaService.eliminar(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
