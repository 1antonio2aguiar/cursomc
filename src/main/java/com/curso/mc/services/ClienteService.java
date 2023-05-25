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

import com.curso.mc.domain.Cep;
import com.curso.mc.domain.Cliente;
import com.curso.mc.domain.enums.TipoCliente;
import com.curso.mc.domain.input.ClienteInput;
import com.curso.mc.domain.input.Endereco;
import com.curso.mc.domain.input.EnderecoInput;
import com.curso.mc.repositories.CepRepository;
import com.curso.mc.repositories.ClienteRepository;
import com.curso.mc.repositories.EnderecoRepository;
import com.curso.mc.services.exceptions.DatabaseException;
import com.curso.mc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired private ClienteRepository clienteRepository;
	@Autowired private CepRepository cepRepository;
	@Autowired private EnderecoRepository enderecoRepository;
	
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
	
	@Transactional 
	public Cliente insert(ClienteInput clienteInput) {
		Cliente cliente = new Cliente();
		BeanUtils.copyProperties(clienteInput, cliente, "id");
		
		// GRAVA TELEFONES
		for(String telefone : clienteInput.getTelefones()) {
			cliente.getTelefones().addAll(Arrays.asList(telefone));
		}
		
		if(clienteInput.getTipoCliente() == 1) {
			cliente.setTipoCliente(TipoCliente.PESSOAFISICA);
		} else {
			cliente.setTipoCliente(TipoCliente.PESSOAJURIDICA);
		}
		
		Cliente clienteSalva = clienteRepository.save(cliente);
		
		// GRAVA ENDERECOS
		for(EnderecoInput ender : clienteInput.getEnderecos()) {
			Endereco endereco = new Endereco();
			
			// BUSCO CEP
			Cep cep = cepRepository.findById(ender.getCep()).get();
			endereco.setCep(cep);
			
			// BUSCO CLIENTE
			Cliente clienteEnder = clienteRepository.findById(clienteSalva.getId()).get();
			endereco.setCliente(clienteEnder);
			
			endereco.setNumero(ender.getNumero());
			endereco.setComplemento(ender.getComplemento());
			
			Endereco enderSalva = enderecoRepository.save(endereco);
			
			//Só pra retornar o endereco no cliente que foi criado.
			clienteSalva.getEnderecos().addAll(Arrays.asList(enderSalva));
		}
		return clienteSalva;
	}
	
	@Transactional
	public Cliente update(Integer id, ClienteInput clienteInput) {
		Cliente clienteSalva = clienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não cadastrado id : " + id));
		
		//Cliente cliente = new Cliente();
		BeanUtils.copyProperties(clienteInput, clienteSalva, "id");
		
		if(clienteInput.getTipoCliente() == 1) {
			clienteSalva.setTipoCliente(TipoCliente.PESSOAFISICA);
		} else {
			clienteSalva.setTipoCliente(TipoCliente.PESSOAJURIDICA);
		}
		
		enderecoRepository.deleteByClienteId(id);
		
		// GRAVA TELEFONES
		for(String telefone : clienteInput.getTelefones()) {
			clienteSalva.getTelefones().addAll(Arrays.asList(telefone));
		}
		
		// GRAVA ENDERECOS
		for(EnderecoInput ender : clienteInput.getEnderecos()) {
			Endereco endereco = new Endereco();
			
			// BUSCO CEP
			Cep cep = cepRepository.findById(ender.getCep()).get();
			endereco.setCep(cep);
			
			// BUSCO CLIENTE
			Cliente clienteEnder = clienteRepository.findById(clienteSalva.getId()).get();
			endereco.setCliente(clienteEnder);
			
			endereco.setNumero(ender.getNumero());
			endereco.setComplemento(ender.getComplemento());
			
			Endereco enderSalva = enderecoRepository.save(endereco);
			
			//Só pra retornar o endereco no cliente que foi criado.
			clienteSalva.getEnderecos().addAll(Arrays.asList(enderSalva));
		}
		
		return clienteRepository.save(clienteSalva);
		
	}
	
	@Transactional
	public void delete(Integer id) {
		Cliente clienteSalva = clienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não cadastrado id : " + id));
		
		try {
			clienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e){
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	
}
