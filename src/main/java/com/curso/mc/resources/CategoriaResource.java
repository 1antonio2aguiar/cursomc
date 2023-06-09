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

import com.curso.mc.domain.Categoria;
import com.curso.mc.domain.Cliente;
import com.curso.mc.domain.input.CategoriaInput;
import com.curso.mc.domain.input.ClienteInput;
import com.curso.mc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	@Autowired CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll(){
		List<Categoria> listCategoria = categoriaService.findAll();
		return ResponseEntity.ok().body(listCategoria);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id){
		Categoria categoria = categoriaService.findById(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	// INSERIR
	@PostMapping
	public ResponseEntity<Categoria> insert(@RequestBody CategoriaInput categoriaInput){
		Categoria categoriaSalva = categoriaService.insert(categoriaInput);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoriaSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	// ALTERAR
	@PutMapping(value = "/{id}")
	public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody CategoriaInput categoriaInput){
		Categoria categoriaSalva = categoriaService.update(id, categoriaInput);
		return ResponseEntity.ok().body(categoriaSalva);
	}
	
	// DELETAR
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Categoria> delete(@PathVariable Integer id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/*@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		return lista;
	}*/
}
