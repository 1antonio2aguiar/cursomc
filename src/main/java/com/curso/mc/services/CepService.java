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
import com.curso.mc.domain.Cep;
import com.curso.mc.domain.Logradouro;
import com.curso.mc.domain.input.CepInput;
import com.curso.mc.repositories.BairroRepository;
import com.curso.mc.repositories.CepRepository;
import com.curso.mc.repositories.LogradouroRepository;
import com.curso.mc.services.exceptions.DatabaseException;
import com.curso.mc.services.exceptions.ObjectNotFoundException;

@Service
public class CepService {
	
	@Autowired private CepRepository cepRepository;
	@Autowired private BairroRepository bairroRepository;
	@Autowired private LogradouroRepository logradouroRepository;
	
	// Lista de logradouros
	public List<Cep> findAll(){
		return cepRepository.findAll();
	}
	
	// Logradouro por id
	public Cep findById(Integer id) {
		Optional<Cep> obj = cepRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto cepo não encontrada! Id: " + id + ", Tipo: " + Cep.class.getName()));
	}
	
	@Transactional 
	public Cep insert(CepInput cepInput) {
		
		Cep cep = new Cep();
		BeanUtils.copyProperties(cepInput, cep, "id");
		
		// BUSCO BAIRRO
		Bairro bairro = bairroRepository.findById(cepInput.getBairroId()).get();
		cep.setBairro(bairro);
		
		// BUSCO LOGRADOURO
		Logradouro logradouro = logradouroRepository.findById(cepInput.getLogradouroId()).get();
		cep.setLogradouro(logradouro);
		
		Cep cepSalva = cepRepository.save(cep);
		return cepSalva;
	}
	
	@Transactional
	public Cep update(Integer id, CepInput cepInput) {
		Cep cepSalva = cepRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cep não cadastrado id : " + id));
		
		Cep cep = new Cep();
		BeanUtils.copyProperties(cepInput, cep, "id");
		
		// BUSCO BAIRRO
		Bairro bairro = bairroRepository.findById(cepInput.getBairroId()).get();
		cep.setBairro(bairro);
		
		// BUSCO LOGRADOURO
		Logradouro logradouro = logradouroRepository.findById(cepInput.getLogradouroId()).get();
		cep.setLogradouro(logradouro);
				
		BeanUtils.copyProperties(cep, cepSalva, "id");
		return cepRepository.save(cepSalva);
	}
	
	public void delete(Integer id) {
		try {
			cepRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e){
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
