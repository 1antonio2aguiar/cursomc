package com.curso.mc.domain.output;

import java.io.Serializable;

import lombok.Data;

@Data
public class CidadeOutput implements Serializable{
	private static final long serialVersionUID = 1L;
		
	private Integer id;
	private String nome;
	
}
