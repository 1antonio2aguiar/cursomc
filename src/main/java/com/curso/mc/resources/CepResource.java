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

import com.curso.mc.domain.Cep;
import com.curso.mc.domain.input.CepInput;
import com.curso.mc.services.CepService;


@RestController
@RequestMapping(value="/ceps")
public class CepResource {
	@Autowired CepService cepService;
	
	@GetMapping
	public ResponseEntity<List<Cep>> findAll(){
		List<Cep> listCep = cepService.findAll();
		return ResponseEntity.ok().body(listCep);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cep> findById(@PathVariable Integer id){
		Cep cep = cepService.findById(id);
		return ResponseEntity.ok().body(cep);
	}
	
	// INSERIR
	@PostMapping
	public ResponseEntity<Cep> insert(@RequestBody CepInput cepInput){
		Cep cepSalva = cepService.insert(cepInput);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cepSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(cepSalva);
	}
	
	// ALTERAR
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cep> update(@PathVariable Integer id, @RequestBody CepInput logradouroInput){
		Cep cepSalva = cepService.update(id, logradouroInput);
		return ResponseEntity.ok().body(cepSalva);
	}
	
	// DELETAR
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cep> delete(@PathVariable Integer id){
		cepService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
