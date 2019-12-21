package br.edu.fatec.les.strategy.cliente;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Cartao;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarDadosCartao implements IStrategy {

private StringBuilder sb = null;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		sb = new StringBuilder();

		Cartao cartao = (Cartao) entidade;

		if (cartao.getNumero().length() != 16) {
			sb.append("*NUMERO DO CARTÃO ESTA INCORRETO!");
		}

		if (cartao.getNome().length() < 2) {
			sb.append("*NOME DO USUARIO DEVE TER MAIS DE 2 CARACTERES!");
		}

		if (cartao.getCodigoSeguranca().length() != 3) {
			sb.append("*CODIGO DE SEGURANCA INCORRETO!");
		}
		
		return sb.toString();

	}

}
