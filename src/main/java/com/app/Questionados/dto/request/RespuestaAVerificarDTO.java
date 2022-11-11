package com.app.Questionados.dto.request;

import javax.validation.constraints.NotNull;

public class RespuestaAVerificarDTO {

	@NotNull(message = "El id de la pregunta no debe ser vacio o nulo")
	private Long preguntaId;
	
	@NotNull(message = "El id de la respuesta no debe ser vacio o nulo")
	private Long respuestaId;

	public RespuestaAVerificarDTO() {
		super();
	}

	public Long getPreguntaId() {
		return preguntaId;
	}

	public void setPreguntaId(Long preguntaId) {
		this.preguntaId = preguntaId;
	}

	public Long getRespuestaId() {
		return respuestaId;
	}

	public void setRespuestaId(Long respuestaId) {
		this.respuestaId = respuestaId;
	}
	
}
