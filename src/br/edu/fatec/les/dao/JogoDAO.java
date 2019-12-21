package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Motivo;
import br.edu.fatec.les.dominio.jogo.Desenvolvedor;
import br.edu.fatec.les.dominio.jogo.Distribuidora;
import br.edu.fatec.les.dominio.jogo.GrupoPrecificacao;
import br.edu.fatec.les.dominio.jogo.Jogo;
import br.edu.fatec.les.web.util.Conexao;

public class JogoDAO implements IDAO {

	private Connection connection = null;

	@Override
	public void salvar(EntidadeDominio entidade) {

		PreparedStatement pst = null;
		Jogo jogo = (Jogo) entidade;

		try {
			connection = Conexao.getConnectionPostgres();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO public.tb_jogo(jog_codigo, jog_titulo, jog_plataforma, jog_categoria, ");
			sql.append("jog_genero_jogo, jog_classificacao_indicativa, jog_numero_jogadores_online, jog_numero_jogadores_offline,");
			sql.append("jog_preco_compra, jog_preco_venda, jog_status,jog_quantidade, jog_descricao, jog_url_foto, jog_desenvolvedor,");
			sql.append(" jog_distribuidora, jog_grupo_precificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);");

			pst = connection.prepareStatement(sql.toString());

			pst.setString(1, jogo.getCodigo());
			pst.setString(2, jogo.getTitulo());
			pst.setString(3, jogo.getPlataforma());
			pst.setString(4, jogo.getCategoria());
			pst.setString(5, jogo.getGeneroJogo());
			pst.setString(6, jogo.getClassificacaoIndicativa());
			pst.setInt(7, jogo.getNumeroJogadoresOnline());
			pst.setInt(8, jogo.getNumeroJogadoresOffline());
			pst.setDouble(9, jogo.getPrecoCompra());
			pst.setDouble(10, jogo.getPrecoVenda());
			pst.setBoolean(11, true);
			pst.setInt(12, jogo.getQuantidade());
			pst.setString(13, jogo.getDescricao());
			pst.setString(14, jogo.getUrlFoto());
			pst.setString(15, jogo.getDesenvolvedor().getNome());
			pst.setString(16, jogo.getDistribuidora().getNome());
			pst.setString(17, jogo.getGrupoPrecificacao().getNome());

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
		Jogo jogo = (Jogo) entidade;

		try {
			connection = Conexao.getConnectionPostgres();
			StringBuilder sql = new StringBuilder();
			
			if(jogo.getMotivo().equals(Motivo.JOGO_ATIVADO_PELO_ADMIN.toString())) {
				sql.append("UPDATE tb_jogo SET jog_status = true where jog_id = ? ");
				pst = connection.prepareStatement(sql.toString());

				pst.setInt(1, jogo.getId());
			}else {
				
				sql.append("UPDATE tb_jogo SET jog_codigo=?, jog_titulo=?, jog_plataforma=?, jog_categoria=?, ");
				sql.append("jog_genero_jogo=?, jog_classificacao_indicativa=?, ");
				sql.append("jog_numero_jogadores_online=?, jog_numero_jogadores_offline=?, ");
				sql.append("jog_preco_compra=?,jog_quantidade=?, jog_descricao=?, jog_url_foto=?, ");
				sql.append("jog_desenvolvedor=?, jog_distribuidora=?, jog_grupo_precificacao=? WHERE jog_id = ?;");
	
				pst = connection.prepareStatement(sql.toString());
	
				pst.setString(1, jogo.getCodigo());
				pst.setString(2, jogo.getTitulo());
				pst.setString(3, jogo.getPlataforma());
				pst.setString(4, jogo.getCategoria());
				pst.setString(5, jogo.getGeneroJogo());
				pst.setString(6, jogo.getClassificacaoIndicativa());
				pst.setInt(7, jogo.getNumeroJogadoresOnline());
				pst.setInt(8, jogo.getNumeroJogadoresOffline());
				pst.setDouble(9, jogo.getPrecoCompra());
				pst.setInt(10, jogo.getQuantidade());
				pst.setString(11, jogo.getDescricao());
				pst.setString(12, jogo.getUrlFoto());
				pst.setString(13, jogo.getDesenvolvedor().getNome());
				pst.setString(14, jogo.getDistribuidora().getNome());
				pst.setString(15, jogo.getGrupoPrecificacao().getNome());
				pst.setInt(16, jogo.getId());
			}
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Jogo jogo = (Jogo) entidade;

		try {
			connection = Conexao.getConnectionPostgres();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_jogo SET jog_status = false, jog_motivo = ? WHERE jog_id = ?");

			pst = connection.prepareStatement(sql.toString());

			pst.setString(1, jogo.getMotivo());
			pst.setInt(2, jogo.getId());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Jogo jogo = (Jogo) entidade;
		PreparedStatement ps = null;

		List<EntidadeDominio> listJogos = null;

		FiltroJogo filtro = new FiltroJogo();

		String querry = filtro.gerarQuerry(jogo);

		try {
			connection = Conexao.getConnectionPostgres();
			listJogos = new ArrayList<EntidadeDominio>();

			ps = connection.prepareStatement(querry);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Desenvolvedor desenvolvedor = new Desenvolvedor();
				Distribuidora distribuidora = new Distribuidora();
				GrupoPrecificacao grupoPrecificacao = new GrupoPrecificacao();
				Jogo jog = new Jogo();

				jog.setId(rs.getInt("jog_id"));
				jog.setCodigo(rs.getString("jog_codigo"));
				jog.setTitulo(rs.getString("jog_titulo"));
				jog.setPlataforma(rs.getString("jog_plataforma"));
				jog.setCategoria(rs.getString("jog_categoria"));
				jog.setGeneroJogo(rs.getString("jog_genero_jogo"));
				jog.setClassificacaoIndicativa(rs.getString("jog_classificacao_indicativa"));
				jog.setNumeroJogadoresOnline(rs.getInt("jog_numero_jogadores_online"));
				jog.setNumeroJogadoresOffline(rs.getInt("jog_numero_jogadores_offline"));
				jog.setPrecoCompra(rs.getDouble("jog_preco_compra"));
				jog.setPrecoVenda(rs.getDouble("jog_preco_venda"));
				jog.setQuantidade(rs.getInt("jog_quantidade"));
				jog.setDescricao(rs.getString("jog_descricao"));
				jog.setStatus(rs.getBoolean("jog_status"));
				jog.setUrlFoto(rs.getString("jog_url_foto"));

				desenvolvedor.setNome(rs.getString("jog_desenvolvedor"));

				distribuidora.setNome(rs.getString("jog_distribuidora"));

				grupoPrecificacao.setNome(rs.getString("jog_grupo_precificacao"));

				jog.setDesenvolvedor(desenvolvedor);
				jog.setDistribuidora(distribuidora);
				jog.setGrupoPrecificacao(grupoPrecificacao);

				listJogos.add(jog);
			}

			return listJogos;

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
