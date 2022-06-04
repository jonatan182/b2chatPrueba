package com.b2chat.orden.compra.api;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.b2chat.orden.compra.dto.OrdenCompraDto;
import com.b2chat.orden.compra.dto.ResponseApi;
import com.b2chat.orden.compra.service.OrdenCompraApiService;

/**
 * 
 * Api encargada de exponer los servicios para realizar operaciones sobre una orden de compra
 * @author Jonatan Velandia
 *
 */
@RestController
@RequestMapping(value = "/b2chat", produces = "application/json", headers = "Accept=application/json;charset=UTF-8")
public class OrdenCompraApi {
	private static final Logger logger = LoggerFactory.getLogger(OrdenCompraApi.class);
	
	/**
     * Variable inyectada para consumir las funcionalidades de la clase OrdenCompraApiService
     */
	@Autowired
	OrdenCompraApiService ordenCompraService;
	
	
	/**
	 * Metodo encargado de generar una orden de compra
	 * 
	 * @param ordenCompra
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/generarOrdenCompra", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseApi> generarOrdenCompra (@RequestBody OrdenCompraDto ordenCompra){
		logger.info("Ingresando al método: generarOrdenCompra");
		return new ResponseEntity<>(ordenCompraService.generarOrdenCompra(ordenCompra), HttpStatus.CREATED);
	}
	
	/**
	 * Metodo encargado de consultar las diferentes ordenes de pago por fecha de entrega
	 * 
	 * @param fechaEntrega
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/consultaOrdenCompra/{fechaEntrega}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseApi> getSession(@PathVariable(value = "fechaEntrega") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaEntrega){
		logger.info("Ingresando al método: consultaOrdenCompra");
		return new ResponseEntity<>(ordenCompraService.consultaOrdenPagoFceha(fechaEntrega), HttpStatus.OK);
	}
}
