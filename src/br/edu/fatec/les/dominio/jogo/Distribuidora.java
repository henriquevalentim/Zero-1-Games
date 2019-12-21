package br.edu.fatec.les.dominio.jogo;

import br.edu.fatec.les.dominio.EntidadeDominio;

public class Distribuidora extends EntidadeDominio{

	private String nome;
	private String siteOficial;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSiteOficial() {
		return siteOficial;
	}
	public void setSiteOficial(String siteOficial) {
		this.siteOficial = siteOficial;
	}
	
	
}
