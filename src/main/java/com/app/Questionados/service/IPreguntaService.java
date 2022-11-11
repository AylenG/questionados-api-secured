package com.app.Questionados.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.Questionados.dto.request.PreguntaRequestDTO;
import com.app.Questionados.dto.request.RespuestaAVerificarDTO;
import com.app.Questionados.entity.Pregunta;

public interface IPreguntaService {

	List<Pregunta> getAll();
	
	Pregunta getPreguntaById(Long idPregunta) throws Exception;
	
	Pregunta createPregunta(PreguntaRequestDTO pregunta) throws Exception;
	
	Pregunta getPreguntaRandom() throws Exception;
	
	boolean verificarRespuesta(RespuestaAVerificarDTO seleccion) throws Exception;
}
