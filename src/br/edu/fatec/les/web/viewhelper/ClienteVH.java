package br.edu.fatec.les.web.viewhelper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Resultado;
import br.edu.fatec.les.dominio.Usuario;
import br.edu.fatec.les.dominio.cliente.Cidade;
import br.edu.fatec.les.dominio.cliente.Cliente;
import br.edu.fatec.les.dominio.cliente.Endereco;
import br.edu.fatec.les.dominio.cliente.Estado;
import br.edu.fatec.les.dominio.cliente.Pais;
import br.edu.fatec.les.dominio.cliente.Senha;
import br.edu.fatec.les.dominio.cliente.Telefone;

public class ClienteVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {

		Cliente cliente = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("SALVAR")) {
			cliente = criarCliente(request);

		} else if (operacao.equals("ALTERAR")) {
			cliente = criarCliente(request);

		} else if (operacao.equals("EXCLUIR")) {
			cliente = new Cliente();
			cliente.setId(Integer.parseInt(request.getParameter("idCliente")));

		} else if (operacao.equals("CONSULTAR")) {
			cliente = criarCliente(request);

		}
		return cliente;
	}

	private Cliente criarCliente(HttpServletRequest request) {

		Cliente cliente = new Cliente();
		Senha senha = new Senha();
		Telefone telefone = new Telefone();
		Endereco endereco = new Endereco();
		Cidade cidade = new Cidade();
		Estado estado = new Estado();
		Pais pais = new Pais();
		Usuario usuario = new Usuario();

		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("SALVAR")) {
			endereco.setNome(request.getParameter("txtNomeEndereco"));
			endereco.setCep(request.getParameter("txtCep").replace("-", ""));
			endereco.setLogradouro(request.getParameter("txtLogradouro"));
			endereco.setBairro(request.getParameter("txtBairro"));
			endereco.setComplemento(request.getParameter("txtComplemento"));
			endereco.setNumero(request.getParameter("txtNumero"));
			endereco.setTipoLogradouro(request.getParameter("txtTpLogradouro"));
			endereco.setTipoResidencia(request.getParameter("txtTpResidencia"));

			pais.setNome(request.getParameter("txtPais"));
			estado.setNome(request.getParameter("txtEstado"));
			cidade.setNome(request.getParameter("txtCidade"));
			
			if(request.getParameter("txtEnderecoCobranca").equals("COBRANCA")) {
				endereco.setCobranca(true);			
			}else {
				endereco.setCobranca(false);
			}
			
			if(request.getParameter("txtEnderecoEntrega").equals("ENTREGA")) {
				endereco.setEntrega(true);
			}else {
				endereco.setEntrega(false);
			}

			estado.setPais(pais);
			cidade.setEstado(estado);
			endereco.setCidade(cidade);
			
			usuario.setLogin(request.getParameter("txtEmail"));
			Senha senha1 = new Senha();
			senha1.setSenha(request.getParameter("txtSenha"));
			usuario.setSenha(senha1);
			
			String numeroCompleto = request.getParameter("txtNumeroTelefone").replace("(", "").replace(")", "")
					.replace("-", "").replace(" ", "").trim();
			String ddd = "";
			String numero = "";
			if(numeroCompleto.length() > 3) {					
				ddd = numeroCompleto.substring(0, 2);
				numero = numeroCompleto.substring(2);
			}

			if (numero.length() == 9) {
				telefone.setTipoTelefone("Celular");
			} else if (numero.length() == 8) {
				telefone.setTipoTelefone("Telefone fixo");
			}

			telefone.setDdd(ddd);
			telefone.setNumero(numero);
			
			cliente.setTelefone(telefone);
			
			cliente.setUsuario(usuario);
			cliente.setTelefone(telefone);
			cliente.setEndereco(endereco);

			if (request.getParameter("txtSenha") == null) {
				senha.setSenha("");
				cliente.setSenha(senha);
			} else {
				senha.setSenha(request.getParameter("txtSenha"));
				cliente.setSenha(senha);
			}
		}

		if (operacao.equals("CONSULTAR") || operacao.equals("ALTERAR")) {
			if(request.getParameter("txtTelefone") != null) {
				Telefone tel = new Telefone();
				tel.setNumero(request.getParameter("txtTelefone"));
				cliente.setTelefone(tel);
			}
		}
		
		if(operacao.equals("ALTERAR")) {
			senha.setSenha(request.getParameter("txtSenha"));
			cliente.setSenha(senha);
			cliente.setId(Integer.parseInt(request.getParameter("txtId")));
		}

		cliente.setGenero(request.getParameter("txtGenero"));
		
		cliente.setNome(request.getParameter("txtNome"));
		cliente.setEmail(request.getParameter("txtEmail"));
		cliente.setCpf(request.getParameter("txtCpf").replace(".", "").replace("-", ""));

		String data = request.getParameter("txtDtNascimento");
		if(request.getParameter("txtDtNascimento") != null && !request.getParameter("txtDtNascimento").equals("") &&
				request.getParameter("txtDtNascimento").contains("/")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(data,formatter);
			cliente.setDataNascimento(date);		
		}else if(request.getParameter("txtDtNascimento") != null && !request.getParameter("txtDtNascimento").equals("") &&
				request.getParameter("txtDtNascimento").contains("-")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(data,formatter);
			cliente.setDataNascimento(date);
		}
	    
		return cliente;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("btnOperacao");

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("") && operacao.equals("SALVAR")) {
			System.out.println("adicionando resultado na sess√£o");
			request.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("CadastroCliente.jsp");
		
		}else if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("") && operacao.equals("ALTERAR")) {
				System.out.println("adicionando resultado na sess√£o");
				request.setAttribute("resultadoErroAlterar", resultado);
				d = request.getRequestDispatcher("AlterarCliente.jsp");

		} else if (operacao.equals("SALVAR")) {
			resultado.setMsg("Cadastro realizado com sucesso.");
			request.setAttribute("resultadoCliente", resultado);
			d = request.getRequestDispatcher("login.jsp");

		} else if (operacao.equals("CONSULTAR")) {
			resultado.setMsg("Consulta realizada com sucesso.");
			request.setAttribute("cliente",
					resultado.getEntidades().get(resultado.getEntidades().size() - 1));
			resultado.getEntidades().remove(resultado.getEntidades().size() - 1);

			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("PesquisaCliente.jsp");

		} else if (operacao.equals("ALTERAR")) {
			resultado.setMsg("AlteraÁ„o realizada com sucesso.");
			request.setAttribute("UsuarioAutenticado", resultado);
			d = request.getRequestDispatcher("AlterarCliente.jsp");

		} else if (operacao.equals("EXCLUIR")) {
			resultado.setMsg("Cliente inativado com sucesso.");
			request.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("PesquisaCliente.jsp");

		}

		d.forward(request, response);

	}

}
