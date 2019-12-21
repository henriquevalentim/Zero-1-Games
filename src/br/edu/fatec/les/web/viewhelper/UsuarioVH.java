package br.edu.fatec.les.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.Resultado;
import br.edu.fatec.les.dominio.Usuario;
import br.edu.fatec.les.dominio.cliente.Senha;

public class UsuarioVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {

		Usuario usuario = null;
		String operacao = request.getParameter("btnOperacao");

		if (operacao.equals("SALVAR")) {
			usuario = criarUsuario(request);

		} else if (operacao.equals("ALTERAR")) {
			usuario = criarUsuario(request);

		} else if (operacao.equals("EXCLUIR")) {
			usuario = new Usuario();
			usuario.setId(Integer.parseInt(request.getParameter("idCliente")));

		} else if (operacao.equals("CONSULTAR")) {
			usuario = criarUsuario(request);

		}
		return usuario;
	}

	private Usuario criarUsuario(HttpServletRequest request) {

		Usuario usuario = new Usuario();

		usuario.setLogin(request.getParameter("txtLogin"));
		Senha senha = new Senha();
		senha.setSenha(request.getParameter("txtSenha"));
		if(request.getParameter("senhaNova") != null && !request.getParameter("senhaNova").isEmpty()) {
			senha.setSenhaTemp(request.getParameter("senhaNova"));
			senha.setConfirmaSenha(request.getParameter("confirmaSenha"));
			usuario.setId(Integer.valueOf(request.getParameter("txtUsuarioId")));
		}
		System.out.println(request.getParameter("txtSenha"));
		usuario.setSenha(senha);

		return usuario;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("btnOperacao");

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("") && operacao.equals("CONSULTAR")) {
			System.out.println("UsuarioVH: Erro ao logar");
			request.setAttribute("usuarioErro", resultado);
			d = request.getRequestDispatcher("login.jsp");

		} else if (operacao.equals("CONSULTAR")) {
			request.getSession().setAttribute("UsuarioAutenticado", resultado);
			d = request.getRequestDispatcher("AlterarCliente.jsp");

		} else if (operacao.equals("ALTERAR")) {

			d = request.getRequestDispatcher("AlterarCliente.jsp");

		}

		d.forward(request, response);

	}

}
