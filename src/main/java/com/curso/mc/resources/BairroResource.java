package com.curso.mc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.curso.mc.domain.Bairro;
import com.curso.mc.domain.input.BairroInput;
import com.curso.mc.domain.output.BairroOutput;
import com.curso.mc.services.BairroService;
import com.curso.mc.repositories.BairroRepository;


@RestController
@RequestMapping(value="/bairros")
public class BairroResource {
	@Autowired BairroService bairroService;
	@Autowired private BairroRepository bairroRepository;
	
//	@GetMapping
//	public ResponseEntity<List<Bairro>> findAll(){
//		List<Bairro> listBairro = bairroService.findAll();
//		return ResponseEntity.ok().body(listBairro);
//	}
	
	@GetMapping
	public ResponseEntity<List<BairroOutput>> findAll(){
		List<Bairro> listBairro = bairroRepository.findAll();
		List<BairroOutput> bairroOut = listBairro.stream().map(obj -> new BairroOutput(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(bairroOut);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Bairro> findById(@PathVariable Integer id){
		Bairro bairro = bairroService.findById(id);
		return ResponseEntity.ok().body(bairro);
	}
	
	// INSERIR
	@PostMapping
	public ResponseEntity<Bairro> insert(@RequestBody BairroInput bairroInput){
		Bairro bairroSalva = bairroService.insert(bairroInput);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(bairroSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(bairroSalva);
	}
	
	// ALTERAR
	@PutMapping(value = "/{id}")
	public ResponseEntity<Bairro> update(@PathVariable Integer id, @RequestBody BairroInput bairroInput){
		Bairro bairroSalva = bairroService.update(id, bairroInput);
		return ResponseEntity.ok().body(bairroSalva);
	}
	
	// DELETAR
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Bairro> delete(@PathVariable Integer id){
		bairroService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
