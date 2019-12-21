package br.edu.fatec.les.strategy.pedido;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Pedido;
import br.edu.fatec.les.dominio.StatusPedido;
import br.edu.fatec.les.strategy.IStrategy;

public class EntregaProduto implements IStrategy {
	
	@Override
	public String processar(EntidadeDominio entidade) {
		
		Pedido pedido = (Pedido)entidade;
		StatusPedido statusPedido = new StatusPedido();
		if(pedido.getStatusPedido().getId().equals(3)) {
			if(pedido.getStatusPedido().getNome().equals("sim")) {
				statusPedido.setId(4);
			}else if(pedido.getStatusPedido().getNome().equals("nao")) {
				statusPedido.setId(9);
			}
			pedido.setStatusPedido(statusPedido);
		}
		return "";
	}

}
