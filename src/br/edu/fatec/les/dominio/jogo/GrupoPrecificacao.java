package br.edu.fatec.les.dominio.jogo;

import br.edu.fatec.les.dominio.EntidadeDominio;

public class GrupoPrecificacao extends EntidadeDominio {
	
	private String nome;
	private double margemLucro;
	private double valorMaximo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getMargemLucro() {
		return margemLucro;
	}
	public void setMargemLucro(double margemLucro) {
		this.margemLucro = margemLucro;
	}
	public double getValorMaximo() {
		return valorMaximo;
	}
	public void setValorMaximo(double valorMaximo) {
		this.valorMaximo = valorMaximo;
	}
	
	
}
