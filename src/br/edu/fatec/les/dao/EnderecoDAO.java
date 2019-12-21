package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.cliente.Cidade;
import br.edu.fatec.les.dominio.cliente.Endereco;
import br.edu.fatec.les.dominio.cliente.Estado;
import br.edu.fatec.les.dominio.cliente.Pais;
import br.edu.fatec.les.web.util.Conexao;

public class EnderecoDAO implements IDAO {
	
	private Connection connection = null;

	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Endereco endereco = (Endereco) entidade;

		try {
			connection = Conexao.getConnectionPostgres();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_endereco(end_nome, end_cep, end_logradouro, end_bairro, end_complemento,end_numero,");
			sql.append("end_tiporesidencia, end_tipologradouro, end_entrega, end_cobranca,end_cidade, end_estado, end_pais, end_cli_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString());

			pst.setString(1, endereco.getNome());
			pst.setString(2, endereco.getCep());
			pst.setString(3, endereco.getLogradouro());
			pst.setString(4, endereco.getBairro());
			pst.setString(5, endereco.getComplemento());
			pst.setString(6, endereco.getNumero());
			pst.setString(7, endereco.getTipoResidencia());
			pst.setString(8, endereco.getTipoLogradouro());
			pst.setBoolean(9, endereco.getEntrega());
			pst.setBoolean(10, endereco.getCobranca());
			pst.setString(11, endereco.getCidade().getNome());
			pst.setString(12, endereco.getCidade().getEstado().getNome());
			pst.setString(13, endereco.getCidade().getEstado().getPais().getNome());
			pst.setInt(14, endereco.getId());

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
        Endereco endereco = (Endereco) entidade;

        try {
            connection = Conexao.getConnectionPostgres();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_endereco SET end_nome=?, end_cep=?, end_logradouro=?, end_bairro=?, ");
            sql.append("end_complemento=?, end_numero=?, end_tiporesidencia=?, end_tipologradouro=?, ");
            sql.append("end_entrega=?, end_cobranca=?,end_cidade=?, end_estado=?, end_pais=? WHERE end_id = ?; ");
            
            
            pst = connection.prepareStatement(sql.toString());

            pst.setString(1, endereco.getNome());
            pst.setString(2, endereco.getCep());
            pst.setString(3, endereco.getLogradouro());
            pst.setString(4, endereco.getBairro());
            pst.setString(5, endereco.getComplemento());
            pst.setString(6, endereco.getNumero());
            pst.setString(7, endereco.getTipoResidencia());
            pst.setString(8, endereco.getTipoLogradouro());
			pst.setBoolean(9, endereco.getEntrega());
			pst.setBoolean(10, endereco.getCobranca());
            pst.setString(11, endereco.getCidade().getNome());
			pst.setString(12, endereco.getCidade().getEstado().getNome());
			pst.setString(13, endereco.getCidade().getEstado().getPais().getNome());
			pst.setInt(14, endereco.getId());

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }


	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Endereco endereco = (Endereco) entidade;

		try {
			connection = Conexao.getConnectionPostgres();

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE from tb_endereco WHERE end_id = ?");

			pst = connection.prepareStatement(sql.toString());

			pst.setInt(1, endereco.getId());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		ResultSet rs;

		Endereco endereco = (Endereco) entidade;
		List<EntidadeDominio> listEndereco = null;
		Boolean flgIf = false;
		String coluna = null;

		try {
			connection = Conexao.getConnectionPostgres();
			
			//System.out.println("EnderecoDAO CONSULTAR:" + endereco.getNome());
			
			if(endereco.getId() != null) {
				flgIf = true;
				coluna = "end_cli_id";
			}else {
				flgIf = false;
				coluna = "end_nome";
			}
			
			pst = connection.prepareStatement("SELECT * FROM tb_endereco where " + coluna +" = ?");
			if(!flgIf) {
				pst.setString(1, endereco.getNome());				
			}else {
				pst.setInt(1, endereco.getId());
			}
			
			rs = pst.executeQuery();
			listEndereco = new ArrayList<EntidadeDominio>();
			Endereco end = null;
			Cidade cid = null;
			Estado est = null;
			Pais pais = null;
			
			while (rs.next()) {
				end = new Endereco();
				cid = new Cidade();
				est = new Estado();
				pais = new Pais();
				
				end.setId(rs.getInt("end_id"));
				end.setNome(rs.getString("end_nome"));
				end.setCep(rs.getString("end_cep"));
				end.setNumero(rs.getString("end_numero"));
				end.setLogradouro(rs.getString("end_logradouro"));
				end.setBairro(rs.getString("end_bairro"));
				end.setComplemento(rs.getString("end_complemento"));
				end.setTipoLogradouro(rs.getString("end_tipologradouro"));
				end.setTipoResidencia(rs.getString("end_tiporesidencia"));
				end.setEntrega(rs.getBoolean("end_entrega"));
				end.setCobranca(rs.getBoolean("end_cobranca"));
				
				cid.setNome(rs.getString("end_cidade"));
				est.setNome(rs.getString("end_estado"));
				pais.setNome(rs.getString("end_pais"));

				est.setPais(pais);
				cid.setEstado(est);
				end.setCidade(cid);
				
				listEndereco.add(end);

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

		return listEndereco;
	}

}
