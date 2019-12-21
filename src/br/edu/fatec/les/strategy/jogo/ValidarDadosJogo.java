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
				sb.append("*CODIGO � UM CAMPO OBRIGAT�RIO!");
			}
			if (jogo.getTitulo() == null || jogo.getTitulo().trim().equals("")) {
				sb.append("*TITULO � UM CAMPO OBRIGAT�RIO!");
			}
			if (jogo.getPlataforma() == null || jogo.getPlataforma().trim().equals("")) {
				sb.append("*PLATAFORMA � UM CAMPO OBRIGAT�RIO!");
			}
			if (jogo.getGeneroJogo() == null || jogo.getGeneroJogo().trim().equals("")) {
				sb.append("*GENERO � UM CAMPO OBRIGAT�RIO!");
			}
			if (jogo.getClassificacaoIndicativa() == null || jogo.getClassificacaoIndicativa().trim().equals("")) {
				sb.append("*CLASSIFICA��O � UM CAMPO OBRIGAT�RIO!");
			}
			if (jogo.getDescricao() == null || jogo.getDescricao().trim().equals("")) {
				sb.append("*DESCRI��O DO JOGO � UM CAMPO OBRIGAT�RIO!");
			}
	//		if (jogo.getUrlFoto() == null || jogo.getUrlFoto().trim().equals("")) {
	//			sb.append("*FOTO � UM CAMPO OBRIGAT�RIO!");
	//		}
	
			if (jogo.getNumeroJogadoresOffline() == null) {
				sb.append("*NUMEROS DE JOGADORES OFFLINE � UM CAMPO OBRIGAT�RIO!");
			}
			if (jogo.getNumeroJogadoresOnline() == null) {
				sb.append("*NUMEROS DE JOGADORES ONLINE � UM CAMPO OBRIGAT�RIO!");
			}
			if (jogo.getPrecoCompra() == null) {
				sb.append("*PRE�O DE COMPRA � UM CAMPO OBRIGAT�RIO!");
			}
			if (jogo.getDistribuidora().getNome() == null || jogo.getDistribuidora().getNome().trim().equals("")) {
				sb.append("*DISTRIBUIDORA � UM CAMPO OBRIGAT�RIO!");
			}
			if (jogo.getDesenvolvedor().getNome() == null || jogo.getDesenvolvedor().getNome().trim().equals("")) {
				sb.append("*DESENVOLVEDOR � UM CAMPO OBRIGAT�RIO!");
			}
		}
		return sb.toString();

	}

}
