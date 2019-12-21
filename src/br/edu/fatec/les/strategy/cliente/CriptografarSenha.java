package br.edu.fatec.les.strategy.cliente;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Cliente;
import br.edu.fatec.les.strategy.IStrategy;
import br.edu.fatec.les.web.util.Criptografia;

public class CriptografarSenha implements IStrategy {
	
	@Override
	public String processar(EntidadeDominio entidade) {
		
		Cliente cliente = (Cliente)entidade;
		String senhaCriptografada = "";
		senhaCriptografada = Criptografia.criptografar(cliente.getSenha().getSenha());
		cliente.getSenha().setSenha(senhaCriptografada);
		cliente.getUsuario().getSenha().setSenha(senhaCriptografada);
		return "";
	}

}
