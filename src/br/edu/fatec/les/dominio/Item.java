package br.edu.fatec.les.dominio;

import br.edu.fatec.les.dominio.jogo.Jogo;

public class Item extends EntidadeDominio {

	private Jogo jogo;
	private Integer quantidade;
	
	public Item(Jogo jogo, int qtd){
		this.jogo = jogo;
		this.quantidade = qtd; 
//		this.id = id; 
	}
	
	public Item(){
		
	}
	
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
}
