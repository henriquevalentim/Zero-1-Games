package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.les.dominio.Cupom;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.web.util.Conexao;

public class CupomDAO implements IDAO {
	
	private Connection connection;
	private boolean ctrlTransaction = true;
	
	public CupomDAO(){}
	
	public CupomDAO(Connection connection){
		this.connection = connection;
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement pst=null;
		Cupom cupom = (Cupom)entidade;
		
		try {
			if(connection == null){
				connection = Conexao.getConnectionPostgres();
			}else{
				ctrlTransaction = false;
			}
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_cupom(cup_valor, cup_status, cup_codigo) VALUES (?,?,?) ");
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setDouble(1, cupom.getValor());
			pst.setBoolean(2, cupom.isStatus());
			pst.setString(3, cupom.getCodigo());
			
			pst.executeUpdate();		
					
			ResultSet rs = pst.getGeneratedKeys();
			int idCupom=0;
			if(rs.next())
				idCupom = rs.getInt(1);
			cupom.setId(idCupom);
			
			connection.commit();					
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();	
		}finally{
			if(ctrlTransaction){
				try {
					pst.close();
					if(ctrlTransaction)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
        Cupom cupom = (Cupom) entidade;

        try {
            connection = Conexao.getConnectionPostgres();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_cupom	SET cup_status=FALSE WHERE cup_codigo=?; ");
            
            pst = connection.prepareStatement(sql.toString());

            pst.setString(1, cupom.getCodigo());
            
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
		PreparedStatement pst = null;
		ResultSet rs;
		
		Cupom cupom = (Cupom)entidade;
		List<EntidadeDominio> list = null;
		
		try {
		connection = Conexao.getConnectionPostgres();
		list = new ArrayList<EntidadeDominio>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tb_cupom ");
		sql.append("where cup_codigo = ? AND cup_status = true;");
		
		pst = connection.prepareStatement(sql.toString());
		pst.setString(1, cupom.getCodigo());
		
		rs = pst.executeQuery();
		
		Cupom cupom1 = null;
		while(rs.next()){
			cupom1 = new Cupom();
			
			cupom1.setId(Integer.parseInt(rs.getString("cup_id")));
			cupom1.setValor(Double.parseDouble(rs.getString("cup_valor")));
			cupom1.setStatus(Boolean.parseBoolean(rs.getString("cup_status")));
			cupom1.setCodigo(rs.getString("cup_codigo"));
			
			list.add(cupom1);
		}
		
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
