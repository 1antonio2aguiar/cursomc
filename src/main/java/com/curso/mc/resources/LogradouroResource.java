package com.curso.mc.resources;

import java.net.URI;
import java.util.List;

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

import com.curso.mc.domain.Logradouro;
import com.curso.mc.domain.input.LogradouroInput;
import com.curso.mc.services.LogradouroService;


@RestController
@RequestMapping(value="/logradouros")
public class LogradouroResource {
	@Autowired LogradouroService logradouroService;
	
	@GetMapping
	public ResponseEntity<List<Logradouro>> findAll(){
		List<Logradouro> listLogradouro = logradouroService.findAll();
		return ResponseEntity.ok().body(listLogradouro);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Logradouro> findById(@PathVariable Integer id){
		Logradouro logradouro = logradouroService.findById(id);
		return ResponseEntity.ok().body(logradouro);
	}
	
	// INSERIR
	@PostMapping
	public ResponseEntity<Logradouro> insert(@RequestBody LogradouroInput logradouroInput){
		Logradouro logradouroSalva = logradouroService.insert(logradouroInput);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(logradouroSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(logradouroSalva);
	}
	
	// ALTERAR
	@PutMapping(value = "/{id}")
	public ResponseEntity<Logradouro> update(@PathVariable Integer id, @RequestBody LogradouroInput logradouroInput){
		Logradouro logradouroSalva = logradouroService.update(id, logradouroInput);
		return ResponseEntity.ok().body(logradouroSalva);
	}
	
	// DELETAR
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Logradouro> delete(@PathVariable Integer id){
		logradouroService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
