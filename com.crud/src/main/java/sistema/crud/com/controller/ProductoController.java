package sistema.crud.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistema.crud.com.dto.Mensaje;
import sistema.crud.com.entities.ProductoEntity;
import sistema.crud.com.service.ProductoService;

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
	
	

}
