package com.curso.mc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Bairro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	
	//@JsonBackReference -- funciona como o JsonIgnore
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "bairro_logradouro",
	joinColumns = @JoinColumn(name = "bairro_id"),
	inverseJoinColumns = @JoinColumn(name = "logradouro_id"))
	private List<Logradouro> logradouros = new ArrayList<>();
	
	public Bairro() {
	}
	
	public Bairro(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Bairro(Integer id, String nome, Cidade cidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
	}
	
}
