package com.app.Questionados.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Questionados.dto.request.PreguntaRequestDTO;
import com.app.Questionados.dto.response.RestResponse;
import com.app.Questionados.entity.Pregunta;
import com.app.Questionados.service.IPreguntaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/preguntas")
public class PreguntaController {

	final static Logger logger = LogManager.getLogger(PreguntaController.class);
	
	@Autowired
	private IPreguntaService preguntaService;
	
	@ApiOperation(value = "Obtener pregunta por id", notes = "Retorna la pregunta correspondiente al id que se envíe")
	@GetMapping("/{preguntaId}")
	public ResponseEntity<RestResponse> getPreguntaById(@PathVariable(value = "preguntaId") Long preguntaId) {
		
		Pregunta pregunta = null;
		logger.debug("GET: /api/preguntas/" + preguntaId);
		
		try {
			pregunta = preguntaService.getPreguntaById(preguntaId);
		} catch(Exception e) {
			String msg = "Error al obtener la pregunta - " + e.getMessage();
			logger.error(msg + " - " + e.getStackTrace());
			return new ResponseEntity<>(new RestResponse(pregunta, true, msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new RestResponse(pregunta, false, "OK"), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Obtener todas las preguntas", notes = "Retorna todas las preguntas existentes al momento")
	@GetMapping
	public ResponseEntity<RestResponse> getAllPreguntas() {
		
		List<Pregunta> pregunta = new ArrayList<>();
		logger.debug("GET: /api/preguntas");
		
		try {
			pregunta = preguntaService.getAll();
		} catch(Exception e) {
			String msg = "Error al obtener todas las preguntas - " + e.getMessage();
			logger.error(msg + " - " + e.getStackTrace());
			return new ResponseEntity<>(new RestResponse(pregunta, true, msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new RestResponse(pregunta, false, "OK"), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Crear una pregunta", notes = "Crea una pregunta y la retorna")
	@PostMapping
	public ResponseEntity<RestResponse> createPregunta(@Valid @RequestBody PreguntaRequestDTO preguntaDTO) {

		Pregunta pregunta = null;
		logger.debug("POST: /api/preguntas");
		
		try {
			pregunta = preguntaService.createPregunta(preguntaDTO);
		} catch(Exception e) {
			String msg = "Error al crear la pregunta - " + e.getMessage();
			logger.error(msg + " - " + e.getStackTrace());
			
			return new ResponseEntity<>(new RestResponse(pregunta, true, msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new RestResponse(pregunta, false, "OK"), HttpStatus.CREATED);
	}
}
