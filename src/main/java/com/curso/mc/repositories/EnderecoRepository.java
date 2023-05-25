package com.curso.mc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.mc.domain.input.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

	//Optional<Endereco> findByCepId(Integer cep);
	
	//Optional<Pessoas> findByCpfCnpj(String cpfCnpj); // pessoas repositorio fianaceiro
	
	void deleteByClienteId(Integer clienteId); // parcelas contrato repositorio fianaceiro

}
