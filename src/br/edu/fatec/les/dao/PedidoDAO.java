package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.FormaPagamento;
import br.edu.fatec.les.dominio.Item;
import br.edu.fatec.les.dominio.Pedido;
import br.edu.fatec.les.dominio.StatusPedido;
import br.edu.fatec.les.dominio.cliente.Cliente;
import br.edu.fatec.les.web.util.Conexao;

public class PedidoDAO implements IDAO {

	private Connection connection = null;
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		
		PreparedStatement pst = null;
		Pedido pedido = (Pedido) entidade;
		
		try {
			connection = Conexao.getConnectionPostgres();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_pedido(ped_stp_id, ped_subtotal,");
			sql.append("ped_cli_id,ped_met_ent_id,ped_end_id,ped_numero) VALUES (?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, pedido.getStatusPedido().getId());
			pst.setDouble(2, pedido.getSubtotal());
			pst.setInt(3, pedido.getCliente().getUsuario().getId());
			pst.setInt(4, pedido.getMetodoEntrega().getId());
			pst.setInt(5, pedido.getEndereco().getId());
			pst.setString(6, pedido.getNumeroPedido());

			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
			ItemDAO itemDAO = new ItemDAO();
			int idPedido=0;
			if(rs.next())
				idPedido = rs.getInt(1);
			
			connection.commit();
			
			for (Item item : pedido.getItens()) {
				item.setId(idPedido);
				itemDAO.salvar(item);
			}
			
			for (FormaPagamento formaPagamento : pedido.getFormaPagamento()) {
				formaPagamento.setId(idPedido);
				formaPagamentoDAO.salvar(formaPagamento);
			}
			
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
        Pedido pedido = (Pedido) entidade;

        try {
            connection = Conexao.getConnectionPostgres();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_pedido SET ped_stp_id=?, ped_solicitacao=? WHERE ped_id=?;");

            pst = connection.prepareStatement(sql.toString());
        	pst.setInt(1, pedido.getStatusPedido().getId());
        	pst.setString(2, pedido.getSolicitacao());
            pst.setInt(3, pedido.getId());

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
//		PreparedStatement pst = null;
//		Cliente cliente = (Cliente) entidade;
//
//		try {
//			connection = Conexao.getConnectionPostgres();
//
//			StringBuilder sql = new StringBuilder();
//			sql.append("UPDATE tb_cliente SET cli_status = false WHERE cli_id = ?");
//
//			pst = connection.prepareStatement(sql.toString());
//
//			pst.setInt(1, cliente.getId());
//
//			pst.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Pedido pedido = (Pedido) entidade;
		PreparedStatement ps = null;

		List<EntidadeDominio> listPedido = null;

		FiltroPedido filtro = new FiltroPedido();

		String querry = filtro.gerarQuerry(pedido);

		try {
			connection = Conexao.getConnectionPostgres();
			listPedido = new ArrayList<EntidadeDominio>();

			ps = connection.prepareStatement(querry);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Pedido ped = new Pedido();
				Cliente cliente = new Cliente();
				StatusPedido statusPedido = new StatusPedido();
				statusPedido.setNome(rs.getString("stp_nome"));
				statusPedido.setId(Integer.valueOf(rs.getString("stp_id")));
				cliente.setNome(rs.getString("cli_nome"));
				ped.setId(rs.getInt("ped_id"));
				ped.setNumeroPedido(rs.getString("ped_numero"));
				ped.setSolicitacao(rs.getString("ped_solicitacao"));
				ped.setDataCompra(rs.getString("ped_datacompra"));
				ped.setSubtotal(rs.getDouble("ped_subtotal"));
				
				ped.setCliente(cliente);
				ped.setStatusPedido(statusPedido);

				listPedido.add(ped);
			}

			return listPedido;

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
