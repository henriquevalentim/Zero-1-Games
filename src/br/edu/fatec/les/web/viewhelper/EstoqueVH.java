package br.edu.fatec.les.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Estoque;
import br.edu.fatec.les.dominio.Resultado;
import br.edu.fatec.les.dominio.jogo.Desenvolvedor;
import br.edu.fatec.les.dominio.jogo.Distribuidora;
import br.edu.fatec.les.dominio.jogo.Jogo;

public class EstoqueVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Estoque estoque = null;
		Jogo jogo = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("ALTERAR")) {
			estoque = criarEstoque(request);
			return estoque;

		} else if (operacao.equals("CONSULTAR")) {
			jogo = criarJogo(request);

		}

		return jogo;
	}

	private Estoque criarEstoque(HttpServletRequest request) {
		String operacao = request.getParameter("btnOperacao");
		String acao = request.getParameter("txtAcao"); 
				
		Estoque estoque = new Estoque();
		Jogo jogo = new Jogo();

		if (operacao.equals("ALTERAR")) {
			if (request.getParameter("idJogo") != null) {

				jogo.setId(Integer.parseInt(request.getParameter("idJogo")));
				estoque.setJogo(jogo);
				
				if (request.getParameter("txtQuantidade") == null || request.getParameter("txtQuantidade").trim(). equals("")) {
					estoque.setQuantidade(null);
				}
				
				else if (acao.contentEquals("Adicionar")) {
					estoque.setQuantidade(Integer.valueOf((request.getParameter("txtQuantidade"))));
				}
				else {
					estoque.setQuantidade(Integer.valueOf((request.getParameter("txtQuantidade"))) * (-1));
				}
			}
		}

		return estoque;
	}

	private Jogo criarJogo(HttpServletRequest request) {
		String operacao = request.getParameter("btnOperacao");

		Jogo jogo = new Jogo();
		Desenvolvedor desenvolvedor = new Desenvolvedor();
		Distribuidora distribuidora = new Distribuidora();

		desenvolvedor.setNome(request.getParameter("txtDesenvolvedor"));
		distribuidora.setNome(request.getParameter("txtDistribuidora"));

		jogo.setDesenvolvedor(desenvolvedor);
		jogo.setDistribuidora(distribuidora);

		if (operacao.equals("ALTERAR") || operacao.equals("CONSULTAR")) {
			if (request.getParameter("id") != null) {
				jogo.setId(Integer.parseInt(request.getParameter("id")));
			}
		}

		jogo.setCodigo(request.getParameter("txtCodigo"));
		jogo.setTitulo(request.getParameter("txtTitulo"));
		jogo.setPlataforma(request.getParameter("txtPlataforma"));
		jogo.setGeneroJogo(request.getParameter("txtGenero"));
		jogo.setClassificacaoIndicativa(request.getParameter("txtClassificacaoIndicativa"));
		jogo.setStatus(true);
		jogo.setUrlFoto(request.getParameter("txtUrlFoto"));

		String strNumeroJogadoresOffline = request.getParameter("txtNumeroJogadoresOffline");
		String strNumeroJogadoresOnline = request.getParameter("txtNumeroJogadoresOnline");
		String strPrecoCompra = request.getParameter("txtPrecoCompra");
		String strQuantidade = request.getParameter("txtQuantidade");

		if (strNumeroJogadoresOffline != null && !strNumeroJogadoresOffline.trim().equals("")) {
			jogo.setNumeroJogadoresOffline(Integer.parseInt(strNumeroJogadoresOffline.replace(".", "")));
		}
		if (strNumeroJogadoresOnline != null && !strNumeroJogadoresOnline.trim().equals("")) {
			jogo.setNumeroJogadoresOnline(Integer.parseInt(strNumeroJogadoresOnline.replace(".", "")));
		}
		if (strPrecoCompra != null && !strPrecoCompra.trim().equals("")) {
			jogo.setPrecoCompra(Double.parseDouble(strPrecoCompra.replace(".", "").replace(",", ".")));
		}
		if (strQuantidade != null && !strQuantidade.trim().equals("")) {
			jogo.setQuantidade(Integer.parseInt(strQuantidade));
		}
		if (operacao.equals("SALVAR") || operacao.equals("ALTERAR") || operacao.equals("PERFIL")) {
			jogo.setDescricao(request.getParameter("txtDescricao").trim());
		}

		return jogo;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		HttpSession session = request.getSession();
		String operacao = request.getParameter("btnOperacao");
		String uri = request.getRequestURI();

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("") && operacao.equals("ALTERAR")) {
			System.out.println("adicionando resultado na sessao");
			request.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("ControleEstoque.jsp");

		} else if (operacao.equals("CONSULTAR")) {

			resultado.setMsg("Consulta realizada com sucesso.");
			request.setAttribute("jogo", resultado.getEntidades().get(resultado.getEntidades().size() - 1));
			resultado.getEntidades().remove(resultado.getEntidades().size() - 1);

			request.getSession().setAttribute("resultadoConsultaJogo", resultado);
			
			if (uri.equals("/zero1games/templates/admin/ControleEstoque")) {
				d = request.getRequestDispatcher("ControleEstoque.jsp");
			}

		} else if (operacao.equals("ALTERAR")) {
			resultado.setMsg("Altera��oo realizada com sucesso.");
			request.setAttribute("resultadoAlterarJogo", resultado);
			request.getSession().invalidate();
			d = request.getRequestDispatcher("ControleEstoque.jsp");
		}

		d.forward(request, response);

	}

}
