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
import javax.servlet.http.HttpSession;

import br.edu.fatec.les.dao.IDAO;
import br.edu.fatec.les.dao.JogoDAO;
import br.edu.fatec.les.dominio.CarrinhoCompra;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Item;
import br.edu.fatec.les.dominio.jogo.Jogo;

@WebFilter("/templates/CarrinhoCompra")
public class FilterCarrinho implements Filter {

    public FilterCarrinho() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String operacao = req.getParameter("btnOperacao");
		HttpSession session = req.getSession();
		
		if(req.getParameter("id") != null) {
			Integer id = Integer.valueOf(req.getParameter("id"));
			if(operacao.equals("ADICIONAR")) {
				
				IDAO jogoDAO = new JogoDAO();
				Jogo jogo = new Jogo();
				jogo.setId(id);
				List<EntidadeDominio> entidades = (List<EntidadeDominio>) jogoDAO.consultar(jogo);
			
				if(req.getSession().getAttribute("carrinho") != null) {
					Item item = new Item();
					CarrinhoCompra carrinho = (CarrinhoCompra) session.getAttribute("carrinho");
					item.setJogo((Jogo)entidades.get(0));
					item.setQuantidade(1);
					carrinho.getItens().add(item);
					req.getSession().setAttribute("carrinho", carrinho);
				}else {
					Item item = new Item();
					CarrinhoCompra carrinho = new CarrinhoCompra();
					item.setJogo((Jogo)entidades.get(0));
					item.setQuantidade(1);
					carrinho.getItens().add(item);
					req.getSession().setAttribute("carrinho", carrinho);
				}
			}else if(operacao.equals("REMOVER")){
				CarrinhoCompra carrinho = (CarrinhoCompra) session.getAttribute("carrinho");
				for (int i = 0;i < carrinho.getItens().size();i++) {
					System.out.println("idjogo: "+carrinho.getItens().get(i).getJogo().getId());
					if(carrinho.getItens().get(i).getJogo().getId().equals(id)) {
						carrinho.getItens().remove(i);
						break;
					}
				}
				req.getSession().setAttribute("carrinho", carrinho);
				System.out.println(carrinho.getItens().size());
				System.out.println(id);
			}else if(operacao.equals("ADD")){
				CarrinhoCompra carrinho = (CarrinhoCompra) session.getAttribute("carrinho");
				for (int i = 0;i < carrinho.getItens().size();i++) {
					String nomeInput = "qtde"+id;
					if(carrinho.getItens().get(i).getJogo().getId().equals(id) && 
						!(Integer.parseInt(req.getParameter(nomeInput))+1 > carrinho.getItens().get(i).getJogo().getQuantidade())) {
						carrinho.getItens().get(i).setQuantidade(Integer.parseInt(req.getParameter(nomeInput))+1);
						break;
					}
					if(Integer.parseInt(req.getParameter(nomeInput))+1 > carrinho.getItens().get(i).getJogo().getQuantidade()) {
						String msg = "O produto " + carrinho.getItens().get(i).getJogo().getTitulo() +" tem apenas " + carrinho.getItens().get(i).getJogo().getQuantidade() + " no estoque." ;
						req.setAttribute("msg", msg);
					}else {
						req.removeAttribute("msg");
					}
				}
				req.getSession().setAttribute("carrinho", carrinho);
			}else if(operacao.equals("REM")){
				CarrinhoCompra carrinho = (CarrinhoCompra) session.getAttribute("carrinho");
				for (int i = 0;i < carrinho.getItens().size();i++) {
					String nomeInput = "qtde"+id;
					if(carrinho.getItens().get(i).getJogo().getId().equals(id) && carrinho.getItens().get(0).getQuantidade() != 1) {
						carrinho.getItens().get(i).setQuantidade(Integer.parseInt(req.getParameter(nomeInput))-1);
						break;
					}
				}
				req.getSession().setAttribute("carrinho", carrinho);
			}
		}
			
		
		request.getRequestDispatcher("carrinho.jsp").forward(request, response);
		//chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
