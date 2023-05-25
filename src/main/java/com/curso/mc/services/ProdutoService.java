package com.curso.mc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Estado;
import com.curso.mc.domain.Produto;
import com.curso.mc.repositories.ProdutoRepository;
import com.curso.mc.services.exceptions.DatabaseException;
import com.curso.mc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired private ProdutoRepository produtoRepository;
	
	// Lista de produtos
	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}
	
	// Produto por id
	public Produto findById(Integer id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Produto: " + Produto.class.getName()));
	}
	
	@Transactional
	public Produto insert(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@Transactional
	public Produto update(Integer id, Produto produto) {
		Produto produtoSalva = produtoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Produto não cadastrado id : " + id));
	
		BeanUtils.copyProperties(produto, produtoSalva, "id");
		return produtoRepository.save(produtoSalva);
	}
	
	@Transactional
	public void delete(Integer id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Produto não cadastrado id : " + id));
		
		try {
			produtoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e){
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
