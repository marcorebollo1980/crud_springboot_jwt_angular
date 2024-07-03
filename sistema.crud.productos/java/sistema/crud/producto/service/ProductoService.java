package sistema.crud.producto.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.crud.producto.entities.ProductoEntity;
import sistema.crud.producto.repository.ProductoRepository;


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
