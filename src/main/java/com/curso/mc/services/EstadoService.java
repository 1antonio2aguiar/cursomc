package com.curso.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Estado;
import com.curso.mc.repositories.EstadoRepository;
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
	
	
}
