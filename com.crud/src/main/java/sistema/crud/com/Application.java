package sistema.crud.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import sistema.crud.com.controller.ProductoController;

@SpringBootApplication
@ComponentScan(basePackageClasses=ProductoController.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
