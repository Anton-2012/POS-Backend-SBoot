package com.example.sistema_ventas.service;

import com.example.sistema_ventas.model.Empleado;

import com.example.sistema_ventas.model.Producto;
import com.example.sistema_ventas.repository.EmpleadoRepository;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> listarTodos(){

        return empleadoRepository.findAll().stream().filter(e -> e.getActivo() != null && e.getActivo()).toList();
    }

    public Empleado guardar(@NonNull Empleado empleado){

        if (empleado.getEdad() < 18){
            throw new IllegalArgumentException("La edad no puede ser menor de 18");
        }

        if (empleado.getCorreo() == null || !empleado.getCorreo().contains("@")){
            throw new IllegalArgumentException("Debe de ser un correo válido");
        }
        return empleadoRepository.save(empleado);

    }

    //buscar por ID

    public Empleado buscarPorId(Long id){

        Empleado empleado = empleadoRepository.findById(id).orElse(null);

        if (empleado != null && !empleado.getActivo()){
            return null;
        }
        return empleado;
    }

    //eliminar empleado

    public void eliminarLogico(Long id){
        Empleado empleado = empleadoRepository.findById(id).orElse(null);

        if (empleado != null){
            empleado.setActivo(false);
            empleadoRepository.save(empleado);
        }
    }
}
