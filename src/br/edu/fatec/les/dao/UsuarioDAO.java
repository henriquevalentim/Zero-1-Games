package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Usuario;
import br.edu.fatec.les.dominio.cliente.Cliente;
import br.edu.fatec.les.dominio.cliente.Senha;
import br.edu.fatec.les.dominio.cliente.Telefone;
import br.edu.fatec.les.web.util.Conexao;

public class UsuarioDAO implements IDAO {
	
	private Connection connection;
	private boolean ctrlTransaction = true;
	
	public UsuarioDAO(){}
	
	public UsuarioDAO(Connection connection){
		this.connection = connection;
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement pst=null;
		Usuario usuario = (Usuario)entidade;
		
		try {
			if(connection == null){
				connection = Conexao.getConnectionPostgres();
			}else{
				ctrlTransaction = false;
			}
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_usuario(usu_login,usu_senha) VALUES (?,?) ");
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, usuario.getLogin());
			pst.setString(2, usuario.getSenha().getSenha());
			
			pst.executeUpdate();		
					
			ResultSet rs = pst.getGeneratedKeys();
			int idUsuario=0;
			if(rs.next())
				idUsuario = rs.getInt(1);
			usuario.setId(idUsuario);
			
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
        Usuario usuario = (Usuario) entidade;

        try {
            connection = Conexao.getConnectionPostgres();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_usuario SET usu_senha=? WHERE usu_id = ?;");

            pst = connection.prepareStatement(sql.toString());
            System.out.println(usuario.getSenha().getConfirmaSenha());
            System.out.println(usuario.getSenha().getSenhaTemp());
            pst.setString(1, usuario.getSenha().getSenha());
            pst.setInt(2, usuario.getId());

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
		
		Usuario usuario = (Usuario)entidade;
		List<EntidadeDominio> list = null;
		
		try {
		connection = Conexao.getConnectionPostgres();
		list = new ArrayList<EntidadeDominio>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tb_usuario as U, tb_cliente as C, tb_telefone as T ");
		sql.append("where U.usu_login = ? AND U.usu_senha = ? AND C.cli_usu_id = U.usu_id and C.cli_id = T.tel_cli_id;");
		
		pst = connection.prepareStatement(sql.toString());
		pst.setString(1, usuario.getLogin());
		pst.setString(2, usuario.getSenha().getSenha());
		
		rs = pst.executeQuery();
		
		Usuario usuarioAutenticado = null;
		Cliente cliente = null;
		while(rs.next()){
			usuarioAutenticado = new Usuario();
			cliente = new Cliente();
			Telefone telefone = new Telefone();
			Senha senha = new Senha();
			usuarioAutenticado.setId(Integer.parseInt(rs.getString("usu_id")));
			usuarioAutenticado.setLogin(rs.getString("usu_login"));
			senha.setSenha(rs.getString("usu_senha"));
			usuarioAutenticado.setSenha(senha);
			
			cliente.setUsuario(usuarioAutenticado);
			cliente.setNome(rs.getString("cli_nome"));
			cliente.setEmail(rs.getString("cli_email"));
			cliente.setCpf(rs.getString("cli_cpf"));
			
			String data = rs.getString("cli_datanascimento");
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate date = LocalDate.parse(data,formatter);
			
			cliente.setDataNascimento(date);
			cliente.setGenero(rs.getString("cli_genero"));
			cliente.setId(rs.getInt("cli_id"));
			
			telefone.setId(rs.getInt("tel_id"));
			telefone.setDdd(rs.getString("tel_ddd"));
			telefone.setNumero(rs.getString("tel_numero"));
			cliente.setTelefone(telefone);
			
			list.add(cliente);
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
