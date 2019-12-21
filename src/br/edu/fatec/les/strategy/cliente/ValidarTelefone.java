package br.edu.fatec.les.strategy.cliente;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Cliente;
import br.edu.fatec.les.dominio.cliente.Telefone;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarTelefone implements IStrategy {

private StringBuilder sb = null;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		sb = new StringBuilder();

		Cliente cliente = (Cliente) entidade;
		Telefone telefone = cliente.getTelefone();

		if (telefone.getDdd() == null || telefone.getDdd().trim().equals("")) {
			sb.append("*DDD � UM CAMPO OBRIGAT�RIO!");
		}
		if (telefone.getNumero() == null || telefone.getNumero().trim().equals("")) {
			sb.append("*TELEFONE � UM CAMPO OBRIGAT�RIO!");
		}
		if (telefone.getTipoTelefone() == null || telefone.getTipoTelefone().trim().equals("")) {
			sb.append("*TIPO TELEFONE � UM CAMPO OBRIGAT�RIO!");
		}


		return sb.toString();

	}

}
