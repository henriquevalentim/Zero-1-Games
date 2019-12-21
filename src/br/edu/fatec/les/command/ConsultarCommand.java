package br.edu.fatec.les.command;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Resultado;

public class ConsultarCommand extends AbstractCommand {

	@Override
	public Resultado executar(EntidadeDominio entidade) {
		return fachada.consultar(entidade);
	}

}
