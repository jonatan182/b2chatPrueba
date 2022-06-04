package com.b2chat.orden.compra.service;

import java.time.LocalDate;

import com.b2chat.orden.compra.dto.OrdenCompraDto;
import com.b2chat.orden.compra.dto.ResponseApi;

/**
 * Interface de negocio con los metodos a implementar para las operaciones soportadas en las ordenes de compra
 * 
 * @author Jonatan Velandia
 *
 */
public interface OrdenCompraApiService { 
	
	/**
	 * Metodo encargado de generar una orden de compra
	 */
	ResponseApi generarOrdenCompra(OrdenCompraDto ordenCompra);
	
	/**
	 * Metodo encargado de consultar las ordenes que deben entregarse en una fecha específica
	 */
	ResponseApi consultaOrdenPagoFceha(LocalDate fecha);
	
}
