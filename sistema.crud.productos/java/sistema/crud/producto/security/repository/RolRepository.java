package sistema.crud.producto.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistema.crud.producto.security.entity.Rol;
import sistema.crud.producto.security.enums.RolNombre;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
	
	Optional<Rol> findByRolNombre(RolNombre rolNombre);

}
