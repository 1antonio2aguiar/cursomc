package com.curso.mc.domain.input;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;


import lombok.Data;

@Data
public class PedidoInput implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date dataPedido;
	@NotNull
	private Integer estadoPagamento;
	private Integer qtdParcelas;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	@NotNull
	private Integer cliente;
	@NotNull
	private Integer enderecoDeEntrega;
	@NotNull
	private List<ItenPedidoInput> itensPedido = new ArrayList<>();
	
	@Override
	public String toString() {
		return "PedidoInput [id=" + id + ", dataPedido=" + dataPedido + ", " + ", estadoPagamento=" + estadoPagamento
				+ ", qtdParcelas=" + qtdParcelas
				+ ", dataPagamento=" + dataPagamento
				+ ", dataVencimento=" + dataVencimento
				+ "cliente=" + cliente + ", enderecoDeEntrega=" + enderecoDeEntrega +  "]";
	}
	
}
