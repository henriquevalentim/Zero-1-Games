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
			sb.append("*NOME DO ENDERECO � UM CAMPO OBRIGAT�RIO!");
		}
		if (endereco.getCep() == null || endereco.getCep().trim().equals("")) {
			sb.append("*CEP � UM CAMPO OBRIGAT�RIO!");
		}
		if (endereco.getLogradouro() == null || endereco.getLogradouro().trim().equals("")) {
			sb.append("*LOGRADOURO � UM CAMPO OBRIGAT�RIO!");
		}
		if (endereco.getBairro() == null || endereco.getBairro().trim().equals("")) {
			sb.append("*BAIRRO � UM CAMPO OBRIGAT�RIO!");
		}
		if (endereco.getNumero() == null || endereco.getNumero().trim().equals("")) {
			sb.append("*NUMERO DO ENDEREÇO � UM CAMPO OBRIGAT�RIO!");
		}
		if (endereco.getTipoResidencia() == null || endereco.getTipoResidencia().trim().equals("")) {
			sb.append("*TIPO RESIDENCIA � UM CAMPO OBRIGAT�RIO!");
		}
		if (endereco.getTipoLogradouro() == null || endereco.getTipoLogradouro().trim().equals("")) {
			sb.append("*TIPO LOGRADOURO � UM CAMPO OBRIGAT�RIO!");
		}
		if (endereco.getCidade().getNome() == null || endereco.getCidade().getNome().trim().equals("")) {
			sb.append("*CIDADE � UM CAMPO OBRIGAT�RIO!");
		}
		if (endereco.getCidade().getEstado().getNome() == null
				|| endereco.getCidade().getEstado().getNome().trim().equals("")) {
			sb.append("*ESTADO � UM CAMPO OBRIGAT�RIO!");
		}
		if (endereco.getCidade().getEstado().getPais().getNome() == null
				|| endereco.getCidade().getEstado().getPais().getNome().trim().equals("")) {
			sb.append("*PAIS � UM CAMPO OBRIGAT�RIO!");
		}

		return sb.toString();

	}

}
