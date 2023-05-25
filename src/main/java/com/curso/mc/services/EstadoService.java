package com.curso.mc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.curso.mc.domain.Estado;
import com.curso.mc.repositories.EstadoRepository;
import com.curso.mc.services.exceptions.DatabaseException;
import com.curso.mc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {
	
	@Autowired private EstadoRepository estadoRepository;
	
	// Lista de estados
	public List<Estado> findAll(){
		return estadoRepository.findAll();
	}
	
	// Estado por id
	public Estado findById(Integer id) {
		Optional<Estado> obj = estadoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto estado não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}
	
	@Transactional
	public Estado insert(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	@Transactional
	public Estado update(Integer id, Estado estado) {
		Estado estadoSalva = estadoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Estado não cadastrado id : " + id));
	
		BeanUtils.copyProperties(estado, estadoSalva, "id");
		return estadoRepository.save(estadoSalva);
	}
	
	@Transactional
	public void delete(Integer id) {
		try {
			estadoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e){
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
