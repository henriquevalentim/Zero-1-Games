package br.edu.fatec.les.strategy.pedido;

import br.edu.fatec.les.dao.EstoqueDAO;
import br.edu.fatec.les.dao.JogoDAO;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Estoque;
import br.edu.fatec.les.dominio.Item;
import br.edu.fatec.les.dominio.Pedido;
import br.edu.fatec.les.dominio.cliente.Motivo;
import br.edu.fatec.les.strategy.IStrategy;

public class RetirarEstoque implements IStrategy {

	private StringBuilder sb = null;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		sb = new StringBuilder();
		Pedido pedido = (Pedido)entidade;
		Estoque estoque = null;
		EstoqueDAO estoqueDAO = new EstoqueDAO();
		JogoDAO jogoDAO = new JogoDAO();
		
		for (Item item: pedido.getItens()) {
			
			if(item.getQuantidade() > item.getJogo().getQuantidade()) {
				sb.append("QUANTIDADE DE JOGOS INSUFICIENTE");
				return sb.toString();
			}else if(item.getJogo().getQuantidade() - item.getQuantidade() == 0) {
				item.getJogo().setMotivo(Motivo.JOGO_SEM_ESTOQUE.toString());
				jogoDAO.excluir(item.getJogo());
			}

			estoque = new Estoque();
			estoque.setJogo(item.getJogo());
			estoque.setQuantidade(item.getQuantidade() * -1);
			estoqueDAO.alterar(estoque);
		}
		
		return sb.toString();
	}

}
