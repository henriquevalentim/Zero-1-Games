package br.edu.fatec.les.command;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Resultado;

public interface ICommand {

	public Resultado executar(EntidadeDominio entidade);
	
}
