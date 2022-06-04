package com.b2chat.orden.compra.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import com.b2chat.orden.compra.dto.OrdenCompraDto;
import com.b2chat.orden.compra.dto.ResponseApi;

/**
 * Clase encargada de implementar la logica de negocio para gestionar una orden de compra
 * 
 * @author Jonatan Velandia
 *
 */
@Service
@Scope(value="singleton", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class OrdenCompraApiServiceImp implements OrdenCompraApiService{
	
	private static final Logger logger = LoggerFactory.getLogger(OrdenCompraApiServiceImp.class);
	
	/**
	 * Variable para response de servicio 
	 */
	private ResponseApi response = new ResponseApi();
	
	/**
	 * Variable que contiene las ordenes de pago
	 */
	private Map<Long, List<OrdenCompraDto>> ordenCompraMap = new HashMap<>();
	
	
	/**
	 * Metodo que contiene la logica de negocio para generar una orden de pago
	 */
	@Override
	public ResponseApi generarOrdenCompra(OrdenCompraDto ordenCompra) {
		logger.info("Generando orden de compra");
		List<OrdenCompraDto> listOrdenCompra = new ArrayList<>();
		try {
			if(ordenCompraMap.containsKey(ordenCompra.getIdCliente()))
				listOrdenCompra = ordenCompraMap.get(ordenCompra.getIdCliente());
			if(null==listOrdenCompra.stream().filter(ord-> ordenCompra.getNumeroOrdenCompra().equals(ord.getNumeroOrdenCompra())).findAny().orElse(null))
				listOrdenCompra.add(ordenCompra);		
			ordenCompraMap.put(ordenCompra.getIdCliente(), listOrdenCompra);
			//Objeto con respuesta
			response.setBody(ordenCompraMap);
			response.setStatus(true);
			response.setCode(200);
			response.setMessage("Se agrego la orden de compra");
		}catch(Exception e) {
			response.setCode(401);
			response.setMessage("Error al generar órden de compra: "+e.getMessage());
			logger.error("Error en la generaci�n de la orden de compra: ", e.getMessage());
		}
		
		return response;
	}
	
	/**
	 * Metodo que contiene la logica de negocio para listar las ordenes de pago por fecha de entrega
	 */
	@Override
	public ResponseApi consultaOrdenPagoFceha(LocalDate fechaEntrega) {
		logger.info("Consultando orden de compra");
		List<OrdenCompraDto> listOrdenCompra = new ArrayList<>();
		try {
			//Bucle encargado de recorrer un Map que contiene las diferentes ordenes de pago
			for (Map.Entry<Long, List<OrdenCompraDto>> entry : ordenCompraMap.entrySet()) {
				//Filtro para obtener las Ordenes de compra por una 
				List<OrdenCompraDto> list = entry.getValue().stream().filter(ord-> fechaEntrega.equals(ord.getFechaEntrega())).collect(Collectors.toList());
		        list.forEach(listOrdenCompra::add);               
		    }
			//Objeto con respuesta
			response.setBody(listOrdenCompra);
			response.setMessage("Se consulta la orden de compra");
			response.setCode(200);
		}catch(Exception e) {
			response.setMessage("Error al consultar la órden de pago: "+e.getMessage());
			response.setCode(400);
			logger.error("Error en la consulta de la orden de compra: ", e.getMessage());
		}
		return response;
	}
}
