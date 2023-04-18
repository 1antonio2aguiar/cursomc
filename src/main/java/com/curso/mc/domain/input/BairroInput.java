package com.curso.mc.domain.input;

import com.sun.istack.NotNull;


import lombok.Data;

@Data
public class BairroInput {
	
	private Integer id;
	
	@NotNull
	private String nome;
	@NotNull
	private Integer cidadeId;
}
