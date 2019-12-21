package br.edu.fatec.les.strategy.pedido;

import br.edu.fatec.les.dao.CupomDAO;
import br.edu.fatec.les.dao.IDAO;
import br.edu.fatec.les.dominio.Cupom;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarCupom implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Cupom cupom =(Cupom)entidade;
		
		IDAO dao = new CupomDAO();
		
		Cupom cup = new Cupom();
		if(cupom.getCodigo() != null && !cupom.getCodigo().trim().equals("")) {
			cup.setCodigo(cupom.getCodigo());

			if(dao.consultar(cup).size() != 0) {
				return "Cupom ja cadastrado!";
			}
		}
		return "";
	}

}
