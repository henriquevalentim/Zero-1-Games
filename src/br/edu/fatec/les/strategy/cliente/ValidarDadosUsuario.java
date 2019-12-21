package br.edu.fatec.les.strategy.cliente;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Usuario;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarDadosUsuario implements IStrategy {
	
	private StringBuilder sb = null;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		sb = new StringBuilder();

		Usuario usuario = (Usuario) entidade;

		if (usuario.getLogin() == null || usuario.getLogin().trim().equals("")) {
			sb.append("*LOGIN É UM CAMPO OBRIGATÓRIO!");
		}
		if (usuario.getSenha() == null || usuario.getSenha().getSenha().trim().equals("")) {
			sb.append("*SENHA É UM CAMPO OBRIGATÓRIO!");
		}
		
		return sb.toString();
	}

}
