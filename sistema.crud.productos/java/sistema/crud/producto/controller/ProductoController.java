package sistema.crud.producto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.util.StringUtils;
import sistema.crud.producto.dto.Mensaje;
import sistema.crud.producto.dto.ProductoDto;
import sistema.crud.producto.entities.ProductoEntity;
import sistema.crud.producto.service.ProductoService;


@RestController
@RequestMapping("/v1/api/producto")
@CrossOrigin("http://localhost:4200")
public class ProductoController {
	
	@Autowired
	ProductoService productoService;
	
	@GetMapping("saludo/{parametro}")
	public ResponseEntity<Mensaje> test(@PathVariable("parametro") String parametro) {
		Mensaje mensaje = new Mensaje("Hola, " +parametro);
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<ProductoEntity>> list(){
		List<ProductoEntity> list = productoService.list();
		return new ResponseEntity<List<ProductoEntity>>(list,HttpStatus.OK);
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/details/{id}")
	public ResponseEntity<ProductoEntity> getById(@PathVariable("id") Integer id){
		
		if(!productoService.existById(id)) {
			return new ResponseEntity(new Mensaje("El id que esta consultando no existe!!"), HttpStatus.NOT_FOUND);	
		}else {
			ProductoEntity productoEntity = productoService.getOne(id).get();
			return new ResponseEntity<ProductoEntity>(productoEntity, HttpStatus.OK);	
		}			
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/detailname/{nombre}")
	public ResponseEntity<ProductoEntity> getByNombre(@PathVariable("nombre") String nombre){
		
		if(!productoService.existByNombre(nombre)) {
			return new ResponseEntity(new Mensaje("El nombre que esta consultando no existe!!"), HttpStatus.NOT_FOUND);	
		}else {
			ProductoEntity productoEntity = productoService.getByNombre(nombre).get();
			return new ResponseEntity<ProductoEntity>(productoEntity, HttpStatus.NOT_FOUND);	
		}	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
    public ResponseEntity<?> create(@Validated  @RequestBody  ProductoDto productoDto){

        if(StringUtils.isBlank(productoDto.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre del producto es obligatorio!!"), HttpStatus.BAD_REQUEST);
        }

        if(productoDto.getPrecio() <= 0){
            return new ResponseEntity(new Mensaje("El precio obligatorio o debe ser mayor que 0!!"), HttpStatus.BAD_REQUEST);
        }
        if(productoService.existByNombre(productoDto.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre ya esta siendo usado en el sistema!!"), HttpStatus.BAD_REQUEST);
        }

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setNombre(productoDto.getNombre());
        productoEntity.setPrecio(productoDto.getPrecio());
        productoService.save(productoEntity);
        return new ResponseEntity<>(new Mensaje("El producto fue registrado de forma exitosa!!!"), HttpStatus.CREATED);
    }
	
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProductoDto productoDto){
        if(!productoService.existById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(productoService.existByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio()==null || productoDto.getPrecio()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        ProductoEntity productoEntity = productoService.getOne(id).get();
        productoEntity.setNombre(productoDto.getNombre());
        productoEntity.setPrecio(productoDto.getPrecio());
        productoService.save(productoEntity);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
         if(!productoService.existById(id)){
            return new ResponseEntity<>(new Mensaje("El id del producto no existe"), HttpStatus.NOT_FOUND);
         }else{
             productoService.delete(id);
             return new ResponseEntity<>(new Mensaje("Producto eliminado de forma exitosa"), HttpStatus.OK);
         }
    }
	
	
	
	
	
	
	

}
