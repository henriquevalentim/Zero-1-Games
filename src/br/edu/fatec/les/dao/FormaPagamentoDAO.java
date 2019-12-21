package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.FormaPagamento;
import br.edu.fatec.les.web.util.Conexao;

public class FormaPagamentoDAO implements IDAO {

	private Connection connection = null;
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		
		PreparedStatement pst = null;
		FormaPagamento formaPagamento = (FormaPagamento) entidade;

		try {
			connection = Conexao.getConnectionPostgres();
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_forma_pagamento(frp_car_id, frp_valor, frp_cup_id,frp_ped_id)");
			sql.append(" VALUES (?,?,?,?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			if(formaPagamento.getCartao() != null) {
				pst.setInt(1, formaPagamento.getCartao().getId());
			}else {
				pst.setInt(1, 0);
			}
			pst.setDouble(2, formaPagamento.getValor());
			if(formaPagamento.getCupom() != null) {
				pst.setInt(3, formaPagamento.getCupom().getId());
			}else {
				pst.setInt(3, 0);
			}
			pst.setInt(4, formaPagamento.getId());

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
