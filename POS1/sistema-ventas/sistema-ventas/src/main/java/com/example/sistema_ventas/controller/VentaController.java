package com.example.sistema_ventas.controller;

import com.example.sistema_ventas.model.Producto;
import com.example.sistema_ventas.model.Venta;
import com.example.sistema_ventas.model.DetalleVenta;
import com.example.sistema_ventas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
// herramientas web de spring
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public Venta crearVenta(@RequestBody Venta venta){
        return ventaService.registrarVenta(venta);
    }

    //hola
    @PutMapping( "/{id}")
    public Venta desactivar(@PathVariable Long id){
        return ventaService.cancelarVenta(id);
    }
}
