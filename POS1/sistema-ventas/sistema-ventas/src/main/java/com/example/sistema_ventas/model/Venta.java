package com.example.sistema_ventas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "ventas")
@Data
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaVenta;




    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    private Boolean activa = true;

    private Double total;

    @JsonManagedReference
    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> detalles;

    // metodo para que la fecha tome el momento donde se agrega una venta sin tener que hacer manual
    @PrePersist
    public void prePersist(){
        this.fechaVenta = LocalDateTime.now();
    }


}
