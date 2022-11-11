package com.app.Questionados.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Questionados.dto.request.PreguntaRequestDTO;
import com.app.Questionados.dto.request.RespuestaAVerificarDTO;
import com.app.Questionados.dto.response.RespuestaVerificada;
import com.app.Questionados.dto.response.RestResponse;
import com.app.Questionados.entity.Pregunta;
import com.app.Questionados.service.IPreguntaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/questionados")
public class QuestionadosController {

	final static Logger logger = LogManager.getLogger(QuestionadosController.class);
	
	@Autowired
	private IPreguntaService preguntaService;

	@ApiOperation(value="Traer una pregunta aleatoria", notes="Retorna una pregunta aleatoria de las existentes hasta el momento")
	@GetMapping("/next")
	private ResponseEntity<RestResponse> getRandom() {
		
		Pregunta pregunta = null;
		logger.debug("GET: /api/questionados/next");
		
		try {
			pregunta = preguntaService.getPreguntaRandom();
		} catch(Exception e) {
			String msg = "Error al obtener pregunta aleatoria - " + e.getMessage();
			logger.error(msg + " - " + e.getStackTrace());
			
			return new ResponseEntity<>(new RestResponse(pregunta, true, msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new RestResponse(pregunta, false, "OK"), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Verifica la respuesta de una pregunta", notes = "Valida la respuesta seleccionada y retorna si es correcta o no")
	@PostMapping("/verificaciones")
	public ResponseEntity<RestResponse> createPregunta(@Valid @RequestBody RespuestaAVerificarDTO seleccion) {

		RespuestaVerificada verificacion = new RespuestaVerificada();
		boolean esCorrecta = false;
		logger.debug("POST: /api/questionados/verificaciones");
		
		try {
			esCorrecta = preguntaService.verificarRespuesta(seleccion);
			verificacion.setEsCorrecta(esCorrecta);
		} catch(Exception e) {
			String msg = "Error al verificar la respuesta - " + e.getMessage();
			logger.error(msg + " - " + e.getStackTrace());
			
			return new ResponseEntity<>(new RestResponse(null, true, msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new RestResponse(verificacion, false, "OK"), HttpStatus.CREATED);
	}
}