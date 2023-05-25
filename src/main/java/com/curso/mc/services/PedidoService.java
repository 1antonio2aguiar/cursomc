package com.curso.mc.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Cep;
import com.curso.mc.domain.Cliente;
import com.curso.mc.domain.ItemPedido;
import com.curso.mc.domain.Pagamento;
import com.curso.mc.domain.PagamentoComBoleto;
import com.curso.mc.domain.PagamentoComCartao;
import com.curso.mc.domain.Pedido;
import com.curso.mc.domain.Produto;
import com.curso.mc.domain.enums.EstadoPagamento;
import com.curso.mc.domain.enums.TipoCliente;
import com.curso.mc.domain.input.ClienteInput;
import com.curso.mc.domain.input.Endereco;
import com.curso.mc.domain.input.EnderecoInput;
import com.curso.mc.domain.input.ItenPedidoInput;
import com.curso.mc.domain.input.PedidoInput;
import com.curso.mc.repositories.ClienteRepository;
import com.curso.mc.repositories.EnderecoRepository;
import com.curso.mc.repositories.ItemPedidoRepository;
import com.curso.mc.repositories.PagamentoRepository;
import com.curso.mc.repositories.PedidoRepository;
import com.curso.mc.repositories.ProdutoRepository;
import com.curso.mc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired private PedidoRepository pedidoRepository;
	@Autowired private ItemPedidoRepository itemPedidoRepository;
	@Autowired private ProdutoRepository produtoRepository;
	@Autowired private ClienteRepository clienteRepository;
	@Autowired private EnderecoRepository enderecoRepository;
	@Autowired private PagamentoRepository pagamentoRepository;
	
	// Lista de categorias
	public List<Pedido> findAll(){
		return pedidoRepository.findAll();
	}
	
	// Pedidos por id
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional 
	public Pedido insert(PedidoInput pedidoInput) {
		Pedido pedido = new Pedido();
		BeanUtils.copyProperties(pedidoInput, pedido, "id");
		
		// BUSCO CLIENTE
		Cliente clientePedido = clienteRepository.findById(pedidoInput.getCliente()).get();
		pedido.setCliente(clientePedido);
		
		//BUSCO O ENDERECO
		Endereco enderecoPedido = enderecoRepository.findById(pedidoInput.getEnderecoDeEntrega()).get();
		pedido.setEnderecoDeEntrega(enderecoPedido);
		
		if(pedidoInput.getEstadoPagamento() == 1) {
			PagamentoComCartao pcc = new PagamentoComCartao();
			pcc.setEstadoPagamento(EstadoPagamento.QUITADO);
			pcc.setQtdParcelas(pedidoInput.getQtdParcelas());
			pcc.setPedido(pedido);
			
			clientePedido.getPedidos().addAll(Arrays.asList(pedido));
			pagamentoRepository.saveAll(Arrays.asList(pcc));
		} else {
			if(pedidoInput.getEstadoPagamento() == 2) {
				PagamentoComBoleto pcb = new PagamentoComBoleto();
				pcb.setEstadoPagamento(EstadoPagamento.PENDENTE);
				pcb.setDataPagamento(pedidoInput.getDataPagamento());
				pcb.setDataVencimento(pedidoInput.getDataVencimento());
				pcb.setPedido(pedido);
				
				clientePedido.getPedidos().addAll(Arrays.asList(pedido));
				pagamentoRepository.saveAll(Arrays.asList(pcb));
			}
		}
		Pedido pedidoSalva = pedidoRepository.save(pedido);
		
		// GRAVA ITENS DO PEDIDO
		for(ItenPedidoInput ipi : pedidoInput.getItensPedido()) {
			
			// BUSCO PRODUTO
			Produto produto = produtoRepository.findById(ipi.getProduto()).get();
			
			ItemPedido itemPedido = new ItemPedido(pedidoSalva,produto,ipi.getDesconto(),ipi.getQuantidade(),ipi.getPreco());

			pedido.getItens().addAll(Arrays.asList(itemPedido));
			
			itemPedidoRepository.saveAll(Arrays.asList(itemPedido));
		}
		return pedidoSalva;
	}
	
	
}
