package br.edu.fatec.les.dominio.cliente;

import br.edu.fatec.les.dominio.EntidadeDominio;

public class Senha extends EntidadeDominio {
	
	private String senhaTemp;
    private String senha;
    private String confirmaSenha;
    
	public Senha() {
		super();
	}

	public Senha(String senhaTemp, String senha, String confirmaSenha) {
		super();
		this.senhaTemp = senhaTemp;
		this.senha = senha;
		this.confirmaSenha = confirmaSenha;
	}
	
	public String getSenhaTemp() {
		return senhaTemp;
	}
	public void setSenhaTemp(String senhaTemp) {
		this.senhaTemp = senhaTemp;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

}
