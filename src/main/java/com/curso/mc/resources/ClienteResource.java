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
import com.curso.mc.domain.Cliente;
import com.curso.mc.domain.input.CepInput;
import com.curso.mc.domain.input.ClienteInput;
import com.curso.mc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	@Autowired ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> listCliente = clienteService.findAll();
		return ResponseEntity.ok().body(listCliente);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id){
		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	// INSERIR
	@PostMapping
	public ResponseEntity<Cliente> insert(@RequestBody ClienteInput clienteInput){
		System.err.println("aqui veio");
		Cliente clienteSalva = clienteService.insert(clienteInput);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clienteSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(clienteSalva);
	}
	
	// ALTERAR
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody ClienteInput clienteInput){
		Cliente clienteSalva = clienteService.update(id, clienteInput);
		return ResponseEntity.ok().body(clienteSalva);
	}
	
	// DELETAR
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Integer id){
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
