package com.curso.mc.resources;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.mc.domain.Estado;
import com.curso.mc.repositories.CidadeRepository;
import com.curso.mc.repositories.EstadoRepository;
import com.curso.mc.services.EstadoService;
import com.curso.mc.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	@Autowired EstadoService estadoService;
	
	@Autowired private EstadoRepository estadoRepository;
	@Autowired private CidadeRepository cidadeRepository;
	
	@GetMapping
	public ResponseEntity<List<Estado>> findAll(){
		List<Estado> listEstado = estadoService.findAll();
		return ResponseEntity.ok().body(listEstado);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Estado> findById(@PathVariable Integer id){
		Estado estado = estadoService.findById(id);
		return ResponseEntity.ok().body(estado);
	}
	
	// INSERIR
	@PostMapping
	public ResponseEntity<Estado> insert(@RequestBody Estado estado){
		estado = estadoService.insert(estado);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).body(estado);
	}
	
	// ALTERAR
	@PutMapping(value = "/{id}")
	public ResponseEntity<Estado> update(@PathVariable Integer id, @RequestBody Estado estado){
		estado = estadoService.update(id, estado);
		return ResponseEntity.ok().body(estado);
	}
	
	// DELETAR
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Estado> delete(@PathVariable Integer id){
		estadoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
