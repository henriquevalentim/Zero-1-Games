package br.edu.fatec.les.web.viewhelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.MetodoEntrega;
import br.edu.fatec.les.dominio.Resultado;
import br.edu.fatec.les.web.util.WebServiceCorreio;

public class WebServiceCorreiosVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {

		// HttpSession session = null;
		MetodoEntrega metodoEntrega = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("CONSULTAR")) {
			metodoEntrega = criarMedodoEntrega(request);
		}

		return metodoEntrega;
	}

	private MetodoEntrega criarMedodoEntrega(HttpServletRequest request) {

		MetodoEntrega metodoEntrega = null;

		String cep = request.getParameter("cep");
		metodoEntrega = WebServiceCorreio.calculaPrecoPrazo(cep);
		
		return metodoEntrega;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("CONSULTAR")) {
			String json = new Gson().toJson(resultado.getEntidades());
			response.setContentType("text/json");
			System.out.println(json.toString());
			response.getWriter().write(json.toString());

		}
	}

}
