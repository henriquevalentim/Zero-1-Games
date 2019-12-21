package br.edu.fatec.les.strategy.pedido;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.FormaPagamento;
import br.edu.fatec.les.dominio.Pedido;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarValorPagamento implements IStrategy {

	private StringBuilder sb = null;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		
		sb = new StringBuilder();
		Double tolerancia = 0.0001;
		Pedido pedido = (Pedido)entidade;
		Double totalPago = 0.0;
		
		if(pedido.getStatusPedido() != null && pedido.getStatusPedido().getId().equals(3)) {			
			for (FormaPagamento fp : pedido.getFormaPagamento()) {
				totalPago += fp.getValor();
			}
			Double diferenca = Math.abs(totalPago - pedido.getSubtotal());
			if(diferenca > tolerancia) {
				sb.append("*O VALOR PAGO ESTA INCORRETO!");
			}else if(pedido.getFormaPagamento().size() == 0) {
				sb.append("*O VALOR PAGO ESTA INCORRETO!");
			}
		}
		return sb.toString();
	}

}
