package com.curso.mc.domain.input;

import com.sun.istack.NotNull;


import lombok.Data;

@Data
public class CidadeInput {
	
	private Integer id;
	
	@NotNull
	private String nome;
	@NotNull
	private Integer estadoId;
}
