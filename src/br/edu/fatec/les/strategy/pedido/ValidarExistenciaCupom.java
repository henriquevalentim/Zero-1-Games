package br.edu.fatec.les.strategy.pedido;

import java.util.List;

import br.edu.fatec.les.dao.CupomDAO;
import br.edu.fatec.les.dao.IDAO;
import br.edu.fatec.les.dominio.Cupom;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarExistenciaCupom implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Cupom cupom =(Cupom)entidade;
		
		IDAO dao = new CupomDAO();
		
		Cupom cup = new Cupom();
		if(cupom.getCodigo() != null) {
			cup.setCodigo(cupom.getCodigo());
			List<EntidadeDominio> cupons = dao.consultar(cup);
			if(cupons.size() == 1) {
				cup = (Cupom) cupons.get(0);
				cupom.setValor(cup.getValor());
			}else {
				return "Esse cupom não existe!";
			}
		}
		return "";
	}

}
