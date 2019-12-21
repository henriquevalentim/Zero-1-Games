package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Estoque;
import br.edu.fatec.les.web.util.Conexao;

public class EstoqueDAO implements IDAO {

	private Connection connection = null;
	
	@Override
	public void salvar(EntidadeDominio entidade) {
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Estoque estoque = (Estoque) entidade;

		try {
			connection = Conexao.getConnectionPostgres();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_jogo SET jog_quantidade = jog_quantidade + ? WHERE jog_id = ?;");

			pst = connection.prepareStatement(sql.toString());

			pst.setDouble(1, estoque.getQuantidade());
			pst.setInt(2, estoque.getJogo().getId());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		return null;
	}

}
