package com.example.sistema_ventas.repository;
// para saber con que clase va a trabajar
import com.example.sistema_ventas.model.Producto;
// trae todos los métodos genéricos
import org.springframework.data.jpa.repository.JpaRepository;
// le dice a esta clase que manejará el acceso a datos
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    // aquí ya se hereda de JpaRepository, ya hay metodos como .save, .find y findById y así ya soy mas huevon
}