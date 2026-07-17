package com.example.sistema_ventas.controller;

import com.example.sistema_ventas.model.Empleado;
import com.example.sistema_ventas.service.EmpleadoService;
import com.example.sistema_ventas.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

//poner la url con la que voy a hacer las apis rest

@RequestMapping("api/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private ProductoService productoService;

    // lo que voy a obtener cuando ponga get en el rest (la lista
    @GetMapping
    public List<Empleado> listarEmpleados(){
        return empleadoService.listarTodos();
    }
    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado){
        return empleadoService.guardar(empleado);
    }
    @DeleteMapping("/{id}")
    public String eliminarEmpleado(@PathVariable Long id){
        empleadoService.eliminarLogico(id);
        return "el producto con id: " + id + " Ha sido desactivado del catálogo";
    }
    @PutMapping("/{id}")
    public Empleado actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado datosNuevos){

        Empleado empleadoExistente = empleadoService.buscarPorId(id);
        if(empleadoExistente != null){

            empleadoExistente.setDireccion(datosNuevos.getDireccion());
            empleadoExistente.setEdad(datosNuevos.getEdad());
            empleadoExistente.setNombre(datosNuevos.getNombre());
            empleadoExistente.setRol(datosNuevos.getRol());
            empleadoExistente.setCorreo(datosNuevos.getCorreo());
            empleadoExistente.setTelefono(datosNuevos.getTelefono());
            empleadoExistente.setSueldoBase(datosNuevos.getSueldoBase());

            return empleadoService.guardar(empleadoExistente);
        }
        return null;

    }

}
