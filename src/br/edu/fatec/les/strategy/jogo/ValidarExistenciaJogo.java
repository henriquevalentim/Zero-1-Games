package br.edu.fatec.les.strategy.jogo;

import java.util.List;

import br.edu.fatec.les.dao.IDAO;
import br.edu.fatec.les.dao.JogoDAO;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Motivo;
import br.edu.fatec.les.dominio.jogo.Jogo;
import br.edu.fatec.les.strategy.IStrategy;

public class ValidarExistenciaJogo implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Jogo jogo = (Jogo) entidade;
	
		if(!jogo.getMotivo().equals(Motivo.JOGO_ATIVADO_PELO_ADMIN.toString())) {
			
			IDAO dao = new JogoDAO();
			
			Jogo jog = new Jogo();
			
			if(jogo.getId() == null) {			
				if(jogo.getCodigo() != null && !jogo.getCodigo().trim().equals("")) {
					jog.setCodigo(jogo.getCodigo());
					
					if(dao.consultar(jog).size() != 0) {
						return "Jogo ja cadastrado!";
					}
				}
			}else {
				jog.setId(jogo.getId());
				List<EntidadeDominio> jogos = dao.consultar(jog);
				if(jogos != null && jogos.size() == 1) {
					Jogo jogoConsulta = (Jogo) jogos.get(0);
					if(jogo.getCodigo().equals(jogoConsulta.getCodigo())) {
						return "";
					}else {
						jogoConsulta = new Jogo();
						jogoConsulta.setCodigo(jogo.getCodigo());
						jogos = dao.consultar(jogoConsulta);
						
						if(jogos.size() != 0) {
							return "Jogo ja cadastrado!";
						}
					}
				}
				
			}
		}
		return null;
	}

}
