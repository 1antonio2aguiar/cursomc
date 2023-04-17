package com.curso.mc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.mc.domain.Estado;
import com.curso.mc.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	@Autowired EstadoService estadoService;
	
	@GetMapping
	public ResponseEntity<List<Estado>> findAll(){
		List<Estado> listEstado = estadoService.findAll();
		return ResponseEntity.ok().body(listEstado);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Estado> findById(@PathVariable Integer id){
		Estado categoria = estadoService.findById(id);
		return ResponseEntity.ok().body(categoria);
	}

}
