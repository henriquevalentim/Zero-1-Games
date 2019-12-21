package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.les.dominio.AnaliseGrafico;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.web.util.Conexao;

public class AnaliseGraficoDAO implements IDAO {
	
	private Connection connection = null;

	@Override
	public void salvar(EntidadeDominio entidade) {
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		StringBuilder sb = new StringBuilder();
		PreparedStatement pst = null;
		ResultSet rs;

		AnaliseGrafico analiseGrafico = (AnaliseGrafico) entidade;
		List<String> listPeriodos = null;
		List<String> listDados = null;
		List<EntidadeDominio> analises = new ArrayList<EntidadeDominio>(); 

		try {
			connection = Conexao.getConnectionPostgres();
			if(analiseGrafico.getTipoAnalise() == 1) {
				sb.append("select count(cli_id),to_char(P.ped_datacompra,'Month'),date_trunc('Month',P.ped_datacompra) ");
				sb.append("from tb_pedido as P,tb_cliente as C where P.ped_cli_id = C.cli_id AND cli_genero = 'M' AND ");
				sb.append("P.ped_datacompra BETWEEN ? AND ? group by ");
				sb.append("to_char(ped_datacompra,'Month'),date_trunc('Month',P.ped_datacompra) order by ");
				sb.append("date_trunc('Month',P.ped_datacompra); ");				
			}else if(analiseGrafico.getTipoAnalise() == 2) {
				sb.append("select count(cli_id),to_char(P.ped_datacompra,'Month'),date_trunc('Month',P.ped_datacompra) ");
				sb.append("from tb_pedido as P,tb_cliente as C where P.ped_cli_id = C.cli_id AND cli_genero = 'F' AND ");
				sb.append("P.ped_datacompra BETWEEN ? AND ? group by ");
				sb.append("to_char(ped_datacompra,'Month'),date_trunc('Month',P.ped_datacompra) order by ");
				sb.append("date_trunc('Month',P.ped_datacompra); ");
			}else if(analiseGrafico.getTipoAnalise() == 3) {
				sb.append("select SUM((J.jog_preco_venda - J.jog_preco_compra) * I.ite_quantidade),J.jog_genero_jogo, ");
				sb.append("to_char(P.ped_datacompra,'Month'),date_trunc('Month',P.ped_datacompra) ");
				sb.append("from tb_pedido as P,tb_item as I,tb_jogo as J where I.ite_ped_id = P.ped_id AND ");
				sb.append("I.ite_jog_id = J.jog_id AND J.jog_genero_jogo='ESPORTE' AND P.ped_datacompra ");
				sb.append("BETWEEN ? AND ? group by J.jog_genero_jogo, ");
				sb.append("to_char(P.ped_datacompra,'Month'),date_trunc('Month',P.ped_datacompra) order by ");
				sb.append("date_trunc('Month',P.ped_datacompra); ");
			}else if(analiseGrafico.getTipoAnalise() == 4) {
				sb.append("select SUM((J.jog_preco_venda - J.jog_preco_compra) * I.ite_quantidade),J.jog_genero_jogo, ");
				sb.append("to_char(P.ped_datacompra,'Month'),date_trunc('Month',P.ped_datacompra) ");
				sb.append("from tb_pedido as P,tb_item as I,tb_jogo as J where I.ite_ped_id = P.ped_id AND ");
				sb.append("I.ite_jog_id = J.jog_id AND J.jog_genero_jogo='RPG' AND P.ped_datacompra ");
				sb.append("BETWEEN ? AND ? group by J.jog_genero_jogo, ");
				sb.append("to_char(P.ped_datacompra,'Month'),date_trunc('Month',P.ped_datacompra) order by ");
				sb.append("date_trunc('Month',P.ped_datacompra); ");
			}else if(analiseGrafico.getTipoAnalise() == 5) {
				sb.append("select SUM((J.jog_preco_venda - J.jog_preco_compra) * I.ite_quantidade),J.jog_genero_jogo, ");
				sb.append("to_char(P.ped_datacompra,'Month'),date_trunc('Month',P.ped_datacompra) ");
				sb.append("from tb_pedido as P,tb_item as I,tb_jogo as J where I.ite_ped_id = P.ped_id AND ");
				sb.append("I.ite_jog_id = J.jog_id AND J.jog_genero_jogo='LUTA' AND P.ped_datacompra ");
				sb.append("BETWEEN ? AND ? group by J.jog_genero_jogo, ");
				sb.append("to_char(P.ped_datacompra,'Month'),date_trunc('Month',P.ped_datacompra) order by ");
				sb.append("date_trunc('Month',P.ped_datacompra); ");
			}else if(analiseGrafico.getTipoAnalise() == 6) {
				sb.append("select SUM((J.jog_preco_venda - J.jog_preco_compra) * I.ite_quantidade),J.jog_genero_jogo, ");
				sb.append("to_char(P.ped_datacompra,'Month'),date_trunc('Month',P.ped_datacompra) ");
				sb.append("from tb_pedido as P,tb_item as I,tb_jogo as J where I.ite_ped_id = P.ped_id AND ");
				sb.append("I.ite_jog_id = J.jog_id AND J.jog_genero_jogo='TIRO' AND P.ped_datacompra ");
				sb.append("BETWEEN ? AND ? group by J.jog_genero_jogo, ");
				sb.append("to_char(P.ped_datacompra,'Month'),date_trunc('Month',P.ped_datacompra) order by ");
				sb.append("date_trunc('Month',P.ped_datacompra); ");
			}

			System.out.println(analiseGrafico.getDataInicio().toString());
			pst = connection.prepareStatement(sb.toString());
			pst.setDate(1, Date.valueOf(analiseGrafico.getDataInicio()));
			pst.setDate(2, Date.valueOf(analiseGrafico.getDataFim().toString()));
			
			rs = pst.executeQuery();
			listPeriodos = new ArrayList<String>();
			listDados = new ArrayList<String>();

			while (rs.next()) {
				if(analiseGrafico.getTipoAnalise() <= 2) {					
					listPeriodos.add(rs.getString("count"));
					listDados.add(rs.getString("to_char"));
				}else {
					listPeriodos.add(rs.getString("sum"));
					listDados.add(rs.getString("to_char"));
				}
			}
			
			analiseGrafico.setDados(listDados);
			analiseGrafico.setPeriodos(listPeriodos);
			
			analises.add(analiseGrafico);

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

		return analises;
	}

}
