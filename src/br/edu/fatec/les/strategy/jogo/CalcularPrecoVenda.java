package br.edu.fatec.les.strategy.jogo;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.jogo.GrupoPrecificacao;
import br.edu.fatec.les.dominio.jogo.Jogo;
import br.edu.fatec.les.strategy.IStrategy;

public class CalcularPrecoVenda implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		
		Jogo jogo = (Jogo)entidade;
		GrupoPrecificacao gp = new GrupoPrecificacao();
		Double preco = jogo.getPrecoCompra();
		if(jogo.getPrecoCompra() != null) {			
			if(jogo.getPrecoCompra() > 200.0) {
				jogo.setPrecoVenda(preco * 1.2);
				gp.setNome("GRUPO A");
				jogo.setGrupoPrecificacao(gp);
			}else if(jogo.getPrecoCompra() > 130.0) {
				jogo.setPrecoVenda(preco * 1.3);
				gp.setNome("GRUPO B");
				jogo.setGrupoPrecificacao(gp);
			}else if(jogo.getPrecoCompra() > 0.0) {
				jogo.setPrecoVenda(preco * 1.4);
				gp.setNome("GRUPO C");
				jogo.setGrupoPrecificacao(gp);
			}
		}
		return "";
	}

}
