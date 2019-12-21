package br.edu.fatec.les.strategy.pedido;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.FormaPagamento;
import br.edu.fatec.les.dominio.Pedido;
import br.edu.fatec.les.strategy.IStrategy;

public class AplicarValorCupomCartaoCredito implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Pedido pedido = (Pedido)entidade;
		Boolean temCupom = false;
		Double valorCupom = 0.0;
		if(pedido.getStatusPedido().getId().equals(1)) {			
			for (FormaPagamento formaPagamento : pedido.getFormaPagamento()) {
				if(formaPagamento.getCupom() != null && pedido.getFormaPagamento().size() == 2) {
					temCupom = true;
					valorCupom = formaPagamento.getCupom().getValor();
					break;
				}
			}
			if(temCupom) {			
				for (FormaPagamento formaPagamento : pedido.getFormaPagamento()) {
					if(formaPagamento.getCartao() != null) {
						formaPagamento.setValor(formaPagamento.getValor() - valorCupom);
						break;
					}
				}
			}
		}
		
		return "";
	}

}
