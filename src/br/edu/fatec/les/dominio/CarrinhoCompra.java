package br.edu.fatec.les.dominio;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoCompra extends EntidadeDominio {

	private List<Item> itens = new ArrayList<Item>();
	private Double total = 0.0;
	
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
}
