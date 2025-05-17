package com.tecsup.examen2.repository;

import com.tecsup.examen2.model.DetalleOrdenVta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenVtaRepository extends JpaRepository<DetalleOrdenVta, Integer> {
}
