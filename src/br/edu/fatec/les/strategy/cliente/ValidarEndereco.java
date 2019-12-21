package br.edu.fatec.les.strategy.cliente;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Endereco;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarEndereco implements IStrategy {

	private StringBuilder sb = null;

	@Override
	public String processar(EntidadeDominio entidade) {
		sb = new StringBuilder();

		Endereco endereco = (Endereco)entidade;

		if (endereco.getNome() == null || endereco.getNome().trim().equals("")) {
			sb.append("*NOME DO ENDERECO É UM CAMPO OBRIGATÓRIO!");
		}
		if (endereco.getCep() == null || endereco.getCep().trim().equals("")) {
			sb.append("*CEP É UM CAMPO OBRIGATÓRIO!");
		}
		if (endereco.getLogradouro() == null || endereco.getLogradouro().trim().equals("")) {
			sb.append("*LOGRADOURO É UM CAMPO OBRIGATÓRIO!");
		}
		if (endereco.getBairro() == null || endereco.getBairro().trim().equals("")) {
			sb.append("*BAIRRO É UM CAMPO OBRIGATÓRIO!");
		}
		if (endereco.getNumero() == null || endereco.getNumero().trim().equals("")) {
			sb.append("*NUMERO DO ENDEREÃ‡O É UM CAMPO OBRIGATÓRIO!");
		}
		if (endereco.getTipoResidencia() == null || endereco.getTipoResidencia().trim().equals("")) {
			sb.append("*TIPO RESIDENCIA É UM CAMPO OBRIGATÓRIO!");
		}
		if (endereco.getTipoLogradouro() == null || endereco.getTipoLogradouro().trim().equals("")) {
			sb.append("*TIPO LOGRADOURO É UM CAMPO OBRIGATÓRIO!");
		}
		if (endereco.getCidade().getNome() == null || endereco.getCidade().getNome().trim().equals("")) {
			sb.append("*CIDADE É UM CAMPO OBRIGATÓRIO!");
		}
		if (endereco.getCidade().getEstado().getNome() == null
				|| endereco.getCidade().getEstado().getNome().trim().equals("")) {
			sb.append("*ESTADO É UM CAMPO OBRIGATÓRIO!");
		}
		if (endereco.getCidade().getEstado().getPais().getNome() == null
				|| endereco.getCidade().getEstado().getPais().getNome().trim().equals("")) {
			sb.append("*PAIS É UM CAMPO OBRIGATÓRIO!");
		}

		return sb.toString();

	}

}
