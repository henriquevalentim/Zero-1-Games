package br.edu.fatec.les.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.les.dominio.jogo.Jogo;

public class FiltroJogo {

	private String querry;
	private Map<Integer, String> mapFiltroQuerry;
	private List<Integer> listQuerryJogo;

	private boolean flgJogo = false;

	public FiltroJogo() {

	}

	public String gerarQuerry(Jogo jogo) {

		querry = "SELECT * FROM tb_jogo as J";

		mapFiltroQuerry = new HashMap<Integer, String>();

		String selectJogo = querryJogo(jogo);
		if (flgJogo) {
			querry += " WHERE " + selectJogo + " order by jog_id;";
		} else if (!flgJogo) {
			querry += " order by 1;";
		}
		System.out.println(querry);
		return querry;

	}

	private String querryJogo(Jogo jogo) {

		boolean flgWhere = false;
		String selectJogo = "";
		listQuerryJogo = new ArrayList<Integer>();

		// id
		if (jogo.getId() != null) {
			if (jogo.getId() > 0) {
				mapFiltroQuerry.put(0, "jog_id = " + jogo.getId());
				listQuerryJogo.add(0);
				flgJogo = true;
				flgWhere = true;
			}
		}

		// codigo
		if (jogo.getCodigo() != null) {
			if (!jogo.getCodigo().trim().equals("")) {
				mapFiltroQuerry.put(1, "jog_codigo = '" + jogo.getCodigo() + "'");
				listQuerryJogo.add(1);
				flgWhere = true;
				flgJogo = true;
			}
		}

		// titulo
		if (jogo.getTitulo() != null) {
			if (!jogo.getTitulo().trim().equals("")) {
				mapFiltroQuerry.put(2, "jog_titulo LIKE '%" + jogo.getTitulo() + "%'");
				listQuerryJogo.add(2);
				flgWhere = true;
				flgJogo = true;
			}
		}
		// plataforma
		if (jogo.getPlataforma() != null) {
			if (!jogo.getPlataforma().trim().equals("")) {
				mapFiltroQuerry.put(3, "jog_plataforma = '" + jogo.getPlataforma() + "'");
				listQuerryJogo.add(3);
				flgWhere = true;
				flgJogo = true;
			}
		}

		// categoria
		if (jogo.getCategoria() != null) {
			if (!jogo.getCategoria().trim().equals("")) {
				mapFiltroQuerry.put(4, "jog_categoria = '" + jogo.getCategoria() + "'");
				listQuerryJogo.add(4);
				flgWhere = true;
				flgJogo = true;
			}
		}

		// genero jogo
		if (jogo.getGeneroJogo() != null) {
			if (!jogo.getGeneroJogo().trim().equals("")) {
				mapFiltroQuerry.put(5, "jog_genero_jogo = '" + jogo.getGeneroJogo() + "'");
				listQuerryJogo.add(5);
				flgWhere = true;
				flgJogo = true;
			}
		}

		// classificao indicativa
		if (jogo.getClassificacaoIndicativa() != null) {
			if (!jogo.getClassificacaoIndicativa().trim().equals("")) {
				mapFiltroQuerry.put(6, "jog_classificacao_indicativa = '" + jogo.getClassificacaoIndicativa() + "'");
				listQuerryJogo.add(6);
				flgWhere = true;
				flgJogo = true;
			}
		}

		// numero jogadores online
		if (jogo.getNumeroJogadoresOnline() != null) {
			if (jogo.getNumeroJogadoresOnline() > 0) {
				mapFiltroQuerry.put(7, "jog_numero_jogadores_online = " + jogo.getNumeroJogadoresOnline());
				listQuerryJogo.add(7);
				flgJogo = true;
				flgWhere = true;
			}
		}

		// numero jogadores offline
		if (jogo.getNumeroJogadoresOffline() != null) {
			if (jogo.getNumeroJogadoresOffline() > 0) {
				mapFiltroQuerry.put(8, "jog_numero_jogadores_offline = " + jogo.getNumeroJogadoresOffline());
				listQuerryJogo.add(8);
				flgJogo = true;
				flgWhere = true;
			}
		}

		// preco compra
		if (jogo.getPrecoCompra() != null) {
			if (jogo.getPrecoCompra() > 0) {
				mapFiltroQuerry.put(9, "jog_preco_compra = " + jogo.getPrecoCompra());
				listQuerryJogo.add(9);
				flgJogo = true;
				flgWhere = true;
			}
		}

		// quantidade
		if (jogo.getQuantidade() != null) {
			if (jogo.getQuantidade() > 0) {
				mapFiltroQuerry.put(10, "jog_quantidade = " + jogo.getQuantidade());
				listQuerryJogo.add(10);
				flgJogo = true;
				flgWhere = true;
			}
		}

		// descricao
		if (jogo.getDescricao() != null) {
			if (!jogo.getDescricao().trim().equals("")) {
				mapFiltroQuerry.put(11, "jog_descricao LIKE '%" + jogo.getDescricao() + "%'");
				listQuerryJogo.add(11);
				flgWhere = true;
				flgJogo = true;
			}
		}

		// urlFoto
		if (jogo.getUrlFoto() != null) {
			if (!jogo.getUrlFoto().trim().equals("")) {
				mapFiltroQuerry.put(12, "jog_url_foto = " + jogo.getUrlFoto());
				listQuerryJogo.add(12);
				flgWhere = true;
				flgJogo = true;
			}
		}
		
		if (jogo.getStatus() != null) {
				mapFiltroQuerry.put(13, "jog_status = " + jogo.getStatus());
				listQuerryJogo.add(13);
				flgWhere = true;
				flgJogo = true;
		}


		// teve filtro?
		if (flgWhere) {
			for (Integer i : listQuerryJogo) {
				if (i != listQuerryJogo.get(0)) {
					selectJogo += " AND ";
				}
				selectJogo += mapFiltroQuerry.get(i);
			}
		}

		return selectJogo;

	}
}