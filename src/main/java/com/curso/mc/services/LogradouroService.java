package com.curso.mc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Cidade;
import com.curso.mc.domain.Logradouro;
import com.curso.mc.domain.input.LogradouroInput;
import com.curso.mc.repositories.CidadeRepository;
import com.curso.mc.repositories.LogradouroRepository;
import com.curso.mc.services.exceptions.DatabaseException;
import com.curso.mc.services.exceptions.ObjectNotFoundException;

@Service
public class LogradouroService {
	
	@Autowired private CidadeRepository cidadeRepository;
	@Autowired private LogradouroRepository logradouroRepository;
	
	// Lista de logradouros
	public List<Logradouro> findAll(){
		return logradouroRepository.findAll();
	}
	
	// Logradouro por id
	public Logradouro findById(Integer id) {
		Optional<Logradouro> obj = logradouroRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto Logradouro não encontrada! Id: " + id + ", Tipo: " + Logradouro.class.getName()));
	}
	
	@Transactional 
	public Logradouro insert(LogradouroInput logradouroInput) {
		
		Logradouro logradouro = new Logradouro();
		BeanUtils.copyProperties(logradouroInput, logradouro, "id");
		
		// BUSCO CIDADE
		Cidade cidade = cidadeRepository.findById(logradouroInput.getCidadeId()).get();
		logradouro.setCidade(cidade);
		
		Logradouro logradouroSalva = logradouroRepository.save(logradouro);
		return logradouroSalva;
	}
	
	@Transactional
	public Logradouro update(Integer id, LogradouroInput logradouroInput) {
		Logradouro logradouroSalva = logradouroRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Logradouro não cadastrada id : " + id));
		
		Logradouro logradouro = new Logradouro();
		BeanUtils.copyProperties(logradouroInput, logradouro, "id");
		
		// BUSCO CIDADE
		Cidade cidade = cidadeRepository.findById(logradouroInput.getCidadeId()).get();
		logradouro.setCidade(cidade);
				
		BeanUtils.copyProperties(logradouro, logradouroSalva, "id");
		return logradouroRepository.save(logradouroSalva);
	}
	
	public void delete(Integer id) {
		try {
			logradouroRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e){
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
