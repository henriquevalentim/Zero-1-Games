package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Telefone;
import br.edu.fatec.les.web.util.Conexao;

public class TelefoneDAO implements IDAO {

	private Connection connection = null;
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Telefone telefone = (Telefone) entidade;
		
		try {
			connection = Conexao.getConnectionPostgres();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_telefone(tel_ddd, tel_numero, tel_tipotelefone, tel_cli_id)");
			sql.append(" VALUES (?,?,?,?)");

			pst = connection.prepareStatement(sql.toString());

			pst.setString(1, telefone.getDdd());
			pst.setString(2, telefone.getNumero());
			pst.setString(3, telefone.getTipoTelefone());
			pst.setInt(4, telefone.getId());

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
		
		PreparedStatement pst = null;
        Telefone telefone = (Telefone) entidade;

        try {
            connection = Conexao.getConnectionPostgres();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_telefone SET tel_ddd=?, tel_numero=?, tel_tipotelefone=? ");
            sql.append("WHERE tel_id = ?;");

            pst = connection.prepareStatement(sql.toString());

            pst.setString(1, telefone.getDdd());
			pst.setString(2, telefone.getNumero());
			pst.setString(3, telefone.getTipoTelefone());
			pst.setInt(4, telefone.getId());


            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Telefone telefone = (Telefone) entidade;

		try {
			connection = Conexao.getConnectionPostgres();

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE from tb_telefone WHERE tel_id = ?");

			pst = connection.prepareStatement(sql.toString());

			pst.setInt(1, telefone.getId());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		return null;
	}

}
