package com.example.sistema_ventas.repository;

import com.example.sistema_ventas.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRepositoy extends JpaRepository <DetalleVenta, Long> {
}
