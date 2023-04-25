package com.curso.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Cliente;
import com.curso.mc.repositories.ClienteRepository;
import com.curso.mc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired private ClienteRepository clienteRepository;
	
	// Lista de clientes
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	// Clientes por id
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	
}
