package com.app.Questionados.service.impl;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.Questionados.dto.request.PreguntaRequestDTO;
import com.app.Questionados.dto.request.RespuestaAVerificarDTO;
import com.app.Questionados.entity.Categoria;
import com.app.Questionados.entity.Pregunta;
import com.app.Questionados.entity.Respuesta;
import com.app.Questionados.repository.ICategoriaRepository;
import com.app.Questionados.repository.IPreguntaRepository;
import com.app.Questionados.repository.IRespuestaRepository;
import com.app.Questionados.service.IPreguntaService;

@Service
public class PreguntaServiceImpl implements IPreguntaService {

	@Autowired
	private IPreguntaRepository preguntaRepository;
	
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	@Autowired
	private IRespuestaRepository respuestaRepository;

	@Override
	public List<Pregunta> getAll() {
		return preguntaRepository.findAll();
	}

	@Override
	public Pregunta getPreguntaById(Long idPregunta) throws Exception {
		Optional<Pregunta> pregunta = preguntaRepository.findById(idPregunta);
		
		if(pregunta.isEmpty()) {
			throw new Exception("La pregunta con id '" + idPregunta + "' no existe.");
		}
		
		return pregunta.get();
	}

	@Override
	public Pregunta createPregunta(PreguntaRequestDTO pregunta) throws Exception {
		Pregunta preg = new Pregunta();
		Optional<Categoria> categoria = categoriaRepository.findById(pregunta.getCategoriaId());
		
		if(categoria.isEmpty()) {
			throw new Exception("La categoría con id '" + pregunta.getCategoriaId() + "' no existe.");
		}
		
		preg.setCategoria(categoria.get());
		preg.setEnunciado(pregunta.getEnunciado());
		preg.setRespuestas(pregunta.getOpciones());

		preg.getRespuestas().forEach((opcion) -> {
		    opcion.setPregunta(preg);
		});
		
		return preguntaRepository.save(preg);
	}

	@Override
	public Pregunta getPreguntaRandom() throws Exception {
		long cantidadRegistros = preguntaRepository.count();
		int rownum = (int)(Math.random() * cantidadRegistros);
		Page<Pregunta> preguntaRandom = preguntaRepository.findAll(PageRequest.of(rownum, 1));
		
		return preguntaRandom.hasContent() ? preguntaRandom.getContent().get(0) : null;
	}

	@Override
	public boolean verificarRespuesta(RespuestaAVerificarDTO seleccion) throws Exception {
		
		Optional<Pregunta> pregunta = preguntaRepository.findById(seleccion.getPreguntaId());
		if(pregunta.isEmpty()) {
			throw new Exception("La pregunta con id '" + seleccion.getPreguntaId() + "' no existe.");
		}
		
		Optional<Respuesta> respuesta = respuestaRepository.findById(seleccion.getRespuestaId());
		if(respuesta.isEmpty()) {
			throw new Exception("La respuesta con id '" + seleccion.getRespuestaId() + "' no existe.");
		}
		
		if(respuesta.get().getPregunta().getId() != pregunta.get().getId()) {
			throw new Exception("La respuesta seleccionada no pertenece a esa pregunta.");
		}
		
		if(respuesta.get().isEsCorrecta()) {
			return true;
		}
		
		return false;
	}
	
}
