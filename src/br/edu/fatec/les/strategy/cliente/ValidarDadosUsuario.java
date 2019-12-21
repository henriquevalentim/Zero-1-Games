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
			sb.append("*LOGIN � UM CAMPO OBRIGAT�RIO!");
		}
		if (usuario.getSenha() == null || usuario.getSenha().getSenha().trim().equals("")) {
			sb.append("*SENHA � UM CAMPO OBRIGAT�RIO!");
		}
		
		return sb.toString();
	}

}
