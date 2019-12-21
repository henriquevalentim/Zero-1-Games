package br.edu.fatec.les.strategy.jogo;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Motivo;
import br.edu.fatec.les.dominio.jogo.Jogo;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarDadosJogo implements IStrategy {

	private StringBuilder sb = null;

	@Override
	public String processar(EntidadeDominio entidade) {
		sb = new StringBuilder();
		Jogo jogo = (Jogo) entidade;
		
		if(jogo.getMotivo() != null && 
				!jogo.getMotivo().equals(Motivo.JOGO_ATIVADO_PELO_ADMIN.toString())) {
			if (jogo.getCodigo() == null || jogo.getCodigo().trim().equals("")) {
				sb.append("*CODIGO É UM CAMPO OBRIGATÓRIO!");
			}
			if (jogo.getTitulo() == null || jogo.getTitulo().trim().equals("")) {
				sb.append("*TITULO É UM CAMPO OBRIGATÓRIO!");
			}
			if (jogo.getPlataforma() == null || jogo.getPlataforma().trim().equals("")) {
				sb.append("*PLATAFORMA É UM CAMPO OBRIGATÓRIO!");
			}
			if (jogo.getGeneroJogo() == null || jogo.getGeneroJogo().trim().equals("")) {
				sb.append("*GENERO É UM CAMPO OBRIGATÓRIO!");
			}
			if (jogo.getClassificacaoIndicativa() == null || jogo.getClassificacaoIndicativa().trim().equals("")) {
				sb.append("*CLASSIFICAÇÃO É UM CAMPO OBRIGATÓRIO!");
			}
			if (jogo.getDescricao() == null || jogo.getDescricao().trim().equals("")) {
				sb.append("*DESCRIÇÃO DO JOGO É UM CAMPO OBRIGATÓRIO!");
			}
	//		if (jogo.getUrlFoto() == null || jogo.getUrlFoto().trim().equals("")) {
	//			sb.append("*FOTO É UM CAMPO OBRIGATÓRIO!");
	//		}
	
			if (jogo.getNumeroJogadoresOffline() == null) {
				sb.append("*NUMEROS DE JOGADORES OFFLINE É UM CAMPO OBRIGATÓRIO!");
			}
			if (jogo.getNumeroJogadoresOnline() == null) {
				sb.append("*NUMEROS DE JOGADORES ONLINE É UM CAMPO OBRIGATÓRIO!");
			}
			if (jogo.getPrecoCompra() == null) {
				sb.append("*PREÇO DE COMPRA É UM CAMPO OBRIGATÓRIO!");
			}
			if (jogo.getDistribuidora().getNome() == null || jogo.getDistribuidora().getNome().trim().equals("")) {
				sb.append("*DISTRIBUIDORA É UM CAMPO OBRIGATÓRIO!");
			}
			if (jogo.getDesenvolvedor().getNome() == null || jogo.getDesenvolvedor().getNome().trim().equals("")) {
				sb.append("*DESENVOLVEDOR É UM CAMPO OBRIGATÓRIO!");
			}
		}
		return sb.toString();

	}

}
