package com.example.sistema_ventas.service;


import com.example.sistema_ventas.model.Producto;

import com.example.sistema_ventas.repository.ProductoRepository;
// para inyección de dependecias, usa el repositorio y ya no hace new ProductoRepository()
import org.springframework.beans.factory.annotation.Autowired;
// le dice que esta clase que es un componente del servicio
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
// la logica de negocio va aquí
    @Autowired
    private ProductoRepository productoRepository;

    //listar productos, solo los activo = true
    public List<Producto> listarTodos(){
        //usa stream para filtrar la lista en la RAM antes de mandar a controlador
        return productoRepository.findAll()
                .stream().
                filter(p -> p.getActivo() != null && p.getActivo()).toList();
    }

    //guardar o actualizar un prodcuto

    public Producto guardar(Producto producto){

        if (producto.getPrecio() < 0 || producto.getStock() < 0){
           throw new IllegalArgumentException("El precio y el stock no pueden ser negativos.");
        }
        return productoRepository.save(producto);

    }

    // buscar un producto por id

    public Producto buscarPorId(Long id){

        // si el producto existe pero está inactivo, no aparecerá,por el borrado lógico

        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null && !producto.getActivo()){
            return null;
        }
        return producto;
    }

    // Eliminar un producto

   public void eliminarLogico(Long id){
        Producto producto = productoRepository.findById(id).orElse(null);

        if (producto != null){
            producto.setActivo(false);
            productoRepository.save(producto);
        }
   }

   //validar stock
   public boolean hayStock(Long id, Integer cantidad){
       Producto producto = productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        if(cantidad  <= 0){
            return false;
        }
        if (cantidad > producto.getStock()){
            throw new IllegalArgumentException("La cantidad eleccionada excede el stock actual");
        }
        //restar a stock
        return true;

   }
   //restar a stock
   public void restarStock(Long id, Integer cantidad){
       Producto producto = productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        if(hayStock(producto.getId(), cantidad)){
           producto.setStock(producto.getStock() - cantidad);
           productoRepository.save(producto);

       }
   }
}
