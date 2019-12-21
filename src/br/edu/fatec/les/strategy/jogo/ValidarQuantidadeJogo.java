package br.edu.fatec.les.strategy.jogo;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Motivo;
import br.edu.fatec.les.dominio.jogo.Jogo;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarQuantidadeJogo implements IStrategy {

	private StringBuilder sb = null;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		Jogo jogo = (Jogo) entidade;
		if(!jogo.getMotivo().equals(Motivo.JOGO_ATIVADO_PELO_ADMIN.toString())) {
			sb = new StringBuilder();
			if (jogo.getQuantidade() != null && jogo.getQuantidade().equals(0)) {
				sb.append("*Quantidade de produtos não pode ser igual a 0!");
			}
			return sb.toString();
		}
		return "";
		
	}

}
