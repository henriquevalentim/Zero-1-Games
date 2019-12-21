package br.edu.fatec.les.dominio;

import java.util.List;

import br.edu.fatec.les.dominio.cliente.Cartao;
import br.edu.fatec.les.dominio.cliente.Cliente;
import br.edu.fatec.les.dominio.cliente.Endereco;

public class Pedido extends EntidadeDominio {
	
	private String numeroPedido;
	private List<Item>itens;
	private List<Cartao> cartoes;
	private Endereco endereco;
	private MetodoEntrega metodoEntrega;
	private Cliente cliente;
	private StatusPedido statusPedido;
	private String solicitacao;
	private Double subtotal;
	private String dataCompra;
	private List<FormaPagamento> formaPagamento;

	public List<FormaPagamento> getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(List<FormaPagamento> formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public Pedido() {
		super();
	}

	public Pedido(List<Item> itens, List<Cartao> cartoes, Endereco endereco, MetodoEntrega metodoEntrega,
			Cliente cliente, Double subtotal, String dataCompra) {
		super();
		this.itens = itens;
		this.cartoes = cartoes;
		this.endereco = endereco;
		this.metodoEntrega = metodoEntrega;
		this.cliente = cliente;
		this.subtotal = subtotal;
		this.dataCompra = dataCompra;
	}
	
	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public MetodoEntrega getMetodoEntrega() {
		return metodoEntrega;
	}

	public void setMetodoEntrega(MetodoEntrega metodoEntrega) {
		this.metodoEntrega = metodoEntrega;
	}

	public String getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(String solicitacao) {
		this.solicitacao = solicitacao;
	}

}
