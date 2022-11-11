package com.app.Questionados.dto.request;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.app.Questionados.entity.Respuesta;

public class PreguntaRequestDTO {

	@NotNull(message = "El id de la categoría no debe ser nulo")
	private Long categoriaId;
	
	@NotEmpty(message = "El enunciado de la pregunta no debe ser vacío o nulo")
	private String enunciado;
	
	@NotEmpty(message = "Debe tener almenos una opción")
	private Set<Respuesta> opciones;

	public PreguntaRequestDTO() {
		super();
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Set<Respuesta> getOpciones() {
		return opciones;
	}

	public void setOpciones(Set<Respuesta> opciones) {
		this.opciones = opciones;
	}
	
	
}
