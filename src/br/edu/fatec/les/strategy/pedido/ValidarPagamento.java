package br.edu.fatec.les.strategy.pedido;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.FormaPagamento;
import br.edu.fatec.les.dominio.Pedido;
import br.edu.fatec.les.dominio.StatusPedido;
import br.edu.fatec.les.dominio.cliente.Cartao;
import br.edu.fatec.les.strategy.IStrategy;
import br.edu.fatec.les.web.util.ValidarPagamentoCartao;

public class ValidarPagamento implements IStrategy {

	private StringBuilder sb = null;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		sb = new StringBuilder();
		Pedido pedido = (Pedido)entidade;
		
		if(pedido.getStatusPedido().getId().equals(1)) {

			StatusPedido statusPedido = new StatusPedido();
			Cartao cartao = null;
			if(pedido.getFormaPagamento().size() == 0) {
				sb.append("NENHUMA FORMA DE PAGAMENTO SELECIONADO!");
			}
			for (FormaPagamento formaPagamento : pedido.getFormaPagamento()) {
				if(formaPagamento.getCartao() != null) {
					cartao = formaPagamento.getCartao();
					if(!ValidarPagamentoCartao.validaCartao(cartao.getNumero(), cartao.getCodigoSeguranca())){
						sb.append("*PAGAMENTO COM TERMINADO COM OS NUMEROS "+ cartao.getNumero().substring(cartao.getNumero().length() - 4) +"NEGADO PELA OPERADORA DO CARTÃO!");
						// inserindo id do "pagamento reprovado"
						statusPedido.setId(2);
						pedido.setStatusPedido(statusPedido);
					}else {
						// inserindo id do "pedido em transporte"
						statusPedido.setId(3);
						pedido.setStatusPedido(statusPedido);
						IStrategy retirarEstoque = new RetirarEstoque();
						sb.append(retirarEstoque.processar(pedido));
					}
				}
			}
		}
		return sb.toString();
	}

}
