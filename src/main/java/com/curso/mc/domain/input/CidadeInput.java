package com.curso.mc.domain.input;

import java.io.Serializable;

import com.sun.istack.NotNull;


import lombok.Data;

@Data
public class CidadeInput implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull
	private String nome;
	@NotNull
	private Integer estadoId;
}
