package com.curso.mc.domain.input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.curso.mc.domain.Cep;
import com.sun.istack.NotNull;


import lombok.Data;

@Data
public class EnderecoInput implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotNull
	private Integer numero;
	private String complemento;
	@NotNull
	private Integer cep;
	@NotNull
	private Integer cliente;
	
}
