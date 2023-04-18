package com.curso.mc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Bairro;
import com.curso.mc.domain.Cidade;
import com.curso.mc.domain.input.BairroInput;
import com.curso.mc.repositories.CidadeRepository;
import com.curso.mc.repositories.BairroRepository;
import com.curso.mc.services.exceptions.DatabaseException;
import com.curso.mc.services.exceptions.ObjectNotFoundException;

@Service
public class BairroService {
	
	@Autowired private CidadeRepository cidadeRepository;
	@Autowired private BairroRepository bairroRepository;
	
	// Lista de bairros
	public List<Bairro> findAll(){
		return bairroRepository.findAll();
	}
	
	// Bairro por id
	public Bairro findById(Integer id) {
		Optional<Bairro> obj = bairroRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto bairro não encontrada! Id: " + id + ", Tipo: " + Bairro.class.getName()));
	}
	
	@Transactional 
	public Bairro insert(BairroInput bairroInput) {
		
		Bairro bairro = new Bairro();
		BeanUtils.copyProperties(bairroInput, bairro, "id");
		
		// BUSCO CIDADE
		Cidade cidade = cidadeRepository.findById(bairroInput.getCidadeId()).get();
		bairro.setCidade(cidade);
		
		Bairro bairroSalva = bairroRepository.save(bairro);
		return bairroSalva;
	}
	
	@Transactional
	public Bairro update(Integer id, BairroInput bairroInput) {
		Bairro bairroSalva = bairroRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Bairro não cadastrada id : " + id));
		
		Bairro bairro = new Bairro();
		BeanUtils.copyProperties(bairroInput, bairro, "id");
		
		// BUSCO CIDADE
		Cidade cidade = cidadeRepository.findById(bairroInput.getCidadeId()).get();
		bairro.setCidade(cidade);
				
		BeanUtils.copyProperties(bairro, bairroSalva, "id");
		return bairroRepository.save(bairroSalva);
	}
	
	public void delete(Integer id) {
		try {
			bairroRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e){
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
