package br.edu.fatec.les.dominio;

import br.edu.fatec.les.dominio.jogo.Jogo;

public class Estoque extends EntidadeDominio {

	private Jogo jogo;
	private Integer quantidade;

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
