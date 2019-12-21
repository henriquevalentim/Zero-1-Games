package br.edu.fatec.les.strategy.pedido;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Pedido;
import br.edu.fatec.les.dominio.StatusPedido;
import br.edu.fatec.les.strategy.IStrategy;

public class SolicitacaoDevolucao implements IStrategy {

	private StringBuilder sb = null;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		sb = new StringBuilder();
		
		Pedido pedido = (Pedido)entidade;
		StatusPedido statusPedido = null;

		if(pedido.getStatusPedido().getId().equals(7)) {		
			if(pedido.getStatusPedido().getNome().equals("sim")) {
				pedido.setSolicitacao(null);
				statusPedido = new StatusPedido();
				IStrategy gerarCupom = new GerarCupomValorPedido();
				sb.append(gerarCupom.processar(entidade));
				statusPedido.setId(8);
				pedido.setStatusPedido(statusPedido);
			}else if(pedido.getStatusPedido().getNome().equals("nao")) {
				pedido.setSolicitacao(null);
				statusPedido = new StatusPedido();
				statusPedido.setId(9);
				pedido.setStatusPedido(statusPedido);
			}
		}
		
		if(pedido.getStatusPedido().getId().equals(5)) {		
			if(pedido.getSolicitacao().equals("TROCA") && pedido.getStatusPedido().getNome().equals("sim")) {
				pedido.setSolicitacao(null);
				statusPedido = new StatusPedido();
				statusPedido.setId(7);
				pedido.setStatusPedido(statusPedido);
			}else if(pedido.getSolicitacao().equals("TROCA") && pedido.getStatusPedido().getNome().equals("nao")) {
				pedido.setSolicitacao(null);
				statusPedido = new StatusPedido();
				statusPedido.setId(6);
				pedido.setStatusPedido(statusPedido);
			}
		}

		if(pedido.getStatusPedido().getId().equals(4) ) {
			if(pedido.getSolicitacao().equals("")) {					
				System.out.println("entrou");
				pedido.getStatusPedido().setId(5);
				pedido.setSolicitacao("TROCA");
			}
		}
			
		return sb.toString();
	}

}
