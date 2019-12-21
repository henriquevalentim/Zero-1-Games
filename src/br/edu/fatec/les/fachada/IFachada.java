package br.edu.fatec.les.fachada;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Resultado;

public interface IFachada {
	public Resultado salvar(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);

}
