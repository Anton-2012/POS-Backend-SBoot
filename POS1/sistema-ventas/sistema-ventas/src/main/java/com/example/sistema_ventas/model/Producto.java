package com.example.sistema_ventas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity //para que tenga un espejo con la BD
@Table(name = "productos")
@Data  //genera getters y setters
public class Producto {

    @Id //define cual va a ser el id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para que sea auto increment
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private Double precio;

    private Integer stock;

    private Boolean activo = true;
}
