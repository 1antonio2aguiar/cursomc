package com.curso.mc.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.mc.domain.Cliente;
import com.curso.mc.domain.Pedido;
import com.curso.mc.domain.input.ClienteInput;
import com.curso.mc.domain.input.PedidoInput;
import com.curso.mc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	@Autowired PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> findAll(){
		List<Pedido> listPedido = pedidoService.findAll();
		return ResponseEntity.ok().body(listPedido);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Integer id){
		Pedido categoria = pedidoService.findById(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	// INSERIR
	@PostMapping
	public ResponseEntity<Pedido> insert(@RequestBody PedidoInput pedidoInput){
		System.err.println("aqui veio");
		Pedido pedidoSalva = pedidoService.insert(pedidoInput);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pedidoSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(pedidoSalva);
	}
	
	
	/*@RequestMapping(method = RequestMethod.GET)
	public List<Pedido> listar() {
		Pedido cat1 = new Pedido(1, "Informática");
		Pedido cat2 = new Pedido(2, "Escritório");
		List<Pedido> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		return lista;
	}*/
}
