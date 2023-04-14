package com.curso.mc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Logradouro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String tipoLogradouro;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	
	//@JsonManagedReference -- funciona como o JsonIgnore
	@ManyToMany(mappedBy = "logradouros")
	private List<Bairro> bairros = new ArrayList<>();
	
	public Logradouro() {}
	
	public Logradouro(Integer id, String tipoLogradouro, String nome) {
		super();
		this.id = id;
		this.tipoLogradouro = tipoLogradouro;
		this.nome = nome;
	}

}
