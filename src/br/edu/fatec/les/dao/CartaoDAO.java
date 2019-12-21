package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Bandeira;
import br.edu.fatec.les.dominio.cliente.Cartao;
import br.edu.fatec.les.web.util.Conexao;

public class CartaoDAO implements IDAO {

	private Connection connection = null;
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Cartao cartao = (Cartao) entidade;
		
		try {
			connection = Conexao.getConnectionPostgres();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_cartao(car_numero, car_nome, car_ban_id, car_codigoseguranca, car_cli_id)");
			sql.append(" VALUES (?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString());

			pst.setString(1, cartao.getNumero());
			pst.setString(2, cartao.getNome());
			pst.setInt(3, cartao.getBandeira().getId());
			pst.setString(4, cartao.getCodigoSeguranca());
			pst.setInt(5, cartao.getId());
			
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
        Cartao cartao = (Cartao) entidade;

        try {
            connection = Conexao.getConnectionPostgres();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_cartao SET car_numero=?, car_nome=?, car_ban_id=?, car_codigoseguranca=? ");
            sql.append("WHERE car_id = ?;");

            pst = connection.prepareStatement(sql.toString());

            pst.setString(1, cartao.getNumero());
            pst.setString(2, cartao.getNome());
            pst.setInt(3, cartao.getBandeira().getId());
            pst.setString(4, cartao.getCodigoSeguranca());
            pst.setInt(6, cartao.getId());

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Cartao cartao = (Cartao) entidade;

		try {
			connection = Conexao.getConnectionPostgres();

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE from tb_cartao WHERE car_id = ?");

			pst = connection.prepareStatement(sql.toString());

			pst.setInt(1, cartao.getId());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		ResultSet rs;

		Cartao cartao = (Cartao) entidade;
		List<EntidadeDominio> listCartao = null;

		try {
			connection = Conexao.getConnectionPostgres();
			
			pst = connection.prepareStatement("SELECT * FROM tb_cartao AS C,tb_bandeira AS B where car_cli_id = ? AND C.car_ban_id = B.ban_id");
				pst.setInt(1, cartao.getId());				
			
			rs = pst.executeQuery();
			listCartao = new ArrayList<EntidadeDominio>();
			Cartao card = null;
			
			while (rs.next()) {
				card = new Cartao();
				Bandeira bandeira = new Bandeira();
				card.setId(rs.getInt("car_id"));
				card.setNome(rs.getString("car_nome"));
				card.setNumero(rs.getString("car_numero"));
				bandeira.setId(rs.getInt("car_ban_id"));
				bandeira.setNome(rs.getString("ban_nome"));
				card.setBandeira(bandeira);
				card.setCodigoSeguranca(rs.getString("car_codigoseguranca"));
				
				
				listCartao.add(card);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listCartao;
	}

}
