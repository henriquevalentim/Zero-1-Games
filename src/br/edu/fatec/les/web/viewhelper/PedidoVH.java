package br.edu.fatec.les.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Pedido;
import br.edu.fatec.les.dominio.Resultado;
import br.edu.fatec.les.dominio.StatusPedido;
import br.edu.fatec.les.dominio.cliente.Cliente;
import br.edu.fatec.les.web.util.GeradorCodigo;

public class PedidoVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {

		Pedido pedido = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("SALVAR")) {
			pedido = criarPedido(request);

		} else if (operacao.equals("ALTERAR")) {
			pedido = criarPedido(request);

		} else if (operacao.equals("EXCLUIR")) {
			pedido = new Pedido();
			pedido.setId(Integer.parseInt(request.getParameter("idPedido")));

		} else if (operacao.equals("CONSULTAR")) {
			pedido = criarPedido(request);

		}
		return pedido;
	}

	private Pedido criarPedido(HttpServletRequest request) {
		String operacao = request.getParameter("btnOperacao");
		Pedido pedido = new Pedido();
		Cliente cliente = null;
		StatusPedido statusPedido = new StatusPedido();
		statusPedido.setId(0);
		
		if (operacao.equals("CONSULTAR")) {
			cliente = new Cliente();
			if(request.getParameter("txtNomeCliente") != null) {
				cliente.setNome(request.getParameter("txtNomeCliente"));
			}
			if(request.getParameter("id") != null) {
				cliente.setId(Integer.valueOf(request.getParameter("id")));
			}
			pedido.setNumeroPedido(request.getParameter("txtNumeroPedido"));
			if(request.getParameter("txtStatus") != null && !request.getParameter("txtStatus").trim().equals("") && request.getParameter("txtSolicitacao") != null) {
				if(!request.getParameter("txtSolicitacao").trim().equals("") ) {					
					statusPedido.setId(Integer.valueOf(request.getParameter("txtStatus")));
					pedido.setStatusPedido(statusPedido);
				}
			}
			pedido.setCliente(cliente);
		}
		
		if (operacao.equals("ALTERAR")) {
			pedido.setId(Integer.valueOf(request.getParameter("idPedido")));
			Resultado result = (Resultado) request.getSession().getAttribute("UsuarioAutenticado");
			pedido.setCliente((Cliente)result.getEntidades().get(0));
			if(request.getParameter("txtStatus") != null) {				
				statusPedido.setId(Integer.valueOf(request.getParameter("txtStatus")));
			}
			if(request.getParameter("op") != null) {				
				if(request.getParameter("op").equals("true")) {				
					statusPedido.setNome("sim");
				}else {
					statusPedido.setNome("nao");
				}
			}
			String strValor = request.getParameter("txtValor");
			if(strValor != null && !strValor.trim().equals("")) {
				pedido.setSubtotal(Double.valueOf(strValor));
			}
			pedido.setStatusPedido(statusPedido);
			pedido.setSolicitacao(request.getParameter("txtSolicitacao"));
			
		}
		
		if (operacao.equals("SALVAR")) {
			Resultado result = (Resultado) request.getSession().getAttribute("UsuarioAutenticado");
			pedido = (Pedido)request.getSession().getAttribute("pedido");
			pedido.setCliente((Cliente)result.getEntidades().get(0));
			pedido.setNumeroPedido(GeradorCodigo.gerarCodigoPedido());
			statusPedido.setId(1);
			pedido.setStatusPedido(statusPedido);
			double total = pedido.getSubtotal();
			double desconto = 0;
			if(request.getParameter("txtDescontoCupom") != null && !request.getParameter("txtDescontoCupom").equals("")) {				
				desconto = Double.valueOf(request.getParameter("txtDescontoCupom"));
			}
			if(pedido.getFormaPagamento().size() == 1) {
				pedido.getFormaPagamento().get(0).setValor(total - desconto);
			}
	
			pedido.setSubtotal(total - desconto);
		}
		
		return pedido;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("btnOperacao");
		String uri = request.getRequestURI();

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("") && operacao.equals("SALVAR")) {
			System.out.println("adicionando resultado na sessão");
			request.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("resumo.jsp");
		
		}else if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("") && operacao.equals("ALTERAR")) {
				System.out.println("adicionando resultado na sessão");
				request.setAttribute("resultadoErroAlterar", resultado);
				d = request.getRequestDispatcher("AlterarCliente.jsp");

		} else if (operacao.equals("SALVAR")) {
			resultado.setMsg("Cadastro realizado com sucesso.");
			request.setAttribute("resultadoCliente", resultado);
			d = request.getRequestDispatcher("ConfirmaPagamento.jsp");

		} else if (operacao.equals("CONSULTAR")) {
			resultado.setMsg("Consulta realizada com sucesso.");
			if(uri.equals("/zero1games/templates/admin/ControleSolicitacaoPedido")) {
				resultado.getEntidades().remove(resultado.getEntidades().size() - 1);
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("Pendencias.jsp");
			}else if(uri.equals("/zero1games/templates/admin/ControlePesquisaPedido")) {
				resultado.getEntidades().remove(resultado.getEntidades().size() - 1);
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("PesquisaPedidos.jsp");
			}else if(uri.equals("/zero1games/templates/ControlePedido")) {
				resultado.getEntidades().remove(resultado.getEntidades().size() - 1);
				
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("pedidos.jsp");
			}else if(uri.equals("/zero1games/templates/admin/ControleEntregaPedido")) {
				resultado.getEntidades().remove(resultado.getEntidades().size() - 1);
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("Entrega.jsp");
			}else if(uri.equals("/zero1games/templates/admin/ControleTrocaPedido")) {
				resultado.getEntidades().remove(resultado.getEntidades().size() - 1);
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("Troca.jsp");
			}

		} else if (operacao.equals("ALTERAR")) {
			if(uri.equals("/zero1games/templates/ControleDevolucao")) {
				resultado.setMsg("Alteração realizada com sucesso.");
				request.setAttribute("resultado", resultado);
				Resultado result = (Resultado)request.getSession().getAttribute("UsuarioAutenticado");
				Cliente cliente = (Cliente)result.getEntidades().get(0);
				d = request.getRequestDispatcher("ControlePedido?btnOperacao=CONSULTAR&id=" + cliente.getUsuario().getId());
			}else if(uri.equals("/zero1games/templates/admin/ControleSolicitacaoPedido")) {
				resultado.setMsg("Alteração realizada com sucesso.");
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("ControleSolicitacaoPedido?btnOperacao=CONSULTAR");
			}else if(uri.equals("/zero1games/templates/admin/ControleEntregaPedido")) {
				resultado.setMsg("Alteração realizada com sucesso.");
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("ControleEntregaPedido?btnOperacao=CONSULTAR");
			}else if(uri.equals("/zero1games/templates/admin/ControleTrocaPedido")){
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("ControleTrocaPedido?btnOperacao=CONSULTAR");
			}
		} else if (operacao.equals("EXCLUIR")) {
			resultado.setMsg("Cliente inativado com sucesso.");
			request.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("PesquisaCliente.jsp");
		}

		d.forward(request, response);

	}

}
