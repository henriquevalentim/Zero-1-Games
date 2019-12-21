package br.edu.fatec.les.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Resultado;
import br.edu.fatec.les.dominio.cliente.Bandeira;
import br.edu.fatec.les.dominio.cliente.Cartao;

public class CartaoVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		//HttpSession session = null;
		Cartao cartao = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("SALVAR")) {
			cartao = criarCartao(request);

		} else if (operacao.equals("ALTERAR")) {
			cartao = criarCartao(request);

		} else if (operacao.equals("EXCLUIR")) {
			cartao = criarCartao(request);

		} else if (operacao.equals("CONSULTAR")) {
			cartao = criarCartao(request);
		}
		
		return cartao;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		RequestDispatcher d = null;
		String operacao = request.getParameter("btnOperacao");
		String uri = request.getRequestURI();

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("")) {
			if(uri.equals("/zero1games/templates/ControleCartao")) {
				System.out.println("adicionando resultado na sessão");
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("CadastrarCartao.jsp");
			}else if(uri.equals("/zero1games/templates/ControlePagamentoCartao")) {
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("CadastrarCartaoPagamento.jsp");
			}

		} else if (operacao.equals("SALVAR")) {
			if(uri.equals("/zero1games/templates/ControleCartao")) {
				resultado.setMsg("Cadastro realizado com sucesso.");
				request.setAttribute("resultado", resultado);
				Resultado result = (Resultado)request.getSession().getAttribute("UsuarioAutenticado");
				d = request.getRequestDispatcher("ControleCartao?btnOperacao=CONSULTAR&id=" + result.getEntidades().get(0).getId());
			}else if(uri.equals("/zero1games/templates/ControlePagamentoCartao")) {
				request.setAttribute("resultado", resultado);
				Resultado result = (Resultado)request.getSession().getAttribute("UsuarioAutenticado");
				d = request.getRequestDispatcher("ControlePagamentoCartao?btnOperacao=CONSULTAR&id=" + result.getEntidades().get(0).getId());
			}

		} else if (operacao.equals("CONSULTAR")) {
			if(uri.equals("/zero1games/templates/ControleCartao")) {
				resultado.setMsg("Consulta realizada com sucesso.");
				resultado.getEntidades().remove(resultado.getEntidades().size()-1);
				request.setAttribute("resultadoConsultarCartao", resultado);
				d = request.getRequestDispatcher("cartao.jsp");
			}else if(uri.equals("/zero1games/templates/ControlePagamentoCartao")) {
				resultado.getEntidades().remove(resultado.getEntidades().size()-1);
				request.setAttribute("cartoes", resultado.getEntidades());
				d = request.getRequestDispatcher("EscolherPagamento.jsp");
			}
			
		} else if (operacao.equals("EXCLUIR")) {
			resultado.setMsg("Cartao excluido com sucesso.");
			request.setAttribute("resultado", resultado);
			Resultado result = (Resultado)request.getSession().getAttribute("UsuarioAutenticado");
			d = request.getRequestDispatcher("ControleCartao?btnOperacao=CONSULTAR&id=" + result.getEntidades().get(0).getId());
		}
		
		d.forward(request, response);
		
	}
	
	private Cartao criarCartao(HttpServletRequest request) {
		
		Cartao cartao = new Cartao();
		Bandeira bandeira = new Bandeira();
		cartao.setNome(request.getParameter("txtNomeCartao"));
		cartao.setNumero(request.getParameter("txtNumeroCartao"));
		if(request.getParameter("txtBandeiraId") != null) {			
			if(!request.getParameter("txtBandeiraId").trim().equals("")) {			
				bandeira.setId(Integer.valueOf(request.getParameter("txtBandeiraId")));
				cartao.setBandeira(bandeira);
			}
		}
		cartao.setCodigoSeguranca(request.getParameter("txtCodigoCartao"));
		
		if(request.getParameter("btnOperacao").equals("SALVAR")) {
			cartao.setId(Integer.parseInt(request.getParameter("txtIdCliente")));
		}else {
			cartao.setId(Integer.parseInt(request.getParameter("id")));
		}
		
		return cartao;
	}

}
