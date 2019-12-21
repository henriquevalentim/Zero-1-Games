package br.edu.fatec.les.web.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = { "/templates/AlterarCliente.jsp", "/templates/CadastrarCartao.jsp","/templates/endereco.jsp",
		"/templates/CadastrarEndereco.jsp", "/templates/cartao.jsp", "/templates/EditarEndereco.jsp","/templates/pedidos.jsp" })
public class FilterVerificarAutenticacao implements Filter {

	public FilterVerificarAutenticacao() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		if (req.getSession().getAttribute("UsuarioAutenticado") == null) {
			// dispatcher to login.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);

		} else {
			// they are already logged
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
