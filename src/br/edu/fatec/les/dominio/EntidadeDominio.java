package br.edu.fatec.les.dominio;

import java.util.Date;

public class EntidadeDominio {

	protected Integer id;
	private Date dtCadastro;

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void complementarDtCadastro() {
		dtCadastro = new Date();
	}

}
