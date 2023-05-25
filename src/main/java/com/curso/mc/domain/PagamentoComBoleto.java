package com.curso.mc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.curso.mc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;
	
	public PagamentoComBoleto() {}
	
	public PagamentoComBoleto(Integer id, EstadoPagamento estdoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estdoPagamento, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}
}
