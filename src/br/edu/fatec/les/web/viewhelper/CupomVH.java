package br.edu.fatec.les.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.les.dominio.Cupom;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Resultado;
import br.edu.fatec.les.dominio.cliente.Cartao;

public class CupomVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		//HttpSession session = null;
		Cupom cupom = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("SALVAR")) {
			cupom = criarCupom(request);

		} else if (operacao.equals("ALTERAR")) {
			cupom = criarCupom(request);

		} else if (operacao.equals("EXCLUIR")) {
			cupom = criarCupom(request);

		} else if (operacao.equals("CONSULTAR")) {
			cupom = criarCupom(request);

		}
		
		return cupom;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		RequestDispatcher d = null;
		String operacao = request.getParameter("btnOperacao");
		String uri = request.getRequestURI();

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("")) {
				request.getSession().setAttribute("resultadoErroCupom", resultado);
				d = request.getRequestDispatcher("EscolherPagamento.jsp");

		} else if (operacao.equals("SALVAR")) {
				resultado.setMsg("Cadastro realizado com sucesso.");
				request.getSession().setAttribute("resultado", resultado);
				Resultado result = (Resultado)request.getSession().getAttribute("UsuarioAutenticado");
				d = request.getRequestDispatcher("ControleCartao?btnOperacao=CONSULTAR&id=" + result.getEntidades().get(0).getId());
			

		} else if (operacao.equals("CONSULTAR")) {
			
				resultado.getEntidades().remove(resultado.getEntidades().size()-1);
				request.getSession().setAttribute("resultadoCupom", resultado.getEntidades().get(0));
				d = request.getRequestDispatcher("EscolherPagamento.jsp");
			
		}else if (operacao.equals("ALTERAR")) {
			
			resultado.setMsg("Consulta realizada com sucesso.");
			request.getSession().setAttribute("resultadoCupom", resultado.getEntidades().get(0));
			d = request.getRequestDispatcher("EscolherPagamento.jsp");
		
	}
		
		d.forward(request, response);
		
	}
	
	private Cupom criarCupom(HttpServletRequest request) {
		
		Cupom cupom = new Cupom();
		
		if(request.getParameter("btnOperacao").equals("SALVAR")) {
			cupom.setId(Integer.parseInt(request.getParameter("txtIdCliente")));
		}else {
			cupom.setCodigo(request.getParameter("txtCupom"));
		}
		
		return cupom;
	}

}
