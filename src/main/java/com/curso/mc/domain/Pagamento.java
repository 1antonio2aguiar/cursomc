package com.curso.mc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.curso.mc.domain.enums.EstadoPagamento;
import com.curso.mc.domain.input.Endereco;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	private Integer estdoPagamento;
	
	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId
	private Pedido pedido;
	
	public Pagamento() {}
	
	public Pagamento(Integer id, EstadoPagamento estadoPagamento, Pedido pedido) {
		this.id = id;
		this.estdoPagamento = estadoPagamento.getCod();
		this.pedido = pedido;
	}
	
	public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
		this.estdoPagamento = estadoPagamento.getCod();
	}
	
	public EstadoPagamento getEstadoPagamento() {
		return EstadoPagamento.toEnum(estdoPagamento);
	}
}
