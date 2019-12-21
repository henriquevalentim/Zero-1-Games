package br.edu.fatec.les.strategy.jogo;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Estoque;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarQuantidadeEstoque implements IStrategy {

	private StringBuilder sb = null;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		sb = new StringBuilder();
		
		Estoque estoque = (Estoque) entidade;
		
		if (estoque.getQuantidade() == null) {
			sb.append("INFORME A QUANTIDADE!");
		}
		
		return sb.toString();
	}

}
