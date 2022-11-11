package com.app.Questionados.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.Questionados.dto.request.CategoriaRequestDTO;
import com.app.Questionados.entity.Categoria;

public interface ICategoriaService {

	List<Categoria> getAll();
	
	Categoria getCategoriaById(Long idCategoria) throws Exception;
	
	Categoria createCategoria(CategoriaRequestDTO categoria) throws Exception;
	
	void deleteCategoria(Long idCategoria) throws Exception;
}
