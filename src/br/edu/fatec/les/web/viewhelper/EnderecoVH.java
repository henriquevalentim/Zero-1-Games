package br.edu.fatec.les.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Resultado;
import br.edu.fatec.les.dominio.cliente.Cidade;
import br.edu.fatec.les.dominio.cliente.Endereco;
import br.edu.fatec.les.dominio.cliente.Estado;
import br.edu.fatec.les.dominio.cliente.Pais;

public class EnderecoVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		//HttpSession session = null;
		Endereco endereco = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("SALVAR")) {
			endereco = criarEndereco(request);

		} else if (operacao.equals("ALTERAR")) {
			endereco = criarEndereco(request);

		} else if (operacao.equals("EXCLUIR")) {
			endereco = criarEndereco(request);

		} else if (operacao.equals("CONSULTAR")) {
			endereco = criarEndereco(request);

		}else if (operacao.equals("PERFIL")) {
			endereco = new Endereco();
			endereco.setNome(request.getParameter("txtNomeEndereco"));
		}
		return endereco;
	}

	private Endereco criarEndereco(HttpServletRequest request) {
		String operacao = request.getParameter("btnOperacao");
		
		Endereco endereco = new Endereco();
		Cidade cidade = new Cidade();
		Estado estado = new Estado();
		Pais pais = new Pais();
		
		endereco.setNome(request.getParameter("txtNomeEndereco"));
		endereco.setCep(request.getParameter("txtCep"));
		endereco.setLogradouro(request.getParameter("txtLogradouro"));
		endereco.setBairro(request.getParameter("txtBairro"));
		endereco.setComplemento(request.getParameter("txtComplemento"));
		endereco.setNumero(request.getParameter("txtNumero"));
		endereco.setTipoLogradouro(request.getParameter("txtTpLogradouro"));
		endereco.setTipoResidencia(request.getParameter("txtTpResidencia"));
		
		
		pais.setNome(request.getParameter("txtPais"));
		estado.setNome(request.getParameter("txtEstado"));
		cidade.setNome(request.getParameter("txtCidade"));
		
		estado.setPais(pais);
		cidade.setEstado(estado);
		endereco.setCidade(cidade);
		
		if(operacao.equals("SALVAR")) {
			endereco.setId(Integer.parseInt(request.getParameter("txtId"))); // salvar endereco
		}else if(operacao.equals("ALTERAR") || operacao.equals("EXCLUIR") || operacao.equals("CONSULTAR")){
			if(request.getParameter("id") != null) {
				endereco.setId(Integer.parseInt(request.getParameter("id"))); //alterando e excluindo
			}
		}
		
		if(operacao.equals("SALVAR") || operacao.equals("ALTERAR")) {
			if(request.getParameter("txtEnderecoCobranca") == null) {
				endereco.setCobranca(false);
			}else if(request.getParameter("txtEnderecoCobranca").equals("COBRANCA")){
				endereco.setCobranca(true);			
			}
			
			if(request.getParameter("txtEnderecoEntrega") == null) {
				endereco.setEntrega(false);
			}else if(request.getParameter("txtEnderecoEntrega").equals("ENTREGA")){
				endereco.setEntrega(true);
			}
		}
		
		return endereco;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("btnOperacao");
		String uri = request.getRequestURI();

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("")) {
			System.out.println("adicionando resultado na sessão");
			if(uri.equals("/zero1games/templates/ControleEndereco")) {
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("CadastrarEndereco.jsp");
			} else if(uri.equals("/zero1games/templates/ControlePagamentoEndereco")) {
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("CadastrarEnderecoEntrega.jsp");
			}

		} else if (operacao.equals("SALVAR")) {
//			resultado.setMsg("Cadastro realizado com sucesso.");
			if(uri.equals("/zero1games/templates/ControleEndereco")) {
				request.setAttribute("ResultadoSalvarEndereco", resultado);
				Resultado result = (Resultado)request.getSession().getAttribute("UsuarioAutenticado");
				d = request.getRequestDispatcher("ControleEnderecoEntrega?btnOperacao=CONSULTAR&id=" + result.getEntidades().get(0).getId());
			}else if(uri.equals("/zero1games/templates/ControlePagamentoEndereco")) {
				Resultado result = (Resultado)request.getSession().getAttribute("UsuarioAutenticado");
				request.getSession().setAttribute("ResultadoSalvarEndereco", resultado);
				d = request.getRequestDispatcher("ControlePagamentoEndereco?btnOperacao=CONSULTAR&id=" + result.getEntidades().get(0).getId());
			}
		} else if (operacao.equals("CONSULTAR")) {
			if(uri.equals("/zero1games/templates/ControleEndereco")) {
//			resultado.setMsg("Consulta realizada com sucesso.");
				resultado.getEntidades().remove(resultado.getEntidades().size()-1);
				request.setAttribute("resultadoEndereco", resultado);
				d = request.getRequestDispatcher("endereco.jsp");
			}else if(uri.equals("/zero1games/templates/ControlePagamentoEndereco")) {
				resultado.getEntidades().remove(resultado.getEntidades().size()-1);
				request.setAttribute("enderecos", resultado.getEntidades());
				d = request.getRequestDispatcher("EscolherEndereco.jsp");
			}else if(uri.equals("/zero1games/templates/ControleEnderecoEntrega")) {
				resultado.getEntidades().remove(resultado.getEntidades().size()-1);
				request.getSession().setAttribute("resultadoEndereco", resultado);
				d = request.getRequestDispatcher("endereco.jsp");
			}

		} else if (operacao.equals("ALTERAR")) {
//			resultado.setMsg("Alteração realizada com sucesso.");
			request.setAttribute("resultado", resultado);
			Resultado result = (Resultado)request.getSession().getAttribute("UsuarioAutenticado");
			d = request.getRequestDispatcher("ControleEndereco?btnOperacao=CONSULTAR&id=" + result.getEntidades().get(0).getId());

		} else if (operacao.equals("EXCLUIR")) {
//			resultado.setMsg("Endereco inativado com sucesso.");
			request.setAttribute("resultado", resultado);
			Resultado result = (Resultado)request.getSession().getAttribute("UsuarioAutenticado");
			d = request.getRequestDispatcher("ControleEndereco?btnOperacao=CONSULTAR&id=" + result.getEntidades().get(0).getId());

		} else if (operacao.equals("PERFIL")) {
			Endereco endereco = (Endereco) resultado.getEntidades().get(0);
			request.setAttribute("EnderecoPerfil", endereco);
			d = request.getRequestDispatcher("EditarEndereco.jsp");
		}

		d.forward(request, response);
	}
}
