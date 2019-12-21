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
import br.edu.fatec.les.dominio.cliente.Telefone;
import br.edu.fatec.les.web.util.Conexao;

public class ClienteDAO implements IDAO {

	private Connection connection = null;
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		
		PreparedStatement pst = null;
		Cliente cliente = (Cliente) entidade;

		try {
			connection = Conexao.getConnectionPostgres();
			connection.setAutoCommit(false);
			
			Usuario usuario = cliente.getUsuario();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.salvar(usuario);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_cliente(cli_nome, cli_cpf, cli_email, cli_senha, cli_genero, cli_datanascimento,");
			sql.append("cli_usu_id, cli_status) VALUES (?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			pst.setString(3, cliente.getEmail());
			pst.setString(4, cliente.getSenha().getSenha());
			pst.setString(5, cliente.getGenero());
			pst.setObject(6,cliente.getDataNascimento());
			pst.setInt(7, cliente.getUsuario().getId());
			pst.setBoolean(8, true);

			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int idCliente=0;
			if(rs.next())
				idCliente = rs.getInt(1);
			cliente.getEndereco().setId(idCliente);
			cliente.getTelefone().setId(idCliente);
			
			cliente.setId(idCliente);

			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecoDAO.salvar(cliente.getEndereco());
			
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.salvar(cliente.getTelefone());
			
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
        Cliente cliente = (Cliente) entidade;

        try {
            connection = Conexao.getConnectionPostgres();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_cliente SET cli_nome=?, cli_cpf=?, cli_email=?, cli_genero=?, ");
            sql.append("cli_datanascimento=? WHERE cli_id = ?;");

            pst = connection.prepareStatement(sql.toString());

            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getEmail());
            pst.setString(4, cliente.getGenero());
            pst.setObject(5, cliente.getDataNascimento());
            pst.setInt(6, cliente.getId());

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Cliente cliente = (Cliente) entidade;

		try {
			connection = Conexao.getConnectionPostgres();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_cliente SET cli_status = false WHERE cli_id = ?");

			pst = connection.prepareStatement(sql.toString());

			pst.setInt(1, cliente.getId());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		PreparedStatement ps = null;

		List<EntidadeDominio> listClientes = null;

		FiltroCliente filtro = new FiltroCliente();

		String querry = filtro.gerarQuerry(cliente);

		try {
			connection = Conexao.getConnectionPostgres();
			listClientes = new ArrayList<EntidadeDominio>();

			ps = connection.prepareStatement(querry);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente cli = new Cliente();
				Telefone telefone = new Telefone();

				cli.setId(rs.getInt("cli_id"));
				cli.setNome(rs.getString("cli_nome"));
				cli.setEmail(rs.getString("cli_email"));
				cli.setCpf(rs.getString("cli_cpf"));
				
				String data = rs.getString("cli_datanascimento");
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			    LocalDate date = LocalDate.parse(data,formatter);
				cli.setDataNascimento(date);
				
				telefone.setNumero(rs.getString("tel_numero"));
				cli.setTelefone(telefone);

				listClientes.add(cli);
			}

			return listClientes;

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
