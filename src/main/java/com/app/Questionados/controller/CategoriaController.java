package com.app.Questionados.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Questionados.dto.request.CategoriaRequestDTO;
import com.app.Questionados.dto.response.RestResponse;
import com.app.Questionados.entity.Categoria;
import com.app.Questionados.service.ICategoriaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	final static Logger logger = LogManager.getLogger(CategoriaController.class);
	
	@Autowired
	private ICategoriaService categoriaService;
	
	
	@ApiOperation(value = "Obtener categoria por id", notes = "Retorna la categorias correspondiente al id que se envíe")
	@GetMapping("/{categoriaId}")
	public ResponseEntity<RestResponse> getCategoriaById(@PathVariable(value = "categoriaId") Long categoriaId) {
		
		Categoria categoria = null;
		logger.debug("GET: /api/categorias/" + categoriaId);
		
		try {
			categoria = categoriaService.getCategoriaById(categoriaId);
		} catch(Exception e) {
			String msg = "Error al obtener la categoria - " + e.getMessage();
			logger.error(msg + " - " + e.getStackTrace());
			return new ResponseEntity<>(new RestResponse(categoria, true, msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new RestResponse(categoria, false, "OK"), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Crear una categoria", notes = "Crea una categoría y la retorna, no deben repetirse")
	@PostMapping
	public ResponseEntity<RestResponse> createCategoria(@Valid @RequestBody CategoriaRequestDTO categoriaDTO) {

		Categoria categoria = null;
		logger.debug("POST: /api/categorias");
		
		try {
			categoria = categoriaService.createCategoria(categoriaDTO);
		} catch(Exception e) {
			String msg = "Error al crear la categoria - " + e.getMessage();
			logger.error(msg + " - " + e.getStackTrace());
			
			return new ResponseEntity<>(new RestResponse(categoria, true, msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new RestResponse(categoria, false, "OK"), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Obtener todas las categorias", notes = "Retorna todas las categorias existentes al momento")
	@GetMapping
	public ResponseEntity<RestResponse> getAllCategorias() {

		List<Categoria> categorias = new ArrayList<>();
		logger.debug("GET: /api/categorias");
		
		try {
			categorias = categoriaService.getAll();
		} catch(Exception e) {
			String msg = "Error al obtener todas las categorias - " + e.getMessage();
			logger.error(msg + " - " + e.getStackTrace());
			return new ResponseEntity<>(new RestResponse(categorias, true, msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new RestResponse(categorias, false, "OK"), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Eliminar categoria", notes = "Elimina la categorias correspondiente al id que se envíe")
	@DeleteMapping("/{categoriaId}")
	public ResponseEntity<RestResponse> eliminarCategoriaById(@PathVariable(value = "categoriaId") Long categoriaId) {
		
		logger.debug("GET: /api/categorias/" + categoriaId);
		
		try {
			categoriaService.deleteCategoria(categoriaId);
		} catch(Exception e) {
			String msg = "Error al eliminar la categoria - " + e.getMessage();
			logger.error(msg + " - " + e.getStackTrace());
			return new ResponseEntity<>(new RestResponse(null, true, msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new RestResponse(null, false, "OK"), HttpStatus.OK);
	}
}
