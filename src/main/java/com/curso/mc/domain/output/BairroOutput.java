package com.curso.mc.domain.output;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.curso.mc.domain.Bairro;

import lombok.Data;

@Data
public class BairroOutput implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private CidadeOutput cidade;
	
	public BairroOutput(Bairro obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cidade = cidade;
	}
}
