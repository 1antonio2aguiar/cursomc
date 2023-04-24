package com.curso.mc.domain.input;

import java.io.Serializable;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class CepInput implements Serializable{
	private static final long serialVersionUID = 1L;

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
