package sistema.crud.com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sistema.crud.com.entities.ProductoEntity;
import sistema.crud.com.repository.ProductoRepository;

@Service
@Transactional
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public java.util.List<ProductoEntity> list(){
		return productoRepository.findAll();
	} 
	
	public Optional<ProductoEntity> getOne(Integer id){
		return productoRepository.findById(id);	
	}
	
	
	public Optional<ProductoEntity> getByNombre(String nombre){
		return productoRepository.findByNombre(nombre);	
	}
	
	public void save(ProductoEntity productoEntity){
        productoRepository.save(productoEntity);
    }

    public void delete(int id){
        productoRepository.deleteById(id);
    }

    public boolean existById(int id){
        return  productoRepository.existsById(id);
    }

    public boolean existByNombre(String nombre){
        return  productoRepository.existsByNombre(nombre);
    }

}
