package com.tecsup.examen2.repository;

import com.tecsup.examen2.model.OrdenVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenVentaRepository extends JpaRepository<OrdenVenta, Integer> {
}
