package com.curso.mc.domain.input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;


import lombok.Data;

@Data
public class ClienteInput implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull
	private String nome;
	@NotNull
	private String email;
	@NotNull
	private String cpfCnpj;
	@NotNull
	private Integer tipoCliente;
	@NotNull
	private List<EnderecoInput> enderecos = new ArrayList<>();
	
	private Set<String> telefones = new HashSet<>();

	@Override
	public String toString() {
		return "ClienteInput [id=" + id + ", nome=" + nome + ", email=" + email + ", cpfCnpj=" + cpfCnpj
				+ ", tipoCliente=" + tipoCliente + ", enderecos=" + enderecos +  "]";
	}

	
}
