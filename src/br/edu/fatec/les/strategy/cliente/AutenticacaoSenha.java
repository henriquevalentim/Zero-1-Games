package br.edu.fatec.les.strategy.cliente;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Usuario;
import br.edu.fatec.les.strategy.IStrategy;
import br.edu.fatec.les.web.util.Criptografia;

public class AutenticacaoSenha implements IStrategy {
	
	@Override
	public String processar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario)entidade;
		
		if(usuario.getSenha().getSenhaTemp() != null) {
			if(usuario.getSenha().getSenhaTemp().equals(usuario.getSenha().getConfirmaSenha())) {
				
				String senhaCriptografada = "";
				senhaCriptografada = Criptografia.criptografar(usuario.getSenha().getConfirmaSenha());
				usuario.getSenha().setSenha(senhaCriptografada);
				return "";
			}else {
				return "*As senhas estão diferentes!";
			}
		}
		return "";
		
	}

}
