package com.app.Questionados.dto.response;

public class RestResponse {

	private Object respuesta;
	
	private boolean error;
	
	private String mensaje;
	
	public RestResponse() {
		super();
	}

	public RestResponse(Object respuesta, boolean error, String mensaje) {
		super();
		this.respuesta = respuesta;
		this.error = error;
		this.mensaje = mensaje;
	}

	public Object getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Object respuesta) {
		this.respuesta = respuesta;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
