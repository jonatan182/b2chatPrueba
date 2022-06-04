package com.b2chat.orden.compra.dto;

/**
 * DTO de respuesta para la API Orden de Compra
 * @author Jonatan Velandia
 *
 */
public class ResponseApi {
	
	/**
	 * Bandera que indica si se presento algun error
	 */
	private boolean status;
	/**
	 * Codigo de error que se presento
	 */
	private int code;
	/**
	 * Mensaje de error
	 */
	private String message;
	/**
	 * Body de la respuesta
	 */
	private Object body;

	public ResponseApi() {}

	public ResponseApi(boolean status, int code, Object body) {		
		this.status = status;
		this.code = code;
		this.body = body;
	}

	public ResponseApi(boolean status, int code, String message) {		
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
