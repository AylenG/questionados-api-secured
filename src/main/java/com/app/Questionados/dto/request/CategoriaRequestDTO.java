package com.app.Questionados.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoriaRequestDTO {

	@NotEmpty(message = "La descripción no debe ser vacía o nula")
	@Size(min = 10, message = "La descripción de la categoría debe tener al menos 10 caracteres")
	String descripcion;
	
	@NotEmpty(message = "El nombre no debe ser vacio o nulo")
	String nombre;

	
	public CategoriaRequestDTO() {
		super();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
