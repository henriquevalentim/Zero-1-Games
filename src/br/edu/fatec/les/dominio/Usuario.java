package br.edu.fatec.les.dominio;

import br.edu.fatec.les.dominio.cliente.Senha;

public class Usuario extends EntidadeDominio {
	
	private String login;
	private Senha senha;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Senha getSenha() {
		return senha;
	}
	public void setSenha(Senha senha) {
		this.senha = senha;
	}
}
