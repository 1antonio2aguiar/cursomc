package com.curso.mc.domain.input;

import com.sun.istack.NotNull;


import lombok.Data;

@Data
public class LogradouroInput {
	
	private Integer id;
	
	@NotNull
	private String tipoLogradouro;
	@NotNull
	private String nome;
	@NotNull
	private Integer cidadeId;
}
