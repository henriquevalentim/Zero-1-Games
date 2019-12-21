package br.edu.fatec.les.web.controle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.les.command.AlterarCommand;
import br.edu.fatec.les.command.ConsultarCommand;
import br.edu.fatec.les.command.ExcluirCommand;
import br.edu.fatec.les.command.ICommand;
import br.edu.fatec.les.command.SalvarCommand;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Resultado;
import br.edu.fatec.les.web.viewhelper.AnaliseGraficoVH;
import br.edu.fatec.les.web.viewhelper.CartaoVH;
import br.edu.fatec.les.web.viewhelper.ClienteVH;
import br.edu.fatec.les.web.viewhelper.CupomVH;
import br.edu.fatec.les.web.viewhelper.EnderecoVH;
import br.edu.fatec.les.web.viewhelper.EstoqueVH;
import br.edu.fatec.les.web.viewhelper.IViewHelper;
import br.edu.fatec.les.web.viewhelper.JogoVH;
import br.edu.fatec.les.web.viewhelper.PedidoVH;
import br.edu.fatec.les.web.viewhelper.UsuarioVH;
import br.edu.fatec.les.web.viewhelper.WebServiceCorreiosVH;

@WebServlet(urlPatterns = { "/templates/ControleCliente", "/templates/ControleUsuario", "/templates/admin/ControleJogo",
		"/templates/ConsultarJogo", "/templates/PerfilJogo", "/templates/ControlePagamentoEndereco",
		"/templates/ControleEnderecoEntrega", "/templates/ControlePagamentoCartao",
		"/templates/admin/ControleSolicitacaoPedido", "/templates/admin/ControlePesquisaPedido",
		"/templates/ControleEndereco", "/templates/admin/ControleCliente", "/templates/ControleCartao",
		"/templates/ControlePedido", "/templates/ControleCupom", "/templates/ControleDevolucao",
		"/templates/ControleWebService", "/templates/admin/ControlePedido", "/templates/admin/ControleEntregaPedido",
		"/templates/admin/ControleTrocaPedido", "/templates/admin/ControleEstoque",
		"/templates/admin/ControleGrafico","/templates/ControleSenha", "/templates/admin/ControleAtivacao"})
public class Controle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, ICommand> commands;
	private Map<String, IViewHelper> vhs;

	public Controle() {
		commands = new HashMap<String, ICommand>();
		commands.put("SALVAR", new SalvarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("PERFIL", new ConsultarCommand());

		vhs = new HashMap<String, IViewHelper>();
		vhs.put("/zero1games/templates/ControleCliente", new ClienteVH());
		vhs.put("/zero1games/templates/admin/ControleCliente", new ClienteVH());
		vhs.put("/zero1games/templates/admin/ControleJogo", new JogoVH());
		vhs.put("/zero1games/templates/ConsultarJogo", new JogoVH());
		vhs.put("/zero1games/templates/PerfilJogo", new JogoVH());
		vhs.put("/zero1games/templates/ControleEndereco", new EnderecoVH());
		vhs.put("/zero1games/templates/ControleEnderecoEntrega", new EnderecoVH());
		vhs.put("/zero1games/templates/ControlePesquisaPedido", new EnderecoVH());
		vhs.put("/zero1games/templates/ControlePagamentoEndereco", new EnderecoVH());
		vhs.put("/zero1games/templates/ControleCartao", new CartaoVH());
		vhs.put("/zero1games/templates/ControlePagamentoCartao", new CartaoVH());
		vhs.put("/zero1games/templates/ControleUsuario", new UsuarioVH());
		vhs.put("/zero1games/templates/ControleCarrinhoCompra", new JogoVH());
		vhs.put("/zero1games/templates/ControlePedido", new PedidoVH());
		vhs.put("/zero1games/templates/admin/ControleSolicitacaoPedido", new PedidoVH());
		vhs.put("/zero1games/templates/admin/ControlePesquisaPedido", new PedidoVH());
		vhs.put("/zero1games/templates/ControleCupom", new CupomVH());
		vhs.put("/zero1games/templates/ControleDevolucao", new PedidoVH());
		vhs.put("/zero1games/templates/admin/ControlePedido", new PedidoVH());
		vhs.put("/zero1games/templates/admin/ControleEntregaPedido", new PedidoVH());
		vhs.put("/zero1games/templates/admin/ControleTrocaPedido", new PedidoVH());
		vhs.put("/zero1games/templates/ControleWebService", new WebServiceCorreiosVH());
		vhs.put("/zero1games/templates/admin/ControleEstoque", new EstoqueVH());
		vhs.put("/zero1games/templates/admin/ControleGrafico", new AnaliseGraficoVH());
		vhs.put("/zero1games/templates/ControleSenha", new UsuarioVH());
		vhs.put("/zero1games/templates/admin/ControleAtivacao", new JogoVH());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String operacao = request.getParameter("btnOperacao");

		System.out.println("URI: " + uri);
		System.out.println("OPERACAO: " + request.getParameter("btnOperacao"));

		IViewHelper vh = vhs.get(uri);
		EntidadeDominio entidade = vh.getEntidade(request);

		ICommand command = null;
		Resultado resultado = null;

		command = commands.get(operacao);
		resultado = command.executar(entidade);
		vh.setView(resultado, request, response);

	}
}
