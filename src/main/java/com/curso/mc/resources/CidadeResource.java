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

import com.curso.mc.domain.Cidade;
import com.curso.mc.domain.input.CidadeInput;
import com.curso.mc.services.CidadeService;

@RestController
@RequestMapping(value="/cidades")
public class CidadeResource {
	@Autowired CidadeService cidadeService;
	
	@GetMapping
	public ResponseEntity<List<Cidade>> findAll(){
		List<Cidade> listCidade = cidadeService.findAll();
		return ResponseEntity.ok().body(listCidade);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cidade> findById(@PathVariable Integer id){
		Cidade cidade = cidadeService.findById(id);
		return ResponseEntity.ok().body(cidade);
	}
	
	// INSERIR
	@PostMapping
	public ResponseEntity<Cidade> insert(@RequestBody CidadeInput cidadeInput){
		Cidade cidadeSalva = cidadeService.insert(cidadeInput);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cidadeSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(cidadeSalva);
	}
	
	// ALTERAR
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cidade> update(@PathVariable Integer id, @RequestBody CidadeInput cidadeInput){
		Cidade cidadeSalva = cidadeService.update(id, cidadeInput);
		return ResponseEntity.ok().body(cidadeSalva);
	}
	
	// DELETAR
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cidade> delete(@PathVariable Integer id){
		cidadeService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
