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
import com.curso.mc.domain.Estado;
import com.curso.mc.domain.input.CidadeInput;
import com.curso.mc.repositories.CidadeRepository;
import com.curso.mc.repositories.EstadoRepository;
import com.curso.mc.services.exceptions.DatabaseException;
import com.curso.mc.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {
	
	@Autowired private CidadeRepository cidadeRepository;
	@Autowired private EstadoRepository estadoRepository;
	
	// Lista de cidades
	public List<Cidade> findAll(){
		return cidadeRepository.findAll();
	}
	
	// Cidade por id
	public Cidade findById(Integer id) {
		Optional<Cidade> obj = cidadeRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto cidade não encontrada! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}
	
	@Transactional 
	public Cidade insert(CidadeInput cidadeInput) {
		
		Cidade cidade = new Cidade();
		BeanUtils.copyProperties(cidadeInput, cidade, "id");
		
		// BUSCO ESTADO
		Estado estado = estadoRepository.findById(cidadeInput.getEstadoId()).get();
		cidade.setEstado(estado);
		
		Cidade cidadeSalva = cidadeRepository.save(cidade);
		return cidadeSalva;
	}
	
	@Transactional
	public Cidade update(Integer id, CidadeInput cidadeInput) {
		Cidade cidadeSalva = cidadeRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cidade não cadastrada id : " + id));
		
		Cidade cidade = new Cidade();
		BeanUtils.copyProperties(cidadeInput, cidade, "id");
		
		// BUSCO ESTADO
		Estado estado = estadoRepository.findById(cidadeInput.getEstadoId()).get();
		cidade.setEstado(estado);
				
		BeanUtils.copyProperties(cidade, cidadeSalva, "id");
		return cidadeRepository.save(cidadeSalva);
	}
	
	public void delete(Integer id) {
		try {
			cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e){
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
