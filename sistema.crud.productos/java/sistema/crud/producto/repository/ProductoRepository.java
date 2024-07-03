package sistema.crud.producto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistema.crud.producto.entities.ProductoEntity;


@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer>{
	
	
	Optional<ProductoEntity> findByNombre(String nombre);
	
	boolean existsByNombre(String nombre);
	

}
