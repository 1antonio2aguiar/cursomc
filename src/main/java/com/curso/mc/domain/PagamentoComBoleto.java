package com.curso.mc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.curso.mc.domain.enums.EstadoPagamento;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	public PagamentoComBoleto() {}
	
	public PagamentoComBoleto(Integer id, EstadoPagamento estdoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estdoPagamento, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}
}
