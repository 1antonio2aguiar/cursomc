package com.curso.mc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class Cep implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer numeroInicial;
	private Integer numeroFinal;
	private String  identificacao;
	private String cep;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "bairro_id")
	private Bairro bairro;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "logradouro_id")
	private Logradouro logradouro;
	
	public Cep() {}
	
	public Cep(Integer id, Integer numeroInicial, Integer numeroFinal, String identificacao, String cep, Bairro bairro, Logradouro logradouro) {
		super();
		this.id = id;
		this.numeroInicial = numeroInicial;
		this.numeroFinal = numeroFinal;
		this.identificacao = identificacao;
		this.cep = cep;
		this.bairro = bairro;
		this.logradouro = logradouro;
	}

}
