package com.example.sistema_ventas.service;

import com.example.sistema_ventas.model.Producto;
import com.example.sistema_ventas.model.Venta;
import com.example.sistema_ventas.model.DetalleVenta;
import com.example.sistema_ventas.repository.DetalleRepositoy;
import com.example.sistema_ventas.repository.VentaRepository;

import com.example.sistema_ventas.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleRepositoy detalleRepositoy;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;
    @Transactional
    public Venta registrarVenta(Venta venta){

        for (DetalleVenta detalle : venta.getDetalles()){
            //por cada producto de la venta revisa si hay stock
            productoService.hayStock(detalle.getProducto().getId(), detalle.getCantidad());
        }
        //guarda la venta con un total de 0.0 antes de los cálculos
        venta.setTotal(0.0);
        ventaRepository.save(venta);

        //hacer variables para los calculos
        double subtotal= 0.0;
        double total = 0.0;

        for(DetalleVenta detalle : venta.getDetalles()){

            Producto productoCompleto = productoService.buscarPorId(detalle.getProducto().getId());
            //el atributo precio del modelo Producto
            double precio = productoCompleto.getPrecio();
            //para detalle del Objeto DetalleVenta revisa por cada lista ligada a la venta

            //en cada iteración hace un subtotal
            subtotal = detalle.getCantidad() * precio;
            // y lo guarda en detalle
            detalle.setPrecioUnitario(precio);
            detalle.setSubTotal(subtotal);
            //descontar del stock
            productoService.restarStock(detalle.getProducto().getId(), detalle.getCantidad());
            //asignar la venta al detalle venta (su id por así decirlo)
            detalle.setVenta(venta);
            total +=subtotal;
            detalleRepositoy.save(detalle);
        }
        //le asigna nuevamente el total calculado
        venta.setTotal(total);
        //finalmente guarda la venta con ayuda de los repository
        ventaRepository.save(venta);
        return venta;
    }

    @Transactional
    public Venta cancelarVenta(Long id){

        Venta venta = ventaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));


        for (DetalleVenta detalle : venta.getDetalles()) {

           Producto producto = productoService.buscarPorId(detalle.getProducto().getId());
           producto.setStock(producto.getStock() + detalle.getCantidad());
           productoService.guardar(producto);
        }

        venta.setActiva(false);
        ventaRepository.save(venta);
        return venta;
    }
}
