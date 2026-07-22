package com.example.sistema_ventas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "detalleVenta")
@Data
public class DetalleVenta {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idDetalle;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;
    @ManyToOne
    @JoinColumn (name = "id_producto")
    private Producto producto;
    @Column (nullable = false)
    private Double subTotal;
    @Column (nullable = false)
    private Integer cantidad;
    @Column (nullable = false)
    private Double precioUnitario;
}
