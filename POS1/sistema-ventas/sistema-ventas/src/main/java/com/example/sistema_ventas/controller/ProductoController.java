package com.example.sistema_ventas.controller;

import com.example.sistema_ventas.model.Producto;
import com.example.sistema_ventas.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
// herramientas web de spring
import org.springframework.web.bind.annotation.*;

import java.util.List;

// va a responder peticiones http
@RestController
// define ruta para entrar a este controlador
@RequestMapping("/api/productos") // Todas las rutas de este controlador empezarán con esto
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Ruta para listar: http://localhost:8080/api/productos
    //mapea las peticiones tipo http get
    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarTodos();
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){
        return productoService.guardar(producto);
    }

    // 4. ELIMINAR (BORRADO LÓGICO): DELETE http://localhost:8080/api/productos/{id}
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id){
        productoService.eliminarLogico(id);
        return "el producto con ID " + id + " Ha sido desactivado del catálogo ";
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto datosNuevos){
        Producto productoExistente = productoService.buscarPorId(id);

        if(productoExistente != null){
            //reemplazar datos viejos con los nuevos que vienen del JSON
            productoExistente.setNombre(datosNuevos.getNombre());
            productoExistente.setPrecio(datosNuevos.getPrecio());
            productoExistente.setStock(datosNuevos.getStock());

            // se mandan a servicio el cual valida que no sean negativos y lo guarda
            return productoService.guardar(productoExistente);
        }
        return null;
    }
}

