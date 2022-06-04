package com.b2chat.orden.compra;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * Clase para inicializar el servidor de SpringBoot y su configuracion.
 * 
 * @author Jonatan Velandia
 *
 */
@SpringBootApplication
public class OrdenCompraApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(OrdenCompraApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OrdenCompraApplication.class);
	}

}
