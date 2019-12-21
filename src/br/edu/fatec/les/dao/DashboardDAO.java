package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.les.dominio.Dashboard;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.web.util.Conexao;

public class DashboardDAO implements IDAO {
	
	private Connection connection;
	
	public DashboardDAO(){}
	
	public DashboardDAO(Connection connection){
		this.connection = connection;
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) {

	}

	@Override
	public void excluir(EntidadeDominio entidade) {

	}

	@SuppressWarnings("resource")
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		ResultSet rs;
		
		List<EntidadeDominio> list = null;
		
		try {
		connection = Conexao.getConnectionPostgres();
		list = new ArrayList<EntidadeDominio>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from tb_cliente;");
		
		pst = connection.prepareStatement(sql.toString());
		
		rs = pst.executeQuery();
		
		Dashboard dashboardDados = null;
	
		while(rs.next()){
			dashboardDados = new Dashboard();
			dashboardDados.setQtdClientes(Integer.parseInt(rs.getString("count")));
			
		}
		
		sql.setLength(0);
		sql.append("select sum(jog_quantidade) from tb_jogo;");
		
		pst = connection.prepareStatement(sql.toString());
		
		rs = pst.executeQuery();
		
		while(rs.next()){
			dashboardDados.setEstoqueTotal(Integer.parseInt(rs.getString("sum")));
		}
		
		sql.setLength(0);
		sql.append("select count (*) from tb_jogo where jog_quantidade = 0;");
		
		pst = connection.prepareStatement(sql.toString());
		
		rs = pst.executeQuery();
		
		while(rs.next()){
			dashboardDados.setProdutosSemEstoque((Integer.parseInt(rs.getString("count"))));
		}
		
		sql.setLength(0);
		sql.append("select sum(jog_quantidade) from tb_jogo;");
		
		pst = connection.prepareStatement(sql.toString());
		
		rs = pst.executeQuery();
		
		while(rs.next()){
			dashboardDados.setEstoqueTotal(Integer.parseInt(rs.getString("sum")));
		}
		
		sql.setLength(0);
		sql.append("select sum(ped_subtotal) from tb_pedido;");
		
		pst = connection.prepareStatement(sql.toString());
		
		rs = pst.executeQuery();
		
		while(rs.next()){
			dashboardDados.setFaturamentoTotal((Double.parseDouble(rs.getString("sum"))));
		}
		
		
		list.add(dashboardDados);
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					pst.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

}
