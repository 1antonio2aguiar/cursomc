package com.curso.mc.domain.input;

import java.io.Serializable;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class ItenPedidoInput implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Integer produto;
	@NotNull
	private Double desconto;
	@NotNull
	private Integer quantidade;
	@NotNull
	private Double  preco;
}
