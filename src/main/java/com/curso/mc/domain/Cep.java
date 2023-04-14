package com.curso.mc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private Integer logradouroId;
	private Integer numeroInicial;
	private Integer numeroFinal;
	private String  identificacao;
	private String cep;
	
	public Cep() {}
	
	public Cep(Integer id, Integer logradouroId, Integer numeroInicial, Integer numeroFinal, String identificacao, String cep) {
		super();
		this.id = id;
		this.logradouroId = logradouroId;
		this.numeroInicial = numeroInicial;
		this.numeroFinal = numeroFinal;
		this.identificacao = identificacao;
		this.cep = cep;
	}

}
