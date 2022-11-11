package com.app.Questionados.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Questionados.dto.request.CategoriaRequestDTO;
import com.app.Questionados.entity.Categoria;
import com.app.Questionados.repository.ICategoriaRepository;
import com.app.Questionados.service.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
	
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	@Override
	public List<Categoria> getAll() {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria getCategoriaById(Long idCategoria) throws Exception {
		Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
		
		if(categoria.isEmpty()) {
			throw new Exception("La categoría con id '" + idCategoria + "' no existe.");
		}
		
		return categoria.get();
	}

	@Override
	public Categoria createCategoria(CategoriaRequestDTO categoria) throws Exception {
		
		Categoria cat = new Categoria();
		
		if(categoriaRepository.findByNombre(categoria.getNombre()).isPresent()) {
			throw new Exception("La categoría '" + categoria.getNombre() + "' ya existe.");
		}
		
		cat.setNombre(categoria.getNombre());
		cat.setDescripcion(categoria.getDescripcion());

		return categoriaRepository.save(cat);
	}

	@Override
	public void deleteCategoria(Long idCategoria) throws Exception {
		
		Optional<Categoria> cat = categoriaRepository.findById(idCategoria);
		
		if(cat.isEmpty()) {
			throw new Exception("La categoría con id '" + idCategoria + "' no existe.");
		}
		
		categoriaRepository.delete(cat.get());
	}

}
