package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Item;
import br.edu.fatec.les.web.util.Conexao;

public class ItemDAO implements IDAO {

	private Connection connection = null;
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		
		PreparedStatement pst = null;
		Item item = (Item) entidade;

		try {
			connection = Conexao.getConnectionPostgres();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_item(ite_quantidade, ite_jog_id, ite_ped_id)");
			sql.append(" VALUES (?,?,?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, item.getQuantidade());
			pst.setInt(2, item.getJogo().getId());
			pst.setInt(3, item.getId());

			pst.executeUpdate();
			
			
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		return null;
	}
}
