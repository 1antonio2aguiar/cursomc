package com.curso.mc.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Categoria;
import com.curso.mc.domain.Produto;
import com.curso.mc.domain.input.CategoriaInput;
import com.curso.mc.domain.input.Endereco;
import com.curso.mc.domain.input.ProdutoInput;
import com.curso.mc.repositories.CategoriaRepository;
import com.curso.mc.repositories.ProdutoRepository;
import com.curso.mc.services.exceptions.DatabaseException;
import com.curso.mc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired private CategoriaRepository categoriaRepository;
	//@Autowired private ProdutoCategoriaRepository produtoCategoriaRepository;
	@Autowired private ProdutoRepository produtoRepository;
	
	// Lista de categorias
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	
	// Categorias por id
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	@Transactional
	public Categoria insert(CategoriaInput categoriaInput) {
		Categoria categoria = new Categoria();
		BeanUtils.copyProperties(categoriaInput, categoria, "id");
		
		// GRAVA PRODUTO CATEGORIA
		for(ProdutoInput prod : categoriaInput.getProdutos()) {
			// BUSCO PRODUTO
			Produto produto = produtoRepository.findById(prod.getId()).get();
			
			//popula lista de produtos em categoria
			categoria.getProdutos().addAll(Arrays.asList(produto));
			//popula lista de categorias em produto
			produto.getCategorias().addAll(Arrays.asList(categoria));
		}
		
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		return categoriaSalva; 
	}
	
	@Transactional
	public Categoria update(Integer id, CategoriaInput categoriaInput) {
		Categoria categoriaSalva = categoriaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não cadastrada id : " + id));
	
		BeanUtils.copyProperties(categoriaInput, categoriaSalva, "id");
		
		return categoriaRepository.save(categoriaSalva);
	}
	
	@Transactional
	public void delete(Integer id) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não cadastrada id : " + id));
		
		try {
			categoriaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e){
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
