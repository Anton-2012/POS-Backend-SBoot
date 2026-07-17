package com.example.sistema_ventas.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "empleados")
@Data
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private Integer edad;

    private String direccion;

    private String correo;

    private String telefono;

    private double sueldoBase;


    private Boolean activo = true;

    @Enumerated(EnumType.STRING)
    private Rol rol;


}
