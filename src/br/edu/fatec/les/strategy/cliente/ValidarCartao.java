package br.edu.fatec.les.strategy.cliente;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Cartao;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarCartao implements IStrategy {

private StringBuilder sb = null;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		sb = new StringBuilder();

		Cartao cartao = (Cartao) entidade;

		if (cartao.getNumero() == null || cartao.getNumero().trim().equals("")) {
			sb.append("*NUMERO � UM CAMPO OBRIGAT�RIO!");
		}

		if (cartao.getNome() == null || cartao.getNome().trim().equals("")) {
			sb.append("*NOME � UM CAMPO OBRIGAT�RIO!");
		}

		if (cartao.getBandeira() == null) {
			sb.append("*BANDEIRA � UM CAMPO OBRIGAT�RIO!");
		}

		if (cartao.getCodigoSeguranca() == null || cartao.getCodigoSeguranca().trim().equals("")) {
			sb.append("*CODIGO DE SEGURANCA � UM CAMPO OBRIGAT�RIO!");
		}
		
		return sb.toString();

	}

}
