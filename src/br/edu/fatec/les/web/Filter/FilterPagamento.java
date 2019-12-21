package br.edu.fatec.les.web.Filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.edu.fatec.les.dao.CartaoDAO;
import br.edu.fatec.les.dao.EnderecoDAO;
import br.edu.fatec.les.dao.IDAO;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Resultado;
import br.edu.fatec.les.dominio.cliente.Cartao;
import br.edu.fatec.les.dominio.cliente.Endereco;

@WebFilter(urlPatterns = { "/templates/pagamento.jsp", "/templates/ControlePagamentoEndereco","/templates/ControlePagamentoCartao",
		"/templates/EscolherEndereco.jsp","/templates/EscolherPagamento.jsp","/templates/resumo.jsp","/templates/ControleCupom" })
public class FilterPagamento implements Filter {

	public FilterPagamento() {
	}

	public void destroy() {
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if(httpServletRequest.getSession().getAttribute("UsuarioAutenticado") == null) {
            //dispatcher to login.jsp        
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);

        }else {
			System.out.println(httpServletRequest.getRequestURL());
	
			List<EntidadeDominio> enderecos = null;
			List<EntidadeDominio> cartoes = null;
	
			Resultado result = (Resultado) httpServletRequest.getSession().getAttribute("UsuarioAutenticado");
	
			int idClienteLogado = result.getEntidades().get(0).getId();
	
			enderecos = (List<EntidadeDominio>) request.getAttribute("enderecos");
			cartoes = (List<EntidadeDominio>) request.getAttribute("cartoes");
	
			IDAO enderecoDAO = new EnderecoDAO();
			Endereco endereco = new Endereco();
			endereco.setId(idClienteLogado);// colocando id do usuario logado no endereco
			List<EntidadeDominio> entidadesEndereco = (List<EntidadeDominio>) enderecoDAO.consultar(endereco);
	
			IDAO cartaoDAO = new CartaoDAO();
			Cartao cartao = new Cartao();
			cartao.setId(idClienteLogado);// colocando id do usuario logado no endereco
			List<EntidadeDominio> entidadesCartao = (List<EntidadeDominio>) cartaoDAO.consultar(cartao);
	
			request.setAttribute("enderecos", entidadesEndereco);
			request.setAttribute("cartoes", entidadesCartao);
	
			chain.doFilter(request, response);
        }
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
