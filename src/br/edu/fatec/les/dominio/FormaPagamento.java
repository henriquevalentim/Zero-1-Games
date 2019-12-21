package br.edu.fatec.les.dominio;

import br.edu.fatec.les.dominio.cliente.Cartao;

public class FormaPagamento extends EntidadeDominio {

	private Cupom cupom;
	private Cartao cartao;
	private Double valor;
	
	public Cupom getCupom() {
		return cupom;
	}
	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
