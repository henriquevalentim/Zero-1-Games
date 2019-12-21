package br.edu.fatec.les.dominio;

public class Dashboard extends EntidadeDominio {

	private Integer qtdClientes;
	private Integer estoqueTotal;
	private Integer produtosSemEstoque;
	private Double faturamentoTotal;

	public Integer getQtdClientes() {
		return qtdClientes;
	}

	public void setQtdClientes(Integer qtdClientes) {
		this.qtdClientes = qtdClientes;
	}

	public Integer getEstoqueTotal() {
		return estoqueTotal;
	}

	public void setEstoqueTotal(Integer estoqueTotal) {
		this.estoqueTotal = estoqueTotal;
	}

	public Integer getProdutosSemEstoque() {
		return produtosSemEstoque;
	}

	public void setProdutosSemEstoque(Integer produtosSemEstoque) {
		this.produtosSemEstoque = produtosSemEstoque;
	}

	public Double getFaturamentoTotal() {
		return faturamentoTotal;
	}

	public void setFaturamentoTotal(Double faturamentoTotal) {
		this.faturamentoTotal = faturamentoTotal;
	}

}
