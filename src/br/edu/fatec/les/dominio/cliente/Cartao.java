package br.edu.fatec.les.dominio.cliente;

import br.edu.fatec.les.dominio.EntidadeDominio;

public class Cartao extends EntidadeDominio {

	private String numero;
	private String nome; 
	private Bandeira bandeira; 
	private String codigoSeguranca;
	
	public Cartao() {
		
	}
	
	public Cartao(String numero, String nome, String codigoSeguranca) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.codigoSeguranca = codigoSeguranca;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public Bandeira getBandeira() {
		return bandeira;
	}

	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}	
	
	
}
