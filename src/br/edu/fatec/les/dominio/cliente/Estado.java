package br.edu.fatec.les.dominio.cliente;

import br.edu.fatec.les.dominio.EntidadeDominio;

public class Estado extends EntidadeDominio{

	private String nome;
	private Pais pais;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
}
