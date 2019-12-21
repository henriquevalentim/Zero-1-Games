package br.edu.fatec.les.dominio.cliente;

import br.edu.fatec.les.dominio.EntidadeDominio;

public class Telefone extends EntidadeDominio {

	private String ddd;
	private String numero;
	private String tipoTelefone;

	public Telefone() {
	}

	public Telefone(String ddd, String numero, String tipoTelefone) {
		super();
		this.ddd = ddd;
		this.numero = numero;
		this.tipoTelefone = tipoTelefone;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

}
