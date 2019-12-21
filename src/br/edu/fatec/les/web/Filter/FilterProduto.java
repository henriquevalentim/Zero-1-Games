package br.edu.fatec.les.web.Filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.edu.fatec.les.dao.IDAO;
import br.edu.fatec.les.dao.JogoDAO;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.jogo.Jogo;

@WebFilter("/templates/index.jsp")
public class FilterProduto implements Filter {

	public FilterProduto() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		List<EntidadeDominio> jogos = null;
		jogos = (List<EntidadeDominio>) request.getAttribute("todosJogos");
		if (jogos == null) {
			IDAO jogoDAO = new JogoDAO();
			Jogo jogo = new Jogo();
			List<EntidadeDominio> entidades = (List<EntidadeDominio>) jogoDAO.consultar(jogo);
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;

			request.setAttribute("todosJogos", entidades);
		}

		chain.doFilter(request, response);

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
