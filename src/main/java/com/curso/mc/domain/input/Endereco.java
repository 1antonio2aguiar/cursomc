package com.curso.mc.domain.input;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.curso.mc.domain.Cep;
import com.curso.mc.domain.Cliente;

import lombok.Data;

@Data
@Entity
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer numero;
	private String complemento;
	
	@ManyToOne
	@JoinColumn(name = "cep_id")
	private Cep cep;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	public Endereco() {}

	public Endereco(Integer id, Integer numero, String complemento, Cliente cliente, Cep cep) {
		super();
		this.id = id;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.cliente = cliente;
	}
	
	
	
}
