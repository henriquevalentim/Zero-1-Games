package br.edu.fatec.les.strategy.cliente;

import br.edu.fatec.les.dao.IDAO;
import br.edu.fatec.les.dao.UsuarioDAO;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Usuario;
import br.edu.fatec.les.strategy.IStrategy;
import br.edu.fatec.les.web.util.Criptografia;

public class ValidarAutenticacao implements IStrategy {
	
	@Override
	public String processar(EntidadeDominio entidade) {

		
		Usuario usuario = (Usuario) entidade;
		IDAO dao = new UsuarioDAO();
		usuario.getSenha().setSenha(Criptografia.criptografar(usuario.getSenha().getSenha()));
		
		if(dao.consultar(usuario).size() != 1) {
			return "Falha na autenticação!";
		}
		
		return null;
	}

}
