package br.edu.fatec.les.web.viewhelper;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.les.dominio.AnaliseGrafico;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Resultado;

public class AnaliseGraficoVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		AnaliseGrafico analiseGrafico = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("CONSULTAR")) {
			analiseGrafico = criarAnalise(request);
		}
		
		return analiseGrafico;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		RequestDispatcher d = null;
		String operacao = request.getParameter("btnOperacao");
		String uri = request.getRequestURI();

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("")) {
			if(uri.equals("/zero1games/templates/ControleGrafico")) {
				System.out.println("adicionando resultado na sessão");
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("CadastrarCartao.jsp");
			}

		} else if (operacao.equals("CONSULTAR")) {
			if(uri.equals("/zero1games/templates/admin/ControleGrafico")) {
				resultado.getEntidades().remove(resultado.getEntidades().size() - 1);
				String json = new Gson().toJson(resultado.getEntidades());
				response.setContentType("text/json");
				System.out.println(json.toString());
				response.getWriter().write(json.toString());
			}
		} 
	}
	
	private AnaliseGrafico criarAnalise(HttpServletRequest request) {
		
		AnaliseGrafico analiseGrafico = new AnaliseGrafico();
		if(request.getParameter("de") != null) {
			analiseGrafico.setDataInicio(LocalDate.parse(request.getParameter("de")));
			analiseGrafico.setDataFim(LocalDate.parse(request.getParameter("ate")));
			analiseGrafico.setTipoAnalise(Integer.valueOf(request.getParameter("tipoAnalise")));
		}
		
		return analiseGrafico;
	}

}
