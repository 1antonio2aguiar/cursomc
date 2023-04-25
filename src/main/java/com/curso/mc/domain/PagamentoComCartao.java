package com.curso.mc.domain;

import javax.persistence.Entity;

import com.curso.mc.domain.enums.EstadoPagamento;
import com.curso.mc.domain.input.Endereco;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private Integer qtdParcelas;
	
	public PagamentoComCartao() {}

	public PagamentoComCartao(Integer id, EstadoPagamento estdoPagamento, Pedido pedido, Integer qtdParcelas) {
		super(id, estdoPagamento, pedido);
		this.qtdParcelas = qtdParcelas;
	}
	
}
