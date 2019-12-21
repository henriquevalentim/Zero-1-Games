package br.edu.fatec.les.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.les.dominio.CarrinhoCompra;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Item;
import br.edu.fatec.les.dominio.Resultado;
import br.edu.fatec.les.dominio.cliente.Motivo;
import br.edu.fatec.les.dominio.jogo.Desenvolvedor;
import br.edu.fatec.les.dominio.jogo.Distribuidora;
import br.edu.fatec.les.dominio.jogo.Jogo;

public class JogoVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Jogo jogo = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("SALVAR")) {
			jogo = criarJogo(request);

		} else if (operacao.equals("ALTERAR")) {
			jogo = criarJogo(request);

		} else if (operacao.equals("EXCLUIR")) {
			jogo = new Jogo();
			if (request.getParameter("id") != null) {
				jogo.setId(Integer.parseInt(request.getParameter("id")));
				jogo.setMotivo(Motivo.JOGO_INATIVADO_PELO_ADMIN.toString());
			}

		} else if (operacao.equals("CONSULTAR")) {
			jogo = criarJogo(request);

		} else if (operacao.equals("PERFIL")) {
			int id = Integer.parseInt(request.getParameter("id"));
			jogo = new Jogo();
			jogo.setId(id);
		}

		return jogo;
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
		
		if(request.getParameter("txtCodigo") != null && !request.getParameter("txtCodigo").trim().equals("")) {
			jogo.setCodigo(request.getParameter("txtCodigo"));			
		}else {
			jogo.setCodigo("");
		}
		jogo.setTitulo(request.getParameter("txtTitulo"));
		jogo.setPlataforma(request.getParameter("txtPlataforma"));
		jogo.setGeneroJogo(request.getParameter("txtGenero"));
		jogo.setClassificacaoIndicativa(request.getParameter("txtClassificacaoIndicativa"));
		jogo.setUrlFoto(request.getParameter("txtUrlFoto"));
		
		String strNumeroJogadoresOffline = request.getParameter("txtNumeroJogadoresOffline");
		String strNumeroJogadoresOnline = request.getParameter("txtNumeroJogadoresOnline");
		String strPrecoCompra = request.getParameter("txtPrecoCompra");
		String strQuantidade = request.getParameter("txtQuantidade");
		
		if(request.getParameter("txtStatus") != null && !request.getParameter("txtStatus").isEmpty()) {
			jogo.setStatus(Boolean.valueOf(request.getParameter("txtStatus")));
		}
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
		}else {
			jogo.setQuantidade(0);
		}
		if (operacao.equals("SALVAR") || operacao.equals("ALTERAR") || operacao.equals("PERFIL")) {
			if(request.getParameter("txtDescricao") != null) {
				jogo.setDescricao(request.getParameter("txtDescricao").trim());
			}
			jogo.setMotivo(Motivo.NA.toString());
		}
		if(request.getParameter("op") != null) {
			if(operacao.equals("ALTERAR") && request.getParameter("op").equals("ativar")) {
				jogo.setMotivo(Motivo.JOGO_ATIVADO_PELO_ADMIN.toString());
			}
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

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("") && operacao.equals("SALVAR")) {
			System.out.println("adicionando resultado na sessão");
			request.setAttribute("resultadoSalvar", resultado);
			d = request.getRequestDispatcher("CadastroProduto.jsp");

		} else if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("") && operacao.equals("ALTERAR")) {
			System.out.println("adicionando resultado na sessão");
			request.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("EditarProduto.jsp");

		} else if (operacao.equals("SALVAR")) {
			resultado.setMsg("Cadastro realizado com sucesso.");
			request.setAttribute("clienteSalvo", resultado);
			d = request.getRequestDispatcher("CadastroProduto.jsp");

		} else if (operacao.equals("CONSULTAR")) {
			
			resultado.setMsg("Consulta realizada com sucesso.");
			request.setAttribute("jogo", resultado.getEntidades().get(resultado.getEntidades().size() - 1));
			resultado.getEntidades().remove(resultado.getEntidades().size() - 1);

			request.getSession().setAttribute("resultadoConsultaJogo", resultado);
			if(uri.equals("/zero1games/templates/ConsultarJogo")) {
				d = request.getRequestDispatcher("categorias.jsp");				
			}else if(uri.equals("/zero1games/templates/admin/ControleJogo")){
				d = request.getRequestDispatcher("PesquisaProduto.jsp");
			}else if(uri.equals("/zero1games/templates/ControleCarrinhoCompra")) {
				if(request.getSession().getAttribute("carrinho") != null) {
					Item item = new Item();
					CarrinhoCompra carrinho = (CarrinhoCompra) session.getAttribute("carrinho");
					item.setJogo((Jogo)resultado.getEntidades().get(0));
					item.setQuantidade(1);
					carrinho.getItens().add(item);
					request.getSession().setAttribute("carrinho", carrinho);
				}else {
					Item item = new Item();
					CarrinhoCompra carrinho = new CarrinhoCompra();
					item.setJogo((Jogo)resultado.getEntidades().get(0));
					item.setQuantidade(1);
					carrinho.getItens().add(item);
					request.getSession().setAttribute("carrinho", carrinho);
				}
				d = request.getRequestDispatcher("carrinho.jsp");
			}else if(uri.equals("/zero1games/templates/ControleREMCarrinhoCompra")) {
				CarrinhoCompra carrinho = (CarrinhoCompra) session.getAttribute("carrinho");
				int id = Integer.valueOf(request.getParameter("id"));
				int cont = 0;
				for (Item item : carrinho.getItens()) {
					if(item.getId() == id) {
						carrinho.getItens().remove(cont);
						break;
					}
					cont++;
				}
				d = request.getRequestDispatcher("carrinho.jsp");
			}else if (uri.equals("/zero1games/templates/admin/ControleEstoque")) {
				d = request.getRequestDispatcher("ControleEstoque.jsp");	
			}

		} else if (operacao.equals("ALTERAR")) {
			resultado.setMsg("Altera��oo realizada com sucesso.");
			request.setAttribute("resultadoAlterarJogo", resultado);
			request.getSession().invalidate();
			d = request.getRequestDispatcher("PesquisaProduto.jsp");

		} else if (operacao.equals("EXCLUIR")) {
			resultado.setMsg("Jogo inativado com sucesso.");
			request.getSession().invalidate();
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("ControleJogo?btnOperacao=CONSULTAR");

		} else if (operacao.equals("PERFIL")) {
			Jogo jogo = (Jogo) resultado.getEntidades().get(0);
			request.setAttribute("jogoPerfil", jogo);
			
			if(uri.equals("/zero1games/templates/PerfilJogo")) {
				d = request.getRequestDispatcher("produto.jsp");
			}else if(uri.equals("/zero1games/templates/admin/ControleJogo")) {
				d = request.getRequestDispatcher("EditarProduto.jsp");
			}
		}

		d.forward(request, response);

	}

}
