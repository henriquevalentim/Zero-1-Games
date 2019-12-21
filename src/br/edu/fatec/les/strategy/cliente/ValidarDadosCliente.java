package br.edu.fatec.les.strategy.cliente;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Cliente;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarDadosCliente implements IStrategy {

private StringBuilder sb = null;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		sb = new StringBuilder();

		Cliente cliente = (Cliente) entidade;

		if (cliente.getNome() == null || cliente.getNome().trim().equals("")) {
			sb.append("*NOME � UM CAMPO OBRIGAT�RIO!");
		}

		if (cliente.getCpf() == null || cliente.getCpf().trim().equals("")) {
			sb.append("*CPF � UM CAMPO OBRIGAT�RIO!");
		}

		if (cliente.getEmail() == null || cliente.getEmail().trim().equals("")) {
			sb.append("*EMAIL � UM CAMPO OBRIGAT�RIO!");
		}

		if (cliente.getSenha().getSenha() == null || cliente.getSenha().getSenha().trim().equals("")) {
			sb.append("*SENHA � UM CAMPO OBRIGAT�RIO!");
		}
		
		if (cliente.getDataNascimento() == null) {
			sb.append("*DATA DE NASCIMENTO � UM CAMPO OBRIGAT�RIO!");
		}
		
		if (cliente.getGenero() == null || cliente.getGenero().trim().equals("")) {
			sb.append("*GENERO � UM CAMPO OBRIGAT�RIO!");
		}
		
		if (cliente.getTelefone().getNumero() == null || cliente.getTelefone().getNumero().trim().equals("")) {
			sb.append("*TELEFONE � UM CAMPO OBRIGAT�RIO!");
		}
		
		return sb.toString();

	}

}
