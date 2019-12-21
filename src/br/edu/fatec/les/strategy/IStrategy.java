package br.edu.fatec.les.strategy;

import br.edu.fatec.les.dominio.EntidadeDominio;

public interface IStrategy {
	public String processar(EntidadeDominio entidade);
}
