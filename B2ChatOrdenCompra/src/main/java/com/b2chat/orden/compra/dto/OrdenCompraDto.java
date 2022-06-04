package com.b2chat.orden.compra.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * DTO con la estructura de una Orden de compra
 * 
 * @author Jonatan Velandia
 *
 */
public class OrdenCompraDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long idCliente;
	private Long numeroOrdenCompra;
	private String descripcion;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate fechaEntrega;
	
	//Setters And Getters
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Long getNumeroOrdenCompra() {
		return numeroOrdenCompra;
	}
	public void setNumeroOrdenCompra(Long numeroOrdenCompra) {
		this.numeroOrdenCompra = numeroOrdenCompra;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
}
