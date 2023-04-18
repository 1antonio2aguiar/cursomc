package com.curso.mc.domain.input;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class CepInput {

	private Integer id;
	@NotNull
	private Integer numeroInicial;
	@NotNull
	private Integer numeroFinal;
	@NotNull
	private String  identificacao;
	@NotNull
	private String cep;
	@NotNull
	private Integer bairroId;
	@NotNull
	private Integer logradouroId;
}
